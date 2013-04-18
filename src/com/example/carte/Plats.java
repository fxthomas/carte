package com.example.carte;

import java.util.ArrayList;

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
	
	public static Plats getInstance() {
		if (instance == null)
			instance = new Plats();
		return instance;
	}
}
