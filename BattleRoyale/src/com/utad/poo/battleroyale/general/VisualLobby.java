package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.battleroyale.ui.CharacterMenu;

public class VisualLobby {
	public static void main(String[] args) {
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.print(""); // Relleno para que siga comprobando
		}
		menu.hide();
		
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
		
	}
}
