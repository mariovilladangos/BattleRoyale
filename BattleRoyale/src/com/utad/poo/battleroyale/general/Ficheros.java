package com.utad.poo.battleroyale.general;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.utad.poo.battleroyale.weapons.*;
import com.utad.poo.battleroyale.players.*;

import java.util.ArrayList;
import java.util.Collections;

public class Ficheros {
	
	private static List<Player> ReadBotFiles() throws Exception {
				
		List<Player> allBots = new ArrayList();
		File currentDir = new File(System.getProperty("user.dir"));
		File ejemplo1File = new File(currentDir.getCanonicalPath() + "\\files\\BotPresets");
		
		BufferedReader br = new BufferedReader(new FileReader(ejemplo1File));
		String lineaDeFichero;
		
		while((lineaDeFichero = br.readLine()) != null) {
			String[] atributos = lineaDeFichero.split(";");
			
			Weapon weapon;
			if (atributos[2].equals("Sword")) weapon = new Sword();
			else if (atributos[2].equals("Spear")) weapon = new Spear();
			else weapon = new Claymore();
			
			Player bot;
			if (atributos[1].equals("Warrior")) bot = new Warrior(atributos[0], weapon);
			else if (atributos[1].equals("Healer")) bot = new Healer(atributos[0], weapon);
			else bot = new Prisoner(atributos[0], weapon);
			
			allBots.add(bot);
		}
		
		br.close();
		return allBots;
	}
	
	public static List<Player> getBots(List<Player> players, Integer n){
		
		List<Player> allBots = null;
		try {
			allBots = Ficheros.ReadBotFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (allBots != null) {
			List<Player> bots = new ArrayList();
			
			for (int i = 0; i < n; i++) {
				Random rand = new Random();
		        Integer index = rand.nextInt(allBots.size());
		        
		        Boolean found = false;
		        while(!found) {
		        	found = true;
		        	for (Player bot: bots) {
			        	if (bot.getName().toUpperCase().equals(allBots.get(index).getName().toUpperCase())) {
			        		index++;
			        		if (index >= allBots.size()) index = 0;
			        		found = false;
			        		break;
			        	}
			        }
		        	if (found == true) for (Player player: players) {
			        	if (player.getName().toUpperCase().equals(allBots.get(index).getName().toUpperCase())) {
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
		else return null;
	}
	
	
	
	public static void saveStats(List<Player> players, Integer day) {
    	try {
			statsLogAdd(players, day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    private static void statsLogAdd(List<Player> players, Integer day) throws Exception{
        String nombreArchivo = "statsLog.txt";  // Nombre del archivo

        File currentDir = new File(System.getProperty("user.dir"));
		File statsLog = new File(currentDir.getCanonicalPath() + "\\files\\statsLog");
        
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(statsLog, true))) {
    
    		writer.write("- Día " + (day) + "\n");
            for (Player player : players) {
            	writer.write("---------------------------------------------------------------------------------\n");
                writer.write(" -  " + player.getName() + "\n");
                writer.write("    Salud restante: " + player.getHp() + "\n");
                writer.write("    Clase: " + player.getClassType() + "\n");
                writer.write("    Arma: " + player.getWeaponType() + "\n");
                writer.write("       Nivel del arma: " + player.getWeapon().getLevel()+"\n");
                writer.write("       Daño del arma: " + player.getWeapon().getDamage()[player.getWeapon().getLevel()-1]+"\n");
                writer.write("---------------------------------------------------------------------------------\n");
            }  
            System.out.println("Las estadísticas han sido guardadas en '" + nombreArchivo + "'");
    	}catch (IOException e) {
    		System.out.println("Error al guardar las estadísticas: " + e.getMessage());
    	}
    }

    
    
    public static void saveActions(List<String> log, String fileName) {
    	try { ActionsLogAdd(log, fileName); }
    	catch (Exception e) { e.printStackTrace(); }
    }
    
	private static void ActionsLogAdd(List<String> log, String fileName) throws Exception {
	    String nombreArchivo = "Actions.txt";
	    
	    File currentDir = new File(System.getProperty("user.dir"));
	    File actionsLog = new File(currentDir.getCanonicalPath() + "\\files\\" + fileName + ".txt");

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(actionsLog, false))) {
	        for (String line: log) writer.write(line + "\n");
	        
	    } catch (IOException e) {
	        System.out.println("Error al guardar las acciones: " + e.getMessage());
	    }
	}
	
}