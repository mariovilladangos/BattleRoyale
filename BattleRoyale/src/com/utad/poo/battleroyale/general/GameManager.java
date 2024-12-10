package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) {
        List<Player> jugadoresVivos = new ArrayList<>();
        boolean finPartida = false;

        // Inicialización dejugadores
        jugadoresVivos.add(new Warrior("Warrior1", new Sword()));
        jugadoresVivos.add(new Healer("Healer1", new Spear()));
        jugadoresVivos.add(new Prisoner("Prisoner1", new Claymore()));

        do {
            // Cada jugador realiza su acción de lootear
            for (Player jugador : jugadoresVivos) {
                lootear(jugador);
            }

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

    // Método para que un jugador 'lootee' algo durante su turno
    private static void lootear(Player jugador) {
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);

        int curaProbabilidad = 25;
        int mejoraProbabilidad = 25;
        int nadaProbabilidad = 50;

        // Ajusta las probabilidades para cada clase
        if (jugador instanceof Healer) {
            curaProbabilidad = 50;
        } else if (jugador instanceof Warrior) {
            mejoraProbabilidad = 50;
        }

        if (probabilidad < curaProbabilidad) {
            jugador.recibirCuracion(); // Implementar este método
        } else if (probabilidad < curaProbabilidad + mejoraProbabilidad) {
            jugador.mejorarArma(); // Método a implementar
        }
        // Si no cae en ninguna de las anteriores, no pasa nada
    }
}





