package com.example.carte;

import java.util.ArrayList;

import com.example.carte.Plat.Type;

public class Plats {
	private static Plats instance = null;
	
	private ArrayList<Plat> plats;
	
	public Plats() {
		plats = new ArrayList<Plat>();
	}
	
	public ArrayList<Plat> getPlats() {
		return plats;
	}
	
	public void addPlat(Plat p) {
		plats.add(p);
	}
	
	public ArrayList<Plat> getPlatType(Type type) {
		ArrayList<Plat> platsType = new ArrayList<Plat>();
		for (Plat p : plats) {
			if (p.getType() == type)
				platsType.add(p);
		}
		return platsType;
	}
	
	public static Plats getInstance() {
		if (instance == null)
			instance = new Plats();
		return instance;
	}
}
