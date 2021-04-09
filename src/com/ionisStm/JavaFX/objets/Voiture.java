package com.ionisStm.JavaFX.objets;

public class Voiture {
	
	int id;
	String marque;
	String model;
	String numeroDeSerie;
	int kilometrage;
	int kilometrageTotal;
	
	public Voiture(int id, String marque, String model, String numeroDeSerie, int kilometrage, int kilometrageTotal) {
		super();
		this.id = id;
		this.marque = marque;
		this.model = model;
		this.numeroDeSerie = numeroDeSerie;
		this.kilometrage = kilometrage;
		this.kilometrageTotal = kilometrageTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public int getKilometrageTotal() {
		return kilometrageTotal;
	}

	public void setKilometrageTotal(int kilometrageTotal) {
		this.kilometrageTotal = kilometrageTotal;
	}
	
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", marque=" + marque + ", model=" + model + ", numeroDeSerie=" + numeroDeSerie + ", kilometrage=" + kilometrage + ", kilometrageTotal=" + kilometrageTotal + "]\n";
	}

	public String serialize() {
		return id+"\t"+marque+"\t"+model+"\t"+numeroDeSerie+"\t"+kilometrage+"\t"+kilometrageTotal+"\n";
	}

}
