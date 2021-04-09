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
import java.util.List;
import java.util.stream.Collectors;

import com.ionisStm.JavaFX.objets.Locataire;
import com.ionisStm.JavaFX.objets.Voiture;

public class ManipulationDesFichiers {

	public static final String NOM_DU_DOSSIER = "/AgenceDeLocationDeVoiture";
	static String path;
	static {
		path = System.getProperty("user.home") + NOM_DU_DOSSIER;		
	}

	public ManipulationDesFichiers() {
	}

	public void affiche(String fichier) {
		try {
			FileReader r = new FileReader(path+fichier);
			BufferedReader br = new BufferedReader(r);
			String fileText = br.lines().collect(Collectors.joining());
		    br.close();
		    r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Object> toutesLesLignes(String fichier) {
		java.util.List<Object> lignes = new ArrayList<Object>();
		try {
			FileReader r = new FileReader(path + fichier);
			BufferedReader br = new BufferedReader(r);
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tab = line.split("\t"); 
		    	if (fichier.equals("/locataires.txt"))
			    	lignes.add(new Locataire( Integer.parseInt(tab[0]), tab[1], tab[2]));					
		    	if (fichier.equals("/voitures.txt"))
			    	lignes.add(new Voiture( Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], Integer.parseInt(tab[4]), Integer.parseInt(tab[5])));					
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
	    	out = new BufferedWriter(new FileWriter(path+fichier));
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
	
	public static void ajouterUneLigne(String fichier, String ligne) {
	    try {	    	
	    	BufferedWriter out = new BufferedWriter(new FileWriter(path+fichier, true));
	        out.append(ligne);
			out.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void supprimerUneLigne(String fichier, Object item) {
		
		List<Object> all = toutesLesLignes(fichier).stream().filter(e -> !e.toString().equals(item.toString())).collect(Collectors.toList());

		try {	    	
	    	BufferedWriter out = new BufferedWriter(new FileWriter(path+fichier));
	    	all.forEach( e -> {
				try {
			    	if (fichier.equals("/locataires.txt"))
						out.append( ((Locataire) e).serialize() );
			    	if (fichier.equals("/voitures.txt"))
			    		out.append( ((Voiture) e).serialize() );
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} );
			out.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void configurerLesFichiers(){
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
