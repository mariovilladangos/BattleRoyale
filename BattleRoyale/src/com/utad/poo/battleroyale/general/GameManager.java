package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) throws Exception {
       
        boolean endgame = false;
        Integer day=0;
        List<Player> players = new ArrayList<Player>();
        List<Player> eliminados = new ArrayList<Player>();
        Random randomNumber = new Random();
        // La inicializacion de jugadores esta hecha en el lobby 

        //Iniciamos la creacion de los jugadores
        Lobby.fillLobby();
        	do{
        		//PASA AL SIGUIENTE DIA (empieza en el uno)
        		day++;
        		
        		// PRIMERA PARTE DEL DIA        		
            // Cada jugador realiza su acción de lootear
        	
        	for(Player player:players) {
        		player.lootear();
        	}
        		// SEGUNDA PARTE DEL DIA:
        	
        	// Los jugadores pueden (o no) encontrarse y luchar.
        	
        	for(Player player:players) {
        		// AQUI DENTRO LOS COMBATES QUE SON ALEATORIOS ENTRE LOS JUGADORES
        	}
        	
        	// utiliar el collections.shufle para randomiar la lista y asi poder hacer los combates
        	//Cada jugador pelea aleatoriamente con otro
        	//el que gane obteendra una mejora en el arma de 1 
        	//el que pierda sera eliminado
        	
        	
        	
        	}while(!endgame);
        	System.out.println("#1 Victory Royale jugador:" + players.get(0).getName());
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






