package com.utad.poo.battleroyale.players;

import java.util.List;
import java.util.Random;

import com.utad.poo.battleroyale.general.GameManagerUI;
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
	
    public Integer[] lootear(GameMenu game, List<Player> players, Integer action) {
    	
    	Integer resultado = 0;
    	
    	Random rand = new Random();
        Integer probabilidad = rand.nextInt(100);
    	
        if (probabilidad < probabilidades[0]) {
        	action = GameManagerUI.wait(game, players, action, 1);
            this.heal(game);
            game.addTerminalLine(" ");
            resultado = 1;
        } else if (probabilidad < probabilidades[0] + probabilidades[1]) {
        	action = GameManagerUI.wait(game, players, action, 1);
            weapon.upgrade(game, this.getName());
            game.addTerminalLine(" ");
            resultado = 2;
        }

        Integer[] res = {action, resultado};
        return res;
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
    
    public int combat(GameMenu game, Integer action, List<Player> players, Player enemy) {
    	Integer damage = this.weapon.getDamage()[this.weapon.getLevel()-1];
    	Integer enemyDamage = enemy.getWeapon().getDamage()[this.weapon.getLevel()-1];
    	Boolean activeCombat = true;
    	
    	Random rand = new Random();
    	String thisAtk = ("  → " + this.getName() + " ataca a " + enemy.getName());
    	String enemyAtk = ("  → " + enemy.getName() + " ataca a " + this.getName());
    	String specificAtk = "";
    	
    	game.addTerminalLine("\n" + this.getName().toUpperCase() + " ⚔️ " + enemy.getName().toUpperCase());
    	while (activeCombat){
    		action = GameManagerUI.wait(game, players, action, 1);
    		
    		Integer thisMult = 1;
			Integer thisLuck = rand.nextInt(8);
			
			if(thisLuck <= 0) {
				thisMult = 0;
    			specificAtk = (" pero FALLA el golpe");
    			
    		} else if(thisLuck >= 7){
    			thisMult = 2;
    			specificAtk = (" causandole un DAÑO CRÍTICO de " + damage * thisMult + "puntos de daño");
    			
    		} else specificAtk = (" causandole " + damage + "puntos de daño");
			
			if (action <= 1) game.addTerminalLine(thisAtk + specificAtk);
			enemy.hp -= damage * thisMult;
    		this.addDamageDeal(damage);
    		enemy.addDamageReceived(damage); 
	    	
	    	if(enemy.getHp() <= 0){
	    		game.addTerminalLine("  💀 " + enemy.getName() + " ha sido eliminado");
	    		this.addKills();
	        	activeCombat = false;
	    	}else{
	    		action = GameManagerUI.wait(game, players, action, 1);
	    		
	    		Integer enemyMult = 1;
				Integer enemyLuck = rand.nextInt(8);
				
				if(enemyLuck <= 0) {
					enemyMult = 0;
	    			specificAtk = (" pero FALLA el golpe");
	    			
	    		} else if(enemyLuck >= 7){
	    			enemyMult = 2;
	    			specificAtk = (" causandole un DAÑO CRÍTICO de " + damage * enemyMult + "puntos de daño");
	    			
	    		} else specificAtk = (" causandole " + damage + "puntos de daño");
				
				if (action <= 1) game.addTerminalLine(enemyAtk + specificAtk);
	    		this.hp -= enemyDamage * enemyMult;
	    		enemy.addDamageDeal(enemyDamage);
	    		this.addDamageReceived(enemyDamage);
	    		
		    	if(this.getHp() <= 0) {
		    		game.addTerminalLine("  💀 " + this.getName() + " ha sido eliminado");
		    		enemy.addKills();
		    		activeCombat = false;
		    	}
	    	}
    	}
    	
	    game.addTerminalLine(" ");
    	return action;
    }
    
    public void autoDamage() {
    	Random seed = new Random();
    	Integer probAutoDamage = seed.nextInt(100);
    	if(probAutoDamage < 50) {
    		System.out.println( this.getName() + " se ha clavado su " + this.weapon.getWeaponType()+ ":(" + " y ha perdido: " + this.weapon.getDamage()[this.weapon.getLevel() - 1] + " puntos de vida");
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
    				game.addTerminalLine(this.getName() + " se ha cegado mirando al sol y se ha tropezado perdiendo -20ps");
    				damage=20;
    			}else if(chance == 2) {
    				game.addTerminalLine(this.getName() + " ha sido atacado de la nada por un dragón, logrando escapar, pero ha escapado perdiendo -70ps");
    				damage=70;
    			}else if(chance == 3) {
    				game.addTerminalLine("A " + this.getName() + " le ha dado un chungo, perdiendo la mitad de sus puntos de vida: -" + (this.getHp()/2) + "ps");
    				damage=this.getHp()/2;
    			}
    			    			
    			this.hp -= damage;
    			this.addIncidentsOcurred();
    			this.addDamageReceived(damage);
    			
    			if(this.getHp()<=0) {
    				game.addTerminalLine("  💀 Lamentablemente " + this.getName() + " no lo logró ");
    			}
    			game.addTerminalLine(" ");
    	}
    }
    
    public void showStats(GameMenu game) {
    	game.addStatsLine(this.name + "[(" + this.getClassType() + " | " + this.getHp() + "ps) : ("
    		+ this.getWeaponType() + " Lvl." + this.weapon.getLevel() + " | DMG:" + this.weapon.getDamage()[this.weapon.getLevel()-1] + ")]");
    }
    
    
    // Modificacion de stats
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