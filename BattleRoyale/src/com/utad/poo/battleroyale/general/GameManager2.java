package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.utad.poo.battleroyale.ui.*;

import com.utad.poo.battleroyale.players.Player;

public class GameManager2 {
	public static void main(String[] args) {
		
		Boolean play = true;
		while(play) {
			Scanner scanner = new Scanner(System.in);
		    Boolean endgame = false;
		    Integer day=0;
		    List<Player> players = new ArrayList<Player>();
		    List<Player> shufflePlayers = new ArrayList<Player>();
		    List<Player> eliminated = new ArrayList<Player>();
		    Random randomNumber = new Random();
		
		    CharacterMenu menu = lobby();
		    List<Player> totalPlayers = new ArrayList();
			List<Player> realPlayers = new ArrayList();
			List<Player> botPlayers = new ArrayList();
			
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
				
				day++;
				game.addTerminalLine("Día " + day);
				
				Bots.saveStats(players, day);
				
				for(Player player:shufflePlayers) {
					action = player.lootear(game, players, action);
				}
				action = wait(game, players, action, 2);
				
	    		Collections.shuffle(shufflePlayers);
	    		Integer length = shufflePlayers.size();
	    		if(length % 2 != 0) {
	    			length -= 1;
	    			shufflePlayers.get(length).autoDamage(game);
	    			action = wait(game, players, action, 2);
	    		}
	    		
	    		for(int i=0;i<length; i+=2) {
					Random rand = new Random();
					Integer probLucha = rand.nextInt(100);
					if(probLucha <= 50) {
						shufflePlayers.get(i).combat(game, action, players, shufflePlayers.get(i+1));
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
	    	
	    	Integer option = podium(podiumList);
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
	
	
	public static CharacterMenu lobby(){
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.print("");
		}
		menu.hide();
		
		return menu;
	}
	
	public static Integer podium(List<String> podiumList){
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
				if (fName != null && !fName.isBlank()) saved = true;
			}
			System.out.print("");
		}
		podium.hide();
		
		return option;
	}
}
