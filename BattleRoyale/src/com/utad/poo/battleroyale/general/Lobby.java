package com.utad.poo.battleroyale.general;

import java.util.Scanner;

public class Lobby {
	private final static Integer DEF_PLAYERS = 10;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Integer players = 0;
		while (players < DEF_PLAYERS) {
			
			System.out.print("Quieres crear un personaje (1) o autorellenar con bots (2): ");
			int scanner.nextInt();
			
		}
		
	}
}