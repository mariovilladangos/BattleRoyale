package com.utad.poo.battleroyale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Bots {
	public static void main(String[] args){
		
	}
	
	public static void ReadBotFiles() throws Exception {
		File currentDir = new File(System.getProperty("user.dir"));
		File ejemplo1File = new File(currentDir.getCanonicalPath() + "\\files\\BotPresets");
		
		BufferedReader br = new BufferedReader(new FileReader(ejemplo1File));
		String lineaDeFichero;
		Integer numeroLineas = 0;
		Integer numeroPalabras = 0;
		
		while((lineaDeFichero = br.readLine()) != null) {
			String[] palabras = lineaDeFichero.split(";");
			numeroPalabras += palabras.length;
			numeroLineas++;
		}
		
		br.close();
		System.out.println("El fichero " + ejemplo1File.getName() + " tiene: ");
		System.out.printf("(%d) líneas, con (%d) palabras", numeroLineas, numeroPalabras);
	}
}