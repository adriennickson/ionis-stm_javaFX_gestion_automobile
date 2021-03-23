package com.ionisStm.JavaFX.objets;

public class Locataire {
	int id;
	String nomComplet;
	String mail;
	
	
	public Locataire(int id, String nomComplet, String mail) {
		super();
		this.id = id;
		this.nomComplet = nomComplet;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}
	
	public String getNomComplet() {
		return nomComplet;
	}
	
	public String getMail() {
		return mail;
	}
	
	@Override
	public String toString() {
		return "locataires [id=" + id + ", nomComplet=" + nomComplet + ", mail=" + mail + "]\n";
	}
	
	public String serialize() {
		return id+"\t"+nomComplet+"\t"+mail+"\n";
	}
	
}
