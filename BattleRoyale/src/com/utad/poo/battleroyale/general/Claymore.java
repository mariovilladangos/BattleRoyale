package com.utad.poo.battleroyale.general;

public class Claymore extends Weapon{
	
	//public static final Integer DEF_LEVEL = 1;
		public static final Integer[] DEF_DAMAGE = {40, 50, 55};		
		public Claymore() {
			this(DEF_DAMAGE);
		}
		public Claymore (Integer[] damage) {
			super(damage);
		}
	//Getters and setters
	public Integer[] getDamage() {
		return damage;
	}
	public void setDamage(Integer[] damage) {
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
