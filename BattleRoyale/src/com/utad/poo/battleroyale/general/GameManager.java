package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.utad.poo.battleroyale.ui.CharacterMenu;



public class GameManager {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        Boolean endgame = false;
        Integer day=0;
        List<Player> players = new ArrayList<Player>();
        List<Player> eliminated = new ArrayList<Player>();
        Random randomNumber = new Random();

        CharacterMenu menu = VisualLobby.fillLobby();
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
			//PASA AL SIGUIENTE DIA (empieza en el uno)
			day++;
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
    
    
   
    
    
    
    
    
}
        	//dia++
        	//dia n se volvera a hacer este bucle hasta que solo quede 1 


			//IMPORTANTE EL CODIGO DE ABAJO COGERLO CON PINZAS AUN PARA LO DE ARRIBA 
			
            // Fase de combate
            /*
            for (int i = 0; i < jugadoresVivos.size(); i++) {
                for (int j = i + 1; j < jugadoresVivos.size(); j++) {
                    Player atacante = jugadoresVivos.get(i);
                    Player defensor = jugadoresVivos.get(j);
                    atacante.combatir(defensor);

                    // Eliminar jugadores si quedan sin vida
                    if (!atacante.estaVivo()) eliminados.add(atacante);
                    if (!defensor.estaVivo()) eliminados.add(defensor);
                }
            }
            jugadoresVivos.removeAll(eliminados);

            // Comprobar condición de fin de partida
            if (jugadoresVivos.size() <= 1) {
                finPartida = true;
            }

        } while (!finPartida);

        // Resultado final
        if (jugadoresVivos.size() == 1) {
            System.out.println("¡El ganador es: " + jugadoresVivos.get(0).getName() + "!");
        } else {
            System.out.println("¡No hay ganadores!");
        }
    }*/






