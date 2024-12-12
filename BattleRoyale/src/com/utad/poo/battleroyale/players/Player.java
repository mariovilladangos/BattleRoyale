package com.utad.poo.battleroyale.players;

import java.util.List;
//import java.util.Arrays;
import java.util.Random;

import com.utad.poo.battleroyale.general.GameManager2;
import com.utad.poo.battleroyale.ui.*;
import com.utad.poo.battleroyale.weapons.Weapon;

public class Player {
	
	protected String name;
	protected Weapon weapon;
	protected Integer hp;
	protected Integer[] probabilidades;
	public static final String DEF_NAME="Player";
	public static final Integer[] DEF_PROB = {25,25,50};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	private Integer kills = 0;
	private Integer damageDeal = 0;
	private Integer damageReceived = 0;
	private Integer hpRestored = 0;
	private Integer incidentsOcurred = 0;
	
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
    public Integer lootear(GameMenu game, List<Player> players, Integer action) {
    	
    	Random rand = new Random();
        Integer probabilidad = rand.nextInt(100);
    	
        if (probabilidad < probabilidades[0]) {
        	action = GameManager2.wait(game, players, action, 1);
            this.heal(game);
            game.addTerminalLine(" ");
        } else if (probabilidad < probabilidades[0] + probabilidades[1]) {
        	action = GameManager2.wait(game, players, action, 1);
            weapon.upgrade(game, this.getName());
            game.addTerminalLine(" ");
        }
        
        return action;
    }
    
    
    public void heal() {
    	Random probi = new Random();
		Integer chanci = probi.nextInt(4);
		Integer heal = 0;
			if(chanci == 0) {
				heal=20;
				System.out.println("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida diminuta");
				System.out.println("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 1) {
				heal=30;
				System.out.println("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida pequeña");
				System.out.println("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 2) {
				heal=40;
				System.out.println("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida mediana");
				System.out.println("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 3) {
				heal=50;
				System.out.println("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida grande");
				System.out.println("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}
			
    	this.hp += heal;
    }
    public void heal(GameMenu game) {
    	Random probi = new Random();
		Integer chanci = probi.nextInt(5);
		Integer heal = 0;
			if(chanci == 0) {
				heal=20;
				game.addTerminalLine("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida diminuta");
		    	game.addTerminalLine("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 1) {
				heal=30;
				game.addTerminalLine("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida pequeña");
		    	game.addTerminalLine("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 2) {
				heal=40;
				game.addTerminalLine("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida mediana");
		    	game.addTerminalLine("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 3) {
				heal=50;
				game.addTerminalLine("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida grande");
		    	game.addTerminalLine("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}else if(chanci == 4) {
				heal=70;
				game.addTerminalLine("\n" + this.getName().toUpperCase() + " ha encontrado una pocion de vida gigante");
		    	game.addTerminalLine("  ❤️‍ " + this.getName() + " recupera " + heal + "ps");
			}
			
    	this.hp += heal;
    	this.addHpRestored(heal);
    }
    
    public void combat(Player enemy) {
    	Integer damage=this.weapon.getDamage()[this.weapon.getLevel()-1];
    	Integer enemyDamage=enemy.getWeapon().getDamage()[enemy.weapon.getLevel() - 1];
    	Boolean activeCombat = true;
    	
    	System.out.println("\n" + this.getName().toUpperCase() + " ⚔️ " + enemy.getName().toUpperCase());
    	while (activeCombat){
	    	System.out.println("  → " + this.getName() + " ataca a " + enemy.getName() + " causandole " + damage + "hp de daño");
	    	enemy.hp -= damage;
	    	
	    	if(enemy.hp <= 0) {
	        	System.out.println("  💀 " + enemy.getName() + " ha sido eliminado");
	        	activeCombat = false;
	    	}else{
	    		System.out.println("  → " + enemy.getName() + " ataca a " + this.getName() + " causandole " + enemyDamage + "hp de daño");
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
    public int combat(GameMenu game, Integer action, List<Player> players, Player enemy) {
    	Integer damage=this.weapon.getDamage()[this.weapon.getLevel()-1];
    	Integer enemyDamage=enemy.getWeapon().getDamage()[this.weapon.getLevel()-1];
    	Boolean activeCombat = true;
    	
    	game.addTerminalLine("\n" + this.getName().toUpperCase() + " ⚔️ " + enemy.getName().toUpperCase());
		
    	while (activeCombat){
    		action = GameManager2.wait(game, players, action, 1);
    		if (action <= 1) game.addTerminalLine("  → " + this.getName() + " ataca a " + enemy.getName() + " causandole " + damage + "puntos de daño");
    		enemy.hp -= damage;
    		this.addDamageDeal(damage);
    		enemy.addDamageReceived(damage);
	    	
	    	if(enemy.hp <= 0) {
	    		game.addTerminalLine("  💀 " + enemy.getName() + " ha sido eliminado");
	    		this.addKills();
	        	activeCombat = false;
	    	}else{
	    		action = GameManager2.wait(game, players, action, 1);
	    		if (action <= 1) game.addTerminalLine("  → " + enemy.getName() + " ataca a " + this.getName() + " causandole " + enemyDamage + "puntos de daño");
		    	this.hp -= enemyDamage;
	    		enemy.addDamageDeal(enemyDamage);
	    		this.addDamageReceived(enemyDamage);
	    		
		    	if(this.hp <= 0) {
		    		game.addTerminalLine("  💀 " + this.getName() + " ha sido eliminado");
		    		enemy.addKills();
		    		activeCombat = false;
		    	}
	    	}
    	}
	    game.addTerminalLine(" ");
    	return action;
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
    		System.out.println( this.getName() + " se ha clavado su " + this.weapon.getWeaponType()+ ":(" +
    							" y ha perdido: " + this.weapon.getDamage()[this.weapon.getLevel() - 1] + " puntos de vida");
    		this.hp -= this.weapon.getDamage()[this.weapon.getLevel()-1];
    	}
    }
    public void autoDamage(GameMenu game) {
    	//Cada vez que se llama a la funcion, se genera una nueva probabilidad
    	Random seed = new Random();
    	Integer probAutoDamage = seed.nextInt(100);
    	//Si la probabilidad es menor o igual a 50, 
    	// el jugador se hace daño a sí mismo
    	if(probAutoDamage < 50) {
    		Integer damage = 0;
    		Random prob = new Random();
    		Integer chance = prob.nextInt(4);
    			if(chance == 0) {
    			game.addTerminalLine(this.getName() + " se ha clavado su " + this.weapon.getWeaponType() +
    							" y ha perdido: " + this.weapon.getDamage()[this.weapon.getLevel() - 1] + "ps");
    			damage=this.weapon.getDamage()[this.weapon.getLevel()-1];
    			}else if(chance == 1) {
    				game.addTerminalLine(this.getName() + " se ha cegado mirando al sol y se ha tropezado perdiendo: 20ps");
    				damage=20;
    			}else if(chance == 2) {
    				game.addTerminalLine(this.getName() + " ha sido atacado de la nada por un dragón, logrando escapar, pero ha escapado perdiendo: 70ps");
    				damage=70;
    			}else if(chance == 3) {
    				game.addTerminalLine("A " + this.getName() + " le ha dado un chungo, perdiendo la mitad de sus puntos de vida: " + this.getHp()/2 + "ps");
    				damage=this.getHp()/2;
    			}
    			    			
    			this.hp -= damage;
    			this.addIncidentsOcurred();
    			this.addDamageReceived(damage);
    			
    			if(this.getHp()<=0) {
    				game.addTerminalLine("  💀 Lamentablemente " + this.getName() + "no lo logró ");
    			}
    			game.addTerminalLine(" ");
    	}
    }
    
    public void showStats() {
    	System.out.println("---------------------------------------------------------------------------------");
    	System.out.println(" - " + this.name);
    	System.out.println("    Salud restante: " + this.getHp());
    	System.out.println("    Clase: " + this.getClassType());
    	System.out.println("    -Arma: "+ this.getWeaponType());
    	System.out.println("       Nivel del arma: "+this.weapon.getLevel());
    	System.out.println("       Daño del arma: "+this.weapon.getDamage()[this.weapon.getLevel()-1]);
    	System.out.println("---------------------------------------------------------------------------------");
    }
    public void showStats(GameMenu game) {
    	game.addStatsLine(this.name + "[(" + this.getClassType() + " | " + this.getHp() + "ps) : ("
    		+ this.getWeaponType() + " Lvl." + this.weapon.getLevel() + " | DMG:" + this.weapon.getDamage()[this.weapon.getLevel()-1] + ")]");
    }
    
    
    
    
    // GETTER / ADDERS :v
	public Integer getKills() {
		return kills;
	}
	public void addKills() {
		this.kills++;
	}
	public void addKills(Integer kills) {
		this.kills += kills;
	}
	
	public Integer getDamageDeal() {
		return damageDeal;
	}
	public void addDamageDeal(Integer damageDeal) {
		this.damageDeal += damageDeal;
	}
	
	public Integer getDamageReceived() {
		return damageReceived;
	}
	public void addDamageReceived(Integer damageReceived) {
		this.damageReceived += damageReceived;
	}
	
	public Integer getHpRestored() {
		return hpRestored;
	}
	public void addHpRestored(Integer hpRestored) {
		this.hpRestored += hpRestored;
	}
	
	public Integer getIncidentsOcurred() {
		return incidentsOcurred;
	}
	public void addIncidentsOcurred() {
		this.incidentsOcurred++;
	}
	public void addIncidentsOcurred(Integer incidentsOcurred) {
		this.incidentsOcurred += incidentsOcurred;
	}
}