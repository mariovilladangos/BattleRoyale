package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) throws Exception {
       
        boolean endgame = false;
        Integer day=0;
        List<Player> players = new ArrayList<Player>();
        List<Player> eliminated = new ArrayList<Player>();
        Random randomNumber = new Random();
        // La inicializacion de jugadores esta hecha en el lobby 

        //Iniciamos la creacion de los jugadores
        players = Lobby.fillLobby();
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
        		if(players.size() %2 != 0) {
        			//Si es impar, el jugador que queda como impar puede autoinflingirse daño
        			players.get(length).autoDamage();
        			length-=1;
        		}
        		for(int i=0;i<length; i+=2) {
    				//Generamos una semilla distinta para cada iteracion del bucle
    				Random lucha = new Random();
    				Integer luchaONo = lucha.nextInt(100);
    				//Si es menor o igual a 50, se lucha
    				if(luchaONo <= 50) {
    					players.get(i).combat(players.get(i+1));
    				}
    			}
        	for(Player player:players) {
        		// Eliminamos de la lista de vivos aquellos jugadores con vida menor o igual a cero
        		if(player.getHp() <=0) {
        			players.remove(player);
        			eliminated.add(player);
        		}
        	}
        	System.out.println("Jugadores vivos: "+players.get(length));
        	System.out.println("Pulsa cualquier tecla para ir al día siguiente: ");
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






