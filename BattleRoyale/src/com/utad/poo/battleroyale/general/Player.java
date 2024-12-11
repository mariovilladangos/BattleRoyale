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
	
	public String getWeaponType() {
		return weapon.getWeaponType();
	}
	
	public String getClassType() {
		return "[-]";
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
            weapon.upgrade(this.getName()); // Método a implementar
        }
        // Si no cae en ninguna de las anteriores, no pasa nada
    }
    
    
    public void heal() {
    	Integer heal = 50;
    	System.out.println("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida");
    	System.out.println("  ❤️‍🩹 " + this.getName() + " recupera " + heal + "ps");
    	this.hp += heal;
    }
    
    public void combat(Player enemigo) {
    	Integer damage=this.weapon.getDamage()[this.weapon.getLevel()-1];
    	Integer enemyDamage=enemigo.getWeapon().getDamage()[this.weapon.getLevel()-1];
    	Boolean activeCombat = true;
    	
    	System.out.println("\n" + this.getName().toUpperCase() + " ⚔️ " + enemigo.getName().toUpperCase());
    	while (activeCombat){
	    	System.out.println("  → " + this.getName() + " ataca a " + enemigo.getName() + " causandole " + damage + "hp de daño");
	    	enemigo.hp -= damage;
	    	
	    	if(enemigo.hp <= 0) {
	        	System.out.println("  💀 " + enemigo.getName() + " ha sido eliminado");
	        	activeCombat = false;
	    	}else{
	    		System.out.println("  → " + enemigo.getName() + " ataca a " + this.getName() + " causandole " + enemyDamage + "hp de daño");
		    	this.hp -= enemyDamage;
	    		
		    	if(this.hp <= 0) {
		    		System.out.println("  💀 " + this.getName() + " ha sido eliminado");
		    		activeCombat = false;
		    	}
	    	}
    	}
    	// retornar el eliminado y eliminarlo de la lista
    	// mario te quiero
    }
    
    public void autoDamage() {
    	//Cada vez que se llama a la funcion, se genera una nueva probabilidad
    	Random seed = new Random();
    	Integer probAutoDamage = seed.nextInt(100);
    	//Si la probabilidad es menor o igual a 50, 
    	// el jugador se hace daño a sí mismo
    	if(probAutoDamage < 50) {
    		System.out.println("El jugador " + this.getName() + "se ha clavado su " + this.weapon.getWeaponType()+ " :| " +
    							" y ha perdido: " + this.weapon.getDamage()[this.weapon.getLevel() - 1] + "puntos de vida");
    		this.hp -= this.weapon.getDamage()[this.weapon.getLevel()-1];
    	}
    }

    
    
    
    
    
    
    
    
    
    
}