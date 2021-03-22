package com.ionisStm.JavaFX;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ionisStm.JavaFX.objets.Locataire;

public class ManipulationDesFichiers {

	public void affiche(String fichier) {
		try {
			FileReader r = new FileReader(fichier);
			BufferedReader br = new BufferedReader(r);
			String fileText = br.lines().collect(Collectors.joining());
			System.out.println(fileText);	
		    br.close();
		    r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static java.util.List<Locataire> toutesLesLignes(String fichier) {
		java.util.List<Locataire> lignes = new ArrayList<Locataire>();
		try {
			FileReader r = new FileReader(fichier);
			BufferedReader br = new BufferedReader(r);
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tab = line.split("\t"); 
		    	lignes.add(new Locataire( Integer.parseInt(tab[0]), tab[1], tab[2]));
		    }
		    br.close();
		    r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}
	
	public void ecrireTexte(String fichier, String ligne) throws IOException{
		BufferedWriter out = null;
		try {	    	
	    	out = new BufferedWriter(new FileWriter(fichier));
	        out.write(ligne);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();				
			}
		}
	   
	}
	
	public void ajouterUneLigne(String fichier, String ligne) {
	    try {	    	
	    	BufferedWriter out = new BufferedWriter(new FileWriter(fichier, true));
	        out.append(ligne);
			out.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void configurerLesFichiers(String path){
	    File directory = new File(path);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
		try {
	        File locataires = new File(path + "/locataires.txt");
	        if(!locataires.exists())
				locataires.createNewFile();

	        File voitures = new File(path + "/voitures.txt");
	        if(!voitures.exists())
	        	voitures.createNewFile();
	        
	        File contrats = new File(path + "/contrats.txt");
	        if(!contrats.exists())
	        	contrats.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
