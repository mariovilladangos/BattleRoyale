package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.utad.poo.battleroyale.ui.*;

public class GameManager2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    Boolean endgame = false;
	    Integer day=0;
	    List<Player> players = new ArrayList<Player>();
	    List<Player> eliminated = new ArrayList<Player>();
	    Random randomNumber = new Random();
	
	    CharacterMenu menu = VisualLobby.fillLobby();
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
		
		players = totalPlayers;
		
		GameMenu game = new GameMenu();
		Integer action = 0;
		
		while(!endgame) {
			GameManager.saveStats(players, day);
			
			day++;
			game.addTerminalLine("Día " + day);
			System.out.println("Día " + day);
			
			for(Player player:players) {
				player.lootear(game);
			}
			
    		Collections.shuffle(players);
    		Integer length = players.size();
    		if(length % 2 != 0) {
    			length -= 1;
    			players.get(length).autoDamage(game);
    		}
    		
    		for(int i=0;i<length; i+=2) {
				Random rand = new Random();
				Integer probLucha = rand.nextInt(100);
				if(probLucha <= 50) {
					players.get(i).combat(game, players.get(i+1));
				}
			}
    		
    		List<Player> deadPlayers = new ArrayList();
    		for(Player player : players) {
    			if(player.getHp() <= 0) {
    				deadPlayers.add(player);
    			}
    		}
    		
	    	players.removeAll(deadPlayers);
	    	eliminated.addAll(deadPlayers);
	    	deadPlayers.clear();
	    	
	    	if (players.size() <= 1) {
	    		endgame = true;
		    	

	    	}
	    	else {
	    		game.addTerminalLine("Jugadores vivos: " + players.size());
		    	System.out.println("Jugadores vivos: " + players.size());
		    	//System.out.println("Pulsa la tecla 's' para ver las estadísticas e ir al día siguiente: ");
		    	wait(game, players, action, 3);
		    	/*String pulsaTecla = scanner.nextLine();
		    	if(pulsaTecla.equals("s")) {
		    		for(Player player:players) {
		    			player.showStats();
		    		}
		    	}*/
		    	// utiliar el collections.shufle para randomiar la lista y asi poder hacer los combates
		    	//Cada jugador pelea aleatoriamente con otro
		    	//el que gane obteendra una mejora en el arma de 1 
		    	//el que pierda sera eliminado
	    	}
    	
    	}while(!endgame);
    	
		System.out.println("\n🥇 Victory Royale jugador:" + players.get(0).getName());
		for (int i = eliminated.size() - 1; i >= 0; i--) {
			System.out.println("  #" + (CharacterMenu.NPLAYERS - i) + " " + eliminated.get(i).getName());
		}
		
		System.out.println("Pulsa ENTER para cerrar");
    	String pulsaTecla = scanner.nextLine();
	}
	
	public static Integer wait(GameMenu game, List<Player> players, Integer actualAction, Integer stopAction){

		if (actualAction <= stopAction) {
			actualAction = 0;
			//game.scrollDown();
			game.setShowStats(0);
			game.setPendingAction(0);
			//game.refresh();
			game.clearStatsLine();
		}
		
		Integer statsShown = 0;
		while(actualAction == 0) {
			actualAction = game.getPendingAction();
			if (game.getShowStats() == 1 && statsShown == 0) {
				
				for(Player player: players) {
					player.showStats(game);
					
				}
				
				game.refresh();
				statsShown = 1;
			}
			System.out.print("");
		}
		
		return actualAction;
	}
}
