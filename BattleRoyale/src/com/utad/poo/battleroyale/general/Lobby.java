package com.utad.poo.battleroyale.general;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Lobby {
	public final static Integer DEF_PLAYERS = 10;
	
	public static List<Player> fillLobby() throws Exception {
		Scanner scanner = new Scanner(System.in);
		List<Player> players = new ArrayList();
		while (players.size() < DEF_PLAYERS) {
			
			System.out.print("Quieres crear un personaje (1) o autorellenar con bots (2): ");
			Integer opt = scanner.nextInt();
			if (scanner.hasNextLine()) scanner.nextLine(); // clean buffer
			
			if (opt == 1) {
				// Pedir nombre, luego clase, luego arma (entre una lista predefinida y elige con un entero igual que opt)
				System.out.print("Escribe el nombre del jugador: ");
				String name = scanner.nextLine();
				
				Integer type = -1;
				while(type < 0 || type > 3){
					System.out.print("Elige una clase (1: Guerrero, 2: Curandero, 3: Prisionero): ");
					type = scanner.nextInt();
					if (scanner.hasNextLine()) scanner.nextLine(); // clean buffer
				}
				
				Integer weaponID = -1;
				while(weaponID < 0 || weaponID > 3){
					System.out.print("Elige un arma (1: Espada, 2: Lanza, 3: Claymore): ");
					weaponID = scanner.nextInt();
					if (scanner.hasNextLine()) scanner.nextLine(); // clean buffer
				}
				
				Weapon weapon;
				if (weaponID == 1) { weapon = new Sword(); }
				else if (weaponID == 2) { weapon = new Spear(); }
				else { weapon = new Claymore(); } // weaponID == 3
				
				if (type == 1) { players.add(new Warrior(name, weapon)); }
				else if (type == 2) { players.add(new Healer(name, weapon)); }
				else { players.add(new Prisoner(name, weapon)); } // type == 3
			}
			else if (opt == 2) {
				Integer playersLeft = DEF_PLAYERS - players.size();
				List<Player> bots = Bots.getBots(playersLeft);
				for (Player bot: bots) players.add(bot);
			}
			else {
				System.out.println("Elige una opcion válida");
			}
		}
		
		System.out.println("PERSONAJES ========================================================================");
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i));
		}
		System.out.println("===================================================================================");
		
		return players;
	}
}