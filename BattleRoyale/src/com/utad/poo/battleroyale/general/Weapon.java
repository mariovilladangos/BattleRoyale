package com.utad.poo.battleroyale.general;

public class Weapon {
	
	protected Double damage;
	protected Integer level;
	
	public static final Double DEF_DAMAGE = 10.0;
	public static final Integer DEF_LEVEL = 1;
	
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
		return "Weapon [damage=" + damage + ", level=" + level + "]";
	}
	
}
