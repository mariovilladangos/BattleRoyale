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
	
	
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
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
    
    public void combat(Player enemigo) {
    	Integer damage=this.weapon.getDamage()[this.weapon.getLevel()-1];
    	Integer enemyDamage=enemigo.getWeapon().getDamage()[this.weapon.getLevel()-1];
    	System.out.println("Jugador: "+this.getName()+ " ataca a: "+ enemigo.getName() + "causandole: "+ damage+ "hp de daño");
    	enemigo.hp-=damage;
    	System.out.println("Enemigo: "+ enemigo.getName() + " ataca a: "+ this.getName() + "causandole: "+ enemyDamage+ "hp de daño");
    	if(enemigo.hp<0) {
        	System.out.println("Jugador: "+ enemigo.getName() + "ha sido eliminado ");

    	}else if(this.hp<0) {
    		System.out.println("Jugador: "+ this.getName() + "ha sido eliminado ");
    	}
    	
    	// retornar el eliminado y eliminarlo de la lista
    }

    
    
    
    
    
    
    
    
    
    
}