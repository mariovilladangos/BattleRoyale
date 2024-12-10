package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) {
        List<Player> jugadoresVivos = new ArrayList<Player>();
        boolean finPartida = false;
        private Integer day=0;

        // Inicialización dejugadores
        jugadoresVivos.add(new Warrior("Warrior1", new Sword()));

        do {
        	
        	//dia:
            // Cada jugador realiza su acción de lootear
        	//tarde:
            //Cada jugador pelea aleatoriamente con otro
        	//el que gane obteendra una mejora en el arma de 1 
        	//el que pierda sera eliminado
        	//dia++
        	//dia n se volvera a hacer este bucle hasta que solo quede 1 

            // Fase de combate
            List<Player> eliminados = new ArrayList<>();
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
    }

}





