package com.example.carte;

public class Plat {
	private static int idActuel = 0;
	
	private String nom;
	private String description;
	private float prix;
	private int note;
	private int image;
	private int quantite;
	private Type type;
	private int id;
	
	public static enum Type {ENTREE, DESSERT, VIANDE};
	
	public Plat(String nom, String description, float prix, int note, int image, Type type) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.note = note;
		this.image = image;
		this.type = type;
		this.id = idActuel;
		idActuel++;
		quantite = 0;
	}

	public Plat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void ajouter() {
		quantite++;
	}
	
	public void diminuer() {
		if (quantite > 0)
			quantite--;
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
	
	public int getQuantite() {
		return quantite;
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
	
	public Type getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
}
