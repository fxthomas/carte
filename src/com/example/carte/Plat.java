package com.example.carte;

public class Plat {
	
	String nom;
	String description;
	float prix;
	int note;
	int image;
	
	public Plat(String nom, String description, float prix, int note, int image) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.note = note;
		this.image = image;
	}

	public Plat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	
	
}
