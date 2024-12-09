package com.utad.poo.battleroyale.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Bots {
	
	public void ReadBotFiles() throws Exception {
		File currentDir = new File(System.getProperty("user.dir"));
		File ejemplo1File = new File(currentDir.getCanonicalPath() + "\\files\\BotPresets");
		
		BufferedReader br = new BufferedReader(new FileReader(ejemplo1File));
		String lineaDeFichero;
		
		while((lineaDeFichero = br.readLine()) != null) {
			String[] atributos = lineaDeFichero.split(";");
			// Añadir atributos a una Lista<Player> que seran todos los bots
		}
		
		br.close();
	}
}