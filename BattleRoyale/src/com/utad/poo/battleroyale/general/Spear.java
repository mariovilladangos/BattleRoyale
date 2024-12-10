package com.utad.poo.battleroyale.general;

public class Spear extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	public static final Double DEF_DAMAGE = 20.0;
	
	public Spear() {
		this(DEF_DAMAGE);
	}
	public Spear (Double damage) {
		super(damage);
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
	@Override
	public String toString() {
		return "Spear [damage=" + damage + ", level=" + level + "]";
	}
	
}
