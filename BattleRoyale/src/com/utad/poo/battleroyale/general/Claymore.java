package com.utad.poo.battleroyale.general;

public class Claymore extends Weapon{
	
	//public static final Integer DEF_LEVEL = 1;
		public static final Integer[] DEF_DAMAGE = {40, 50, 55};		
		public Claymore() {
			this(DEF_DAMAGE);
		}
		public Claymore (Double damage) {
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
		return "Claymore damage=" + damage + ", level=" + level + " ";
	}
	
}
