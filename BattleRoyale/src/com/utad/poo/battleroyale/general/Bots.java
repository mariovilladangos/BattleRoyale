package com.utad.poo.battleroyale.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Bots {
	
	private static List<Player> ReadBotFiles() throws Exception {
				
		List<Player> allBots = new ArrayList();
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
			
			allBots.add(bot);
		}
		
		br.close();
		return allBots;
	}
	
	public static List<Player> getBots(Integer n) throws Exception {
		
		List<Player> allBots = Bots.ReadBotFiles();
		List<Player> bots = new ArrayList();
		
		for (int i = 0; i < n; i++) {
			Random rand = new Random();
	        Integer index = rand.nextInt(allBots.size());
	        
	        Boolean found = false;
	        while(!found) {
	        	found = true;
	        	for (Player bot: bots) {
		        	if (bot.getName().equals(allBots.get(index).getName())) {
		        		index++;
		        		if (index >= allBots.size()) index = 0;
		        		found = false;
		        		break;
		        	}
		        }
	        }

	        bots.add(allBots.get(index));
		}
		
		return bots;
	}
}