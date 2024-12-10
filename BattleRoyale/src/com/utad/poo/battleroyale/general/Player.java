package com.utad.poo.battleroyale.general;

import java.util.Random;

public class Player {
	
	protected String name;
	protected Weapon weapon;
	protected Integer hp;
	protected Integer[] probabilidades;
	public static final String DEF_NAME="Player";
	public static final Integer[] DEF_PROB = {25,25,50};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	//public static final Weapon DEF_WEAPON;
	public Player(String name,Weapon weapon, Integer hp){
		this(name, weapon, hp, DEF_PROB);
	}
	public Player(String name,Weapon weapon, Integer hp, Integer[] probabilidades){
		
		this.name=name;
		this.weapon=weapon;
		this.hp = hp;
		this.probabilidades = probabilidades.clone();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public String toString() {
		return "Player [ Name=" + this.name + ", Weapon=" + this.weapon + " ]";
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
    // Método par que un jugador consiga objetos durante su turno
    private static void lootear() {
    	Random rand = new Random();
    	//Generamos un número del 1 al 100 (una probabilidad)
    	Integer probabilidad = rand.nextInt(100);
    	//
    	if(jugador instanceof Healer) {
    		Integer curaProbabilidad = 50;
    		Integer mejoraProbabilidad = 25;
    		Integer nadaProbabilidad = 25;
    	}
    	if(jugador instanceof Warrior) {
    		Integer curaProbabilidad = 25;
    		Integer mejoraProbabilidad = 50;
    		Integer nadaProbabilidad = 25;
    	}
    	if()
    }

}
