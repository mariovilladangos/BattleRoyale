package com.utad.poo.battleroyale.general;

public class Player {
	
	protected String name;
	protected Weapon weapon;
	public static final String DEF_NAME="Player";
	//public static final Weapon DEF_WEAPON;
	
	public Player(String name,Weapon weapon){
		
		this.name=name;
		this.weapon=weapon;
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
	
	@Override
	public String toString() {
		return "Player [ Name=" + this.name + ", Weapon=" + this.weapon + " ]";
	}
	
	

}
