package com.utad.poo.battleroyale.general;

public class Player {
	
	protected String name;
	protected String weapon;
	public static final String DEF_NAME="Player";
	public static final String DEF_WEAPON="Sword";
	
	
	public Player(){
		
		this(Player.DEF_NAME,Player.DEF_WEAPON);
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public Player(String name,String weapon){
		
		this.name=name;
		this.weapon=weapon;
	}
	
	
	@Override
	public String toString() {
		return "Player [ Name=" + this.name + ", Weapon=" + this.weapon + " ]";
	}
	
	

}
