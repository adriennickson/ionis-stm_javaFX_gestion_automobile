package com.ionisStm.JavaFX.objets;

import java.util.Date;

public class Location {
	
	int id;
	int idLocataire;
	int idVoiture;
	Date dateLocation;
	Date dateRetour;
	int kilometrage;
	
	public Location(int id, int idLocataire, int idVoiture, Date dateLocation, Date dateRetour, int kilometrage) {
		super();
		this.id = id;
		this.idLocataire = idLocataire;
		this.idVoiture = idVoiture;
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
		this.kilometrage = kilometrage;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdLocataire() {
		return idLocataire;
	}

	public void setIdLocataire(int idLocataire) {
		this.idLocataire = idLocataire;
	}

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public Date getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(Date dateLocation) {
		this.dateLocation = dateLocation;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", idLocataire=" + idLocataire + ", idVoiture=" + idVoiture + ", dateLocation="
				+ dateLocation + ", dateRetour=" + dateRetour + ", kilometrage=" + kilometrage + "]";
	}
	
	public String serialize() {
		return id+"\t"+idLocataire+"\t"+idVoiture+"\t"+dateLocation+"\t"+dateRetour+"\t"+kilometrage+"\n";
	}
	
}
