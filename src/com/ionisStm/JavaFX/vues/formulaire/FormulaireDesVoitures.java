package com.ionisStm.JavaFX.vues.formulaire;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FormulaireDesVoitures {

	Dialog<String[]> dialog;
	
	public FormulaireDesVoitures() {
		// Create the custom dialog.
		dialog = new Dialog<>();
		dialog.setTitle("Nouvelle voiture");
		dialog.setHeaderText("Formulaire de création d'une nouvelle voiture.");

		dialog.setGraphic(new ImageView(this.getClass().getResource("car.png").toString()));

		// Set the button types.
		ButtonType buttonType = new ButtonType("Sauvegarder", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

		// Create nomComplet and eMail labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField marque = new TextField();
		marque.setPromptText("Marque");
		TextField model = new TextField();
		model.setPromptText("Model");
		TextField numeroDeSerie = new TextField();
		numeroDeSerie.setPromptText("Numéro de serie");

		grid.add(new Label("Marque:"), 0, 0);
		grid.add(marque, 1, 0);
		grid.add(new Label("Model:"), 0, 1);
		grid.add(model, 1, 1);
		grid.add(new Label("Numéro de serie:"), 0, 2);
		grid.add(numeroDeSerie, 1, 2);

		// Enable/Disable button depending on whether a nomComplet was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(buttonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		marque.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on nomComplet field by default.
		Platform.runLater(() -> marque.requestFocus());

		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == buttonType) {
		        return new String[] {marque.getText(), model.getText(), numeroDeSerie.getText()};
		    }
		    return null;
		});

	}

	public Dialog<String[]> getDialog(){
		return dialog;
	}
}
