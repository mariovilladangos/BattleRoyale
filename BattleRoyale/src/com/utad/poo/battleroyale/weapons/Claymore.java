package com.utad.poo.battleroyale.weapons;

import java.util.Arrays;

public final class Claymore extends Weapon{
	
	//public static final Integer DEF_LEVEL = 1;
	public static final Integer[] DEF_DAMAGE = {40, 50, 55};		
	public Claymore() {
		this(DEF_DAMAGE);
	}
	public Claymore (Integer[] damage) {
		super(damage);
	}

	@Override
	public String getWeaponType() {
		return "Claymore";
	}
		
	@Override
	public String toString() {
		return "Claymore [damage=" + Arrays.toString(damage) + ", level=" + level + "]";
	}
}
