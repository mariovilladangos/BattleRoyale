package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.battleroyale.ui.CharacterMenu;

public class VisualLobby {
	public static void main(String[] args) {
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.println("Not Ready");
		}
		List<Player> totalPlayers = new ArrayList();
		for(Player p: menu.getPlayers()) totalPlayers.add(p);
		for(Player p: menu.getBotPlayers()) totalPlayers.add(p);
		for(Player p: totalPlayers) System.out.println(p);
	}
}
