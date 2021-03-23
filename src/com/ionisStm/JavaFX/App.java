package com.ionisStm.JavaFX;

import java.util.Optional;
import java.util.stream.Collectors;
import com.ionisStm.JavaFX.objets.Locataire;
import com.ionisStm.JavaFX.vues.FormulaireDesLocataires;
import com.ionisStm.JavaFX.vues.TableauDesLocataires;
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
	//java.util.List<Voiture> voitures;
	//java.util.List<Contrat> contrats;
	TableView<Locataire> tableauDesLocataires;
	
	@Override
	public void start(Stage fenetre) throws Exception {

		locataires = ManipulationDesFichiers.toutesLesLignes("/locataires.txt").stream().map(e-> (Locataire)e).collect(Collectors.toList());
		//voitures = ManipulationDesFichiers.toutesLesLignes("/voitures.txt").stream().map(e-> (Voiture)e).collect(Collectors.toList());
		//contrats = ManipulationDesFichiers.toutesLesLignes("/voitures.txt").stream().map(e-> (Contrat)e).collect(Collectors.toList());
		
		tableauDesLocataires = (new TableauDesLocataires(locataires)).getView();

		fenetre.setTitle("Agence de location de voiture");
		fenetre.setWidth(600);
		fenetre.setHeight(500);
		fenetre.setResizable(false);
		
        TabPane tabPane = new TabPane();

        ImageView plus = new ImageView(getClass().getResource("plus.png").toString());
        plus.setFitWidth(18);
        plus.setFitHeight(18);
        Button ajouterUtilisateur = new Button("Ajouter un utilisateur");
        ajouterUtilisateur.setGraphic(plus);
        ajouterUtilisateur.setOnAction(event -> {
        	
        	Dialog dialog = (new FormulaireDesLocataires()).getDialog();
        	
    		Optional<String[]> result = dialog.showAndWait();

    		result.ifPresent(nomEtMail -> {
    		    int index = locataires.get(locataires.size()-1).getId() + 1; 
    		    Locataire l = new Locataire(index, nomEtMail[0], nomEtMail[1]);
    		    ManipulationDesFichiers.ajouterUneLigne("/locataires.txt", l.serialize());
    		    locataires.add(l);
    		    tableauDesLocataires.getItems().add(l);
    		});	
    		

        });
        VBox tabUtilisateurs = new VBox( ajouterUtilisateur, tableauDesLocataires);
        tabUtilisateurs.setSpacing(5);
        tabUtilisateurs.setPadding(new Insets(10));
        
        Tab tabLocataires = new Tab("Utilisateurs",tabUtilisateurs);
        Tab tabLocations = new Tab("Locations", new Label("Liste de toutes les locations"));
        Tab tabVoitures = new Tab("Voitures", new Rectangle(150, 200, Color.RED));

        tabPane.getTabs().add(tabLocataires);
        tabPane.getTabs().add(tabLocations);
        tabPane.getTabs().add(tabVoitures);

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
