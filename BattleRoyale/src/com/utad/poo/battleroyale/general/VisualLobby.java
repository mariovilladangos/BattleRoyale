package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;

import com.utad.poo.battleroyale.ui.CharacterMenu;

public class VisualLobby {
	public static CharacterMenu fillLobby(){
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.print(""); // Relleno para que siga comprobando
		}
		menu.hide();
		
		
		return menu;
	}
}
