package com.ionisStm.JavaFX.vues;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class FormulaireDesLocataires {

	Dialog<String[]> dialog;
	
	public FormulaireDesLocataires() {
		// Create the custom dialog.
		dialog = new Dialog<>();
		dialog.setTitle("Nouveau locataire");
		dialog.setHeaderText("Formulaire de création d'un nouveau locataire.");

		dialog.setGraphic(new ImageView(this.getClass().getResource("new_user.png").toString()));

		// Set the button types.
		ButtonType buttonType = new ButtonType("Sauvegarder", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

		// Create nomComplet and eMail labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nomComplet = new TextField();
		nomComplet.setPromptText("Nom Complet");
		//PasswordField eMail = new PasswordField();
		TextField eMail = new TextField();
		eMail.setPromptText("Mail");

		grid.add(new Label("Nom Complet:"), 0, 0);
		grid.add(nomComplet, 1, 0);
		grid.add(new Label("Mail:"), 0, 1);
		grid.add(eMail, 1, 1);

		// Enable/Disable button depending on whether a nomComplet was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(buttonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		nomComplet.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on nomComplet field by default.
		Platform.runLater(() -> nomComplet.requestFocus());

		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == buttonType) {
		        return new String[] {nomComplet.getText(), eMail.getText()};
		    }
		    return null;
		});

	}

	public Dialog<String[]> getDialog(){
		return dialog;
	}
}
