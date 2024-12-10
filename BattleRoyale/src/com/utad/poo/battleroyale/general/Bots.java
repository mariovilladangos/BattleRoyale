package com.utad.poo.battleroyale.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class Bots {
	
	public List<Player> ReadBotFiles() throws Exception {
				
		List<Player> AllBots = new ArrayList();
		File currentDir = new File(System.getProperty("user.dir"));
		File ejemplo1File = new File(currentDir.getCanonicalPath() + "\\files\\BotPresets");
		
		BufferedReader br = new BufferedReader(new FileReader(ejemplo1File));
		String lineaDeFichero;
		
		while((lineaDeFichero = br.readLine()) != null) {
			String[] atributos = lineaDeFichero.split(";");
			
			Weapon weapon;
			if (atributos[2] == "Sword") weapon = new Sword();
			else if (atributos[2] == "Spear") weapon = new Spear();
			else weapon = new Claymore();
			
			Player bot;
			if (atributos[1] == "Warrior") bot = new Warrior(atributos[0], weapon);
			else if (atributos[1] == "Healer") bot = new Healer(atributos[0], weapon);
			else  bot = new Prisoner(atributos[0], weapon);
			
			AllBots.add(bot);
		}
		
		br.close();
		return AllBots;
	}
	
	public List<Player> GetBots(Integer n) {
		
		List<Player> bots = new ArrayList();
		
		for (int i = 0; i < n; i++) {
			
		}
		
		return bots;
	}
}