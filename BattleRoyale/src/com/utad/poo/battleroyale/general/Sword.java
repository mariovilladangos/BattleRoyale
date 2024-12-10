package com.utad.poo.battleroyale.general;

import java.util.Arrays;

public class Sword extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	//public static final Double[] DEF_DAMAGE = {}
	public static final Integer[] DEF_DAMAGE = {30, 50, 65};
	
	public Sword() {
		this(DEF_DAMAGE);
	}
	public Sword (Integer[] damage) {
		super(damage);
	}
	@Override
	public String toString() {
		return "Sword [damage=" + Arrays.toString(damage) + ", level=" + level + "]";
	}
	
	
	
	
}
