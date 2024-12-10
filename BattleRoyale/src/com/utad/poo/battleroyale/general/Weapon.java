package com.utad.poo.battleroyale.general;

public class Weapon {
	
	protected Double damage;
	protected Integer level;
	
	//public static final Double DEF_DAMAGE = 10.0;
	public static final Integer DEF_LEVEL = 1;
	//Constructor
	public Weapon(Double damage) {
		this(damage, DEF_LEVEL);
	}
	public Weapon(Double damage, Integer level) {
		this.damage = damage;
		this.level = level;
	}
	//Getters and setters
	public Double getDamage() {
		return damage;
	}

	public void setDamage(Double damage) {
		this.damage = damage;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	//ToString
	@Override
	public String toString() {
		return "Weapon damage=" + damage + ", level=" + level + " ";
	}
	
	//Método para mejorar el arma
	public void upgrade() {
    	this.level += 1;
    }
	
}
