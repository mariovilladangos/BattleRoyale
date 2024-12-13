package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.utad.poo.battleroyale.ui.CharacterMenu;
import com.utad.poo.battleroyale.players.Player;



public class GameManager {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        Boolean endgame = false;
        Integer day=0;
        List<Player> players = new ArrayList<Player>();
        List<Player> eliminated = new ArrayList<Player>();
        Random randomNumber = new Random();

        CharacterMenu menu = lobby();
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
		
		
		// La inicializacion de jugadores esta hecha en el lobby 
		//Iniciamos la creacion de los jugadores
		players = totalPlayers;
		
    	do{
    		day++;
    		saveStats(players, day);
    		saveActions(players,eliminated,day);
			//PASA AL SIGUIENTE DIA (empieza en el uno)
			
			System.out.println("Día " + day);
			// PRIMERA PARTE DEL DIA        		
		    // Cada jugador realiza su acción de lootear
			
			for(Player player:players) {
				player.lootear();
			}
			
    		// SEGUNDA PARTE DEL DIA:
			//Mezclamos el orden de los jugadores para ver quiénes se enfrentan.
			// Los jugadores pueden (o no) encontrarse y luchar.
    		Collections.shuffle(players);
    		//Comprobamos si la lista de jugadores vivos es par o impar
    		Integer length = players.size();
    		if(length % 2 != 0) {
    			//Si es impar, el jugador que queda como impar puede autoinflingirse daño
    			length -= 1;
    			players.get(length).autoDamage();
    		}
    		
    		for(int i=0;i<length; i+=2) {
				//Generamos una semilla distinta para cada iteracion del bucle
				Random rand = new Random();
				Integer probLucha = rand.nextInt(100);
				//Si es menor o igual a 50, se lucha
				if(probLucha <= 50) {
					players.get(i).combat(players.get(i+1));
				}
			}
    		
    		List<Player> deadPlayers = new ArrayList();
    		for(Player player : players) {
    			// Eliminamos de la lista de vivos aquellos jugadores con vida menor o igual a cero
    			if(player.getHp() <= 0) {
    				deadPlayers.add(player);
    			}
    		}
    		
	    	players.removeAll(deadPlayers);
	    	eliminated.addAll(deadPlayers);
	    	deadPlayers.clear();
	    	
	    	if (players.size() <= 1) {
	    		endgame = true;
	    		
	    	}
	    	else {
		    	System.out.println("Jugadores vivos: " + players.size());
		    	System.out.println("Pulsa la tecla 's' para ver las estadísticas e ir al día siguiente: ");
		    	System.out.println("Pulsa ENTER para ir al día siguiente: ");
		    	String pulsaTecla = scanner.nextLine();
		    	if(pulsaTecla.equals("s")) {
		    		for(Player player:players) {
		    			player.showStats();
		    		}
		    	}
		    	// utiliar el collections.shufle para randomiar la lista y asi poder hacer los combates
		    	//Cada jugador pelea aleatoriamente con otro
		    	//el que gane obteendra una mejora en el arma de 1 
		    	//el que pierda sera eliminado
	    	}
    	
    	}while(!endgame);
    	
		System.out.println("\n🥇 Victory Royale jugador:" + players.get(0).getName());
		for (int i = eliminated.size() - 1; i >= 0; i--) {
			System.out.println("  #" + (CharacterMenu.NPLAYERS - i) + " " + eliminated.get(i).getName());
		}
		System.out.println("Pulsa ENTER para cerrar");
    	String pulsaTecla = scanner.nextLine();
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
    // preguntar mario no se registran todas las acciones(eliminados y demas ) bien si hay mas de un combate 
    // ns porque en el dia 0 ya muere 1 xdd
    private static void ActionsLogAdd(List<Player> players, List<Player> eliminated, Integer day) throws Exception {
        String nombreArchivo = "Actions.txt";  // Nombre del archivo

        File currentDir = new File(System.getProperty("user.dir"));
        File actionsLog = new File(currentDir.getCanonicalPath() + "\\files\\ActionsLog.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(actionsLog, true))) {
            writer.write("- Día " + day + "\n");
            writer.write("Registro de Acciones del Juego en el día\n");
            writer.write("--------------------------------\n");

            // Mezclamos el orden de los jugadores
            Collections.shuffle(players);
            Integer length = players.size();

            // Si el número de jugadores es impar, el último se autoinflinge daño
            if (length % 2 != 0) {
                Player oddPlayer = players.get(length - 1);
                oddPlayer.autoDamage();
                writer.write("- " + oddPlayer.getName() + " se autoinfligió daño. Salud restante: " + oddPlayer.getHp() + "\n");
                length -= 1;
            }

            // Generamos combates entre pares de jugadores
            for (int i = 0; i < length; i += 2) {
                Random rand = new Random();
                Integer probLucha = rand.nextInt(100);

                Player player = players.get(i);
                Player enemy = players.get(i + 1);

                if (probLucha <= 50) {
                    writer.write("- Combate entre " + player.getName() + " y " + enemy.getName() + "\n");
                    player.combat(enemy);
                    if (player.getHp() <= 0) {
                        eliminated.add(player);
                        writer.write("  -> " + player.getName() + " ha sido eliminado.\n");
                    }
                    if (enemy.getHp() <= 0) {
                        eliminated.add(enemy);
                        writer.write("  -> " + enemy.getName() + " ha sido eliminado.\n");
                    }
                } else {
                    writer.write("- " + player.getName() + " y " + enemy.getName() + " no se encontraron.\n");
                }
            }

            writer.write("--------------------------------\n");
            System.out.println("Las acciones han sido registradas en '" + nombreArchivo + "'");
        } catch (IOException e) {
            System.out.println("Error al guardar las acciones: " + e.getMessage());
        }
    }
    
    public static void saveActions(List<Player> players,List<Player> eliminated, Integer day) {
    	try {
    		ActionsLogAdd(players,eliminated,day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static CharacterMenu lobby(){
		CharacterMenu menu = new CharacterMenu();
		while(!menu.isLobbyReady()) {
			System.out.print("");
		}
		menu.hide();
		
		return menu;
	}
}
      