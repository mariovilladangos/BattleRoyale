package com.utad.poo.battleroyale.general;

public class Spear extends Weapon{
	
	private Double damage;
	private Integer level;
	
	public static final Double DEF_DAMAGE = 20.0;

	public Spear(Double damage, Integer level) {
		super();
		this.damage = damage;
		this.level = level;
	}

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
