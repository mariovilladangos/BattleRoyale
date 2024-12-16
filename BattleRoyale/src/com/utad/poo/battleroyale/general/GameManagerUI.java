package com.utad.poo.battleroyale.general;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.utad.poo.battleroyale.ui.*;

import com.utad.poo.battleroyale.players.Player;

public class GameManagerUI {
	
	public static void main(String[] args) {
		Boolean play = true;
		while(play) {
		    Boolean endgame = false;
		    Integer day=0;
		    List<Player> players = new ArrayList<Player>();
		    List<Player> shufflePlayers = new ArrayList<Player>();
		    List<Player> eliminated = new ArrayList<Player>();
		
		    CharacterMenu menu = lobby();
		    List<Player> totalPlayers = new ArrayList();
			List<Player> realPlayers = new ArrayList();
			List<Player> botPlayers = new ArrayList();
			
			List<String> actionsLog = new ArrayList();
			List<String> stats = new ArrayList();
			
			for(Player p: menu.getPlayers()) {
				realPlayers.add(p);
				totalPlayers.add(p);
			}
			for(Player p: menu.getBotPlayers()) {
				botPlayers.add(p);
				totalPlayers.add(p);
			}
			
			for(Player p: totalPlayers) {
				players.add(p);
				shufflePlayers.add(p);
				System.out.println(p);
			}
			
			GameMenu game = new GameMenu();
			Integer action = 0;
			while(!endgame) {
				
				// Guarda las stats antes de empezar el dia. Ejemplo salida:
				// - Dia 0: muestra el estado de los jugadores antes de empezar el dia 1
				// - Dia 2: el estado de los juagadores al fianl del dia 2
				stats = loadStats(stats, players, day);
				
				day++;
				game.addTerminalLine("Día " + day);
    			actionsLog.add("Empieza el dia " + day);
				
				
				for(Player player:shufflePlayers) {
					Integer hp = player.getHp();
					Integer weaponLvl = player.getWeapon().getLevel();
					
					Integer[] res = player.lootear(game, players, action);
					action = res[0];
					if (res[1] == 1)
						actionsLog.add("  " + player.getName() + " se cura +" + (player.getHp() - hp) + "ps");
					else if (res[1] == 2)
						if (weaponLvl == player.getWeapon().getLevel()) actionsLog.add("  " + player.getName() + " ha encontrado una piedra de mejora pero ya tiene el arma al máximo");
						else actionsLog.add("  " + player.getName() + " ha mejorado su " + player.getWeaponType() + " a nivel " + player.getWeapon().getLevel());
				}
				action = wait(game, players, action, 2);
				
	    		Collections.shuffle(shufflePlayers);
	    		Integer length = shufflePlayers.size();
	    		if(length % 2 != 0) {
	    			length -= 1;
	    			
	    			Integer hp = shufflePlayers.get(length).getHp();
	    			shufflePlayers.get(length).autoDamage(game);
	    			if (shufflePlayers.get(length).getHp() - hp != 0) actionsLog.add("  " + shufflePlayers.get(length).getName() + " se ha autoinflingido -" + (hp - shufflePlayers.get(length).getHp()) + "ps");
	    			if (shufflePlayers.get(length).getHp() <= 0) actionsLog.add("  " + shufflePlayers.get(length).getName() + " ha muerto");
	    			
	    			action = wait(game, players, action, 2);
	    		}
	    		
	    		for(int i=0;i<length; i+=2) {
					Random rand = new Random();
					Integer probLucha = rand.nextInt(100);
					if(probLucha <= 50) {
						action = shufflePlayers.get(i).combat(game, action, players, shufflePlayers.get(i+1));
						
						if (shufflePlayers.get(i).getHp() <= 0) actionsLog.add("  " + shufflePlayers.get(i + 1).getName() + " ha eliminado a " + shufflePlayers.get(i).getName());
						else if (shufflePlayers.get(i + 1).getHp() <= 0) actionsLog.add("  " + shufflePlayers.get(i).getName() + " ha eliminado a " + shufflePlayers.get(i + 1).getName());
						
						action = wait(game, players, action, 2);
					}
				}
	    		
	    		List<Player> deadPlayers = new ArrayList();
	    		for(Player player : players) {
	    			if(player.getHp() <= 0) {
	    				deadPlayers.add(player);
	    			}
	    		}
	    		
		    	players.removeAll(deadPlayers);
		    	shufflePlayers.removeAll(deadPlayers);
		    	eliminated.addAll(deadPlayers);
		    	deadPlayers.clear();
		    	
		    	if (players.size() <= 1) {
		    		endgame = true;
		    		game.addTerminalLine("🥇 Victory Royale: " + players.get(0).getName());
		    		actionsLog.add(players.get(0).getName() + " gana la competicion");
		    		action = wait(game, players, action, 3);
	
		    	}
		    	else {
		    		game.addTerminalLine("Jugadores vivos: " + players.size());
			    	action = wait(game, players, action, 3);
		    	}
		    	game.addTerminalLine(" ");
	    	}while(!endgame);
	    	
	    	game.hide();
	    	
	    	List<String> podiumList = new ArrayList();
	    	podiumList.add("  🥇  " + players.get(0).getName());
	    	for (int i = eliminated.size() - 1; i >= 0; i--) {
	    		podiumList.add("  #" + (CharacterMenu.NPLAYERS - i) + " " + eliminated.get(i).getName());
	    	}
	    	
	    	// Guarda las stats al acabar la partida (estado del jugador ganador)
	    	stats = loadStats(stats, players, day);
	    	
	    	Integer option = podium(podiumList, actionsLog, stats);
			if (option == 1) play = false;
		}
	}
	
