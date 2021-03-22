package com.ionisStm.JavaFX;

import com.ionisStm.JavaFX.objets.Locataire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage fenetre) throws Exception {
		// TODO Auto-generated method stub
		String os = System.getProperty("os.name");
		
		String pathToFiles = System.getProperty("user.home")+"/AgenceDeLocationDeVoiture";

		ManipulationDesFichiers.configurerLesFichiers(pathToFiles);

		String pathToLocataires = pathToFiles+"/locataires.txt";
		
		java.util.List<Locataire> locataires = ManipulationDesFichiers.toutesLesLignes(pathToLocataires);
		
		fenetre.setTitle("Agence de location de voiture");
		fenetre.setWidth(600);
		fenetre.setHeight(500);
		fenetre.setResizable(false);

		VBox root = new VBox();
		root.getChildren().add(new Rectangle(400, 200, Color.RED));
		root.getChildren().add(new Label( locataires.stream().map(e -> e.toString()).reduce("", String::concat) ));
		Scene scene = new Scene(root);
		
		fenetre.setScene(scene);
		fenetre.show();
		fenetre.centerOnScreen();	
				
		new Thread(() -> {
			try {
				Thread.sleep(5000);
				javafx.application.Platform.runLater(() -> fenetre.close());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

}
