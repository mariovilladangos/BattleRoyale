package com.utad.poo.battleroyale.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static void main(String[] args) {
        List<Player> jugadoresVivos = new ArrayList<>();
        boolean finPartida = false;

        // Inicialización de los player
        jugadoresVivos.add(new Warrior("Warrior1", new Weapon("Sword")));
        jugadoresVivos.add(new Wretch("Wretch1", new Weapon("Sword")));
        jugadoresVivos.add(new Prisoner("Prisoner1", new Weapon("Sword")));

        do {
            // Cada jugador coge loot, definir método de lootear?
            for (Player jugador : jugadoresVivos) {
                jugador.lootear();
                jugador.setWeapon(jugadorLootearWeapon());
            }

            // Fase de combate entre jugadores, queda definir método combatir?
            List<Player> eliminados = new ArrayList<>();
            for (int i = 0; i < jugadoresVivos.size(); i++) {
                for (int j = i + 1; j < jugadoresVivos.size(); j++) {
                    Player atacante = jugadoresVivos.get(i);
                    Player defensor = jugadoresVivos.get(j);
                    atacante.combatir(defensor);

                    // Eliminar jugadores si se quedan sin vida, falta método que compruebe si no le queda vida
                    if (!atacante.estaVivo()) eliminados.add(atacante);
                    if (!defensor.estaVivo()) eliminados.add(defensor);
                }
            }
            jugadoresVivos.removeAll(eliminados);

            // Comprobar si ha acabado la partida y cierra bucle
            if (jugadoresVivos.size() <= 1) {
                finPartida = true;
            }

        } while (!finPartida);

        // Resultado final de la partida con el ganador o si hay empate
        if (jugadoresVivos.size() == 1) {
            System.out.println("El ganador es: " + jugadoresVivos.get(0).getName());
        } else {
            System.out.println("No hay ganadores");
        }
    }

//Metodo de lootear provisional???????
    private static Weapon jugadorLootearWeapon() {
        String[] armas = { "Sword", "Spear", "Claymore" };
        Random rand = new Random();
        String armaAleatoria = armas[rand.nextInt(armas.length)];
        return new Weapon(armaAleatoria);
    }
}