	public static Integer wait(GameMenu game, List<Player> players, Integer actualAction, Integer stopAction){

		if (actualAction <= stopAction) {
			actualAction = 0;
			for(Player player: players) {
				if (player.getHp() > 0) {
					game.addBoardLine(player.getName() + " (" + player.getClassType() + ": " + player.getHp() + "ps)");
					game.addBoardLine("  → " + player.getWeaponType() + " lvl." + player.getWeapon().getLevel() + ": " +
							player.getWeapon().getDamage()[player.getWeapon().getLevel() - 1] + "dmg");
					game.addBoardLine(" ");
				}
			}
			game.setPlayers(players);
			game.printAllLines();
			game.setShowStats(0);
			game.setPendingAction(0);
			game.scrollToBottom(game.getTerminal());
		}
		
		Integer statsShown = 0;
		while(actualAction == 0) {
			actualAction = game.getPendingAction();
			if (game.getShowStats() == 1 && statsShown == 0) {
				
				for(Player player: players) {
					player.showStats(game);
					
				}
				statsShown = 1;
			}
			System.out.print("");
		}
		return actualAction;
	}
	

	private static List<String> loadStats(List<String> stats, List<Player> players, Integer day){
		stats.add("=================================================================================");
		stats.add("- Día " + (day));
        for (Player player : players) {
        	stats.add("---------------------------------------------------------------------------------");
        	stats.add(" -  " + player.getName());
        	stats.add("    Salud restante: " + player.getHp());
        	stats.add("    Clase: " + player.getClassType());
        	stats.add("    Arma: " + player.getWeaponType());
        	stats.add("       Nivel del arma: " + player.getWeapon().getLevel());
        	stats.add("       Daño del arma: " + player.getWeapon().getDamage()[player.getWeapon().getLevel()-1]);
        }
        
        return stats;
    }
	
	public static CharacterMenu lobby(){
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.print("");
		}
		menu.hide();
		
		return menu;
	}
	
	public static Integer podium(List<String> podiumList, List<String> actionsLog, List<String> stats){
		PodiumMenu podium = new PodiumMenu();
		podium.printPodium(podiumList);
		
		Boolean saved = false;
		Integer option = 0;
		while(option == 0) {
			option = podium.getOption();
			if (option == 3) {
				option = 0;
				podium.setOption(0);
				String fName = null;
				if (!saved) fName = podium.saveFileName();
				if (fName != null && !fName.isBlank()) {
					Ficheros.saveActions(actionsLog, fName);
					Ficheros.saveStats(stats, fName);
					saved = true;
				}
			}
			System.out.print("");
		}
		podium.hide();
		
		return option;
	}
}
