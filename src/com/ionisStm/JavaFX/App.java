package com.ionisStm.JavaFX;

import java.util.Optional;
import java.util.stream.Collectors;
import com.ionisStm.JavaFX.objets.Locataire;
import com.ionisStm.JavaFX.objets.Voiture;
import com.ionisStm.JavaFX.vues.TableauDesLocataires;
import com.ionisStm.JavaFX.vues.TableauDesVoitures;
import com.ionisStm.JavaFX.vues.formulaire.FormulaireDesLocataires;
import com.ionisStm.JavaFX.vues.formulaire.FormulaireDesVoitures;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

public class App extends Application{

	java.util.List<Locataire> locataires;
	java.util.List<Voiture> voitures;
	//java.util.List<Contrat> contrats;
	TableView<Locataire> tableauDesLocataires;
	TableView<Voiture> tableauDesVoitures;
	
	@Override
	public void start(Stage fenetre) throws Exception {

		ManipulationDesFichiers.configurerLesFichiers();
		
		locataires = ManipulationDesFichiers.toutesLesLignes("/locataires.txt").stream().map(e-> (Locataire)e).collect(Collectors.toList());
		voitures = ManipulationDesFichiers.toutesLesLignes("/voitures.txt").stream().map(e-> (Voiture)e).collect(Collectors.toList());
		//contrats = ManipulationDesFichiers.toutesLesLignes("/voitures.txt").stream().map(e-> (Contrat)e).collect(Collectors.toList());
		
		voitures.forEach(v -> {
			System.out.println(v);
		});
		System.out.println("-");
		tableauDesLocataires = (new TableauDesLocataires(locataires)).getView();
		tableauDesVoitures = (new TableauDesVoitures(voitures)).getView();

		fenetre.setTitle("Agence de location de voiture");
		fenetre.setWidth(600);
		fenetre.setHeight(500);
		fenetre.setResizable(false);
		
        TabPane tabPane = new TabPane();


        // utilisateur
        ImageView plusUtilisateur = new ImageView(getClass().getResource("plus.png").toString());
        plusUtilisateur.setFitWidth(18);
        plusUtilisateur.setFitHeight(18);
        
        Button ajouterUtilisateur = new Button("Ajouter un utilisateur");
        ajouterUtilisateur.setGraphic(plusUtilisateur);
        ajouterUtilisateur.setOnAction(event -> {
        	
        	Dialog dialog = (new FormulaireDesLocataires()).getDialog();
        	
    		Optional<String[]> result = dialog.showAndWait();

    		result.ifPresent(nomEtMail -> {
    			int index = 1;
    			try {
    		    	index = locataires.get(locataires.size()-1).getId() + 1; 
    			}catch (Exception e) {
					// TODO: handle exception
				}
    		    Locataire l = new Locataire(index, nomEtMail[0], nomEtMail[1]);
    		    ManipulationDesFichiers.ajouterUneLigne("/locataires.txt", l.serialize());
    		    locataires.add(l);
    		    tableauDesLocataires.getItems().add(l);
    		});	    		

        });
        VBox vBoxLocataires = new VBox( ajouterUtilisateur, tableauDesLocataires);
        vBoxLocataires.setSpacing(5);
        vBoxLocataires.setPadding(new Insets(10));
        
        
        // voiture
        ImageView plusVoiture = new ImageView(getClass().getResource("plus.png").toString());
        plusVoiture.setFitWidth(18);
        plusVoiture.setFitHeight(18);
        
        Button ajouterVoiture = new Button("Ajouter une voiture");
        ajouterVoiture.setGraphic(plusVoiture);
        ajouterVoiture.setOnAction(event -> {
        	
        	Dialog dialogDesVoitures = (new FormulaireDesVoitures()).getDialog();
        	
    		Optional<String[]> result = dialogDesVoitures.showAndWait();

    		result.ifPresent(voiture -> {
    			int index = 1;
    			try {
        			index = voitures.get(voitures.size()-1).getId() + 1;     				
    			}catch (Exception e) {
					// TODO: handle exception
				}
    		    Voiture v = new Voiture(index, voiture[0], voiture[1], voiture[2], 0, 0);
    		    ManipulationDesFichiers.ajouterUneLigne("/voitures.txt", v.serialize());
    		    voitures.add(v);
    		    tableauDesVoitures.getItems().add(v);
    		});	    		

        });
        VBox vBoxVoitures = new VBox( ajouterVoiture, tableauDesVoitures);
        vBoxVoitures.setSpacing(5);
        vBoxVoitures.setPadding(new Insets(10));
        
        
        
        
        
        Tab tabLocataires = new Tab("Utilisateurs",vBoxLocataires);
        Tab tabVoitures = new Tab("Voitures", vBoxVoitures);
        Tab tabLocations = new Tab("Locations", new Rectangle(150, 200, Color.RED));

        tabPane.getTabs().add(tabLocataires);
        tabPane.getTabs().add(tabVoitures);
        tabPane.getTabs().add(tabLocations);

		VBox root = new VBox(tabPane);
		
		Scene scene = new Scene(root);
		
		fenetre.setScene(scene);
		fenetre.show();
		fenetre.centerOnScreen();	
				
		new Thread(() -> {
			try {
				Thread.sleep(15000);
				javafx.application.Platform.runLater(() -> fenetre.close());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		//.start();
	}

}
