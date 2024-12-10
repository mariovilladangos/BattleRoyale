package com.utad.poo.battleroyale.general;

//import java.util.Arrays;
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
		return "Player [name=" + name + ", weapon=" + weapon + ", hp=" + hp + " ";
	}
	
	 
    public void lootear() {
    	
    	//Generamos un numero aleatorio
    	Random rand = new Random();
        Integer probabilidad = rand.nextInt(100);
    	
        if (probabilidad < probabilidades[0]) {
            this.heal(); // Implementar este método
        } else if (probabilidad < probabilidades[0] + probabilidades[1]) {
            weapon.upgrade(); // Método a implementar
        }
        // Si no cae en ninguna de las anteriores, no pasa nada
    }
    
    
    public void heal() {
    	this.hp+=40;
    }

}