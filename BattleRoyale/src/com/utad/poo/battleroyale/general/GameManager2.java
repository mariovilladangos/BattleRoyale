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
			this.wait(game, action, 3);
		}
	}
	
	public Integer wait(GameMenu game, Integer actualAction, Integer StopAction) {

		if (actualAction <= StopAction) actualAction = 0;
		
		while(actualAction == 0) {
			actualAction = game.listenAction();
		}
		
		return actualAction;
	}
}
