package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

	        System.out.println(game.buttonListener.p);
			//wait(game, action, 2);
			//System.out.println(action);
			
			/*day++;
			System.out.println("Día " + day);
			
			for(Player player:players) {
				player.lootear();
			}
			wait(game, action, 2);
			
    		Collections.shuffle(players);
    		Integer length = players.size();
    		if(length % 2 != 0) {
    			length -= 1;
    			players.get(length).autoDamage();
    			wait(game, action, 2);
    		}
    		
    		for(int i=0;i<length; i+=2) {
				Random rand = new Random();
				Integer probLucha = rand.nextInt(100);
				if(probLucha <= 50) {
					players.get(i).combat(players.get(i+1));
	    			wait(game, action, 2);
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

			wait(game, action, 1);
	    	if (players.size() <= 1) {
	    		endgame = true;
	    	}
	    	else {
		    	System.out.println("Jugadores vivos: " + players.size());
    			wait(game, action, 3);
	    	}*/
		}
	}
	
	public static Integer wait(GameMenu game, Integer actualAction, Integer StopAction) {

		if (actualAction <= StopAction) actualAction = 0;
		
		while(actualAction == 0) {
			actualAction = game.listenAction();
		}
		
		return actualAction;
	}
}
