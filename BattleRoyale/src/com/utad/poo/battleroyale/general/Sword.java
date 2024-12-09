package com.utad.poo.battleroyale.general;

public class Sword extends Weapon{
	
	private Double damage;
	private Integer level;
	
	public static final Double DEF_DAMAGE = 30.0;
	
	public Sword(Double damage, Integer level) {
		super();
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

	
	
}
