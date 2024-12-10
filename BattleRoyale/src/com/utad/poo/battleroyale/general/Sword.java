package com.utad.poo.battleroyale.general;

public class Sword extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	public static final Double DEF_DAMAGE = 30.0;
		
	public Sword() {
		this(DEF_DAMAGE);
	}
	public Sword (Double damage) {
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
		return "Sword damage=" + damage + ", level=" + level + " ";
	}
	
	
	
}
