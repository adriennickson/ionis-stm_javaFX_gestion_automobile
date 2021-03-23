package com.ionisStm.JavaFX.vues;

import com.ionisStm.JavaFX.ManipulationDesFichiers;
import com.ionisStm.JavaFX.objets.Locataire;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class TableauDesLocataires {

	TableView<Locataire> locatairesTableView;

	public TableauDesLocataires(java.util.List<Locataire> locataires) {
		
		locatairesTableView =  new TableView<Locataire>();
	    locatairesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	    TableColumn<Locataire, String> columnId = new TableColumn<>("ID");
	    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    TableColumn<Locataire, String> columnNom = new TableColumn<>("Full Name");
	    columnNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
	    columnNom.setMinWidth(100);


	    TableColumn<Locataire, String> columnMail = new TableColumn<>("Mail");
	    columnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
	    columnMail.setMinWidth(200);

        TableColumn<Locataire, Void> columnBtn = new TableColumn("");
        Callback<TableColumn<Locataire, Void>, TableCell<Locataire, Void>> cellFactory = new Callback<TableColumn<Locataire, Void>, TableCell<Locataire, Void>>() {
            @Override
            public TableCell<Locataire, Void> call(final TableColumn<Locataire, Void> param) {
                final TableCell<Locataire, Void> cell = new TableCell<Locataire, Void>() {

                    private final Button btnVoir = new Button("Voir");
                    private final Button btnSupprimer = new Button("Supprimer");

                    {
                        btnVoir.setStyle("-fx-background-color: #55AA55");
                        btnSupprimer.setStyle("-fx-background-color: #FF5555");
                        btnVoir.setOnAction((ActionEvent event) -> {
                        	Locataire data = getTableView().getItems().get(getIndex());
                        });
                        btnSupprimer.setOnAction((ActionEvent event) -> {
                        	Locataire data = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(getIndex());
                            ManipulationDesFichiers.supprimerUneLigne("/locataires.txt", data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	HBox bouttons = new HBox(btnVoir, btnSupprimer);
                        	bouttons.setSpacing(5);
                        	setGraphic(bouttons);
                        }
                    }
                };
                return cell;
            }
        };
        columnBtn.setMinWidth(150);

        columnBtn.setCellFactory(cellFactory);

		locataires.forEach(l -> {
			locatairesTableView.getItems().add(l);
		});
		
	    locatairesTableView.getColumns().clear();
	    locatairesTableView.getColumns().addAll(columnId, columnNom, columnMail, columnBtn);

	}
	

	public TableView<Locataire> getView() {
		return this.locatairesTableView;
	}
	
}
