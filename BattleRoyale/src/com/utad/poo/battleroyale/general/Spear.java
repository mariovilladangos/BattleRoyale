package com.utad.poo.battleroyale.general;

import java.util.Arrays;

public class Spear extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	public static final Integer[] DEF_DAMAGE = {20, 40, 70};	
	public Spear() {
		this(DEF_DAMAGE);
	}
	public Spear (Integer[] damage) {
		super(damage);
	}
	@Override
	public String toString() {
		return "Spear [damage=" + Arrays.toString(damage) + ", level=" + level + "]";
	}
	
	
}
