package com.utad.poo.battleroyale.general;

public class Claymore extends Weapon{
	
	private Double damage;
	private Integer level;
	
	public static final Double DEF_DAMAGE = 40.0;
	
	public Claymore(Double damage, Integer level) {
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
		return "Claymore [damage=" + damage + ", level=" + level + "]";
	}
	
}
