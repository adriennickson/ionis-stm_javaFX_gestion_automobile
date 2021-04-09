package com.ionisStm.JavaFX.vues;

import com.ionisStm.JavaFX.ManipulationDesFichiers;
import com.ionisStm.JavaFX.objets.Voiture;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class TableauDesVoitures {

	TableView<Voiture> VoituresTableView;

	public TableauDesVoitures(java.util.List<Voiture> voitures) {
		
		VoituresTableView =  new TableView<Voiture>();
	    VoituresTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	    TableColumn<Voiture, Integer> columnId = new TableColumn<>("ID");
	    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    TableColumn<Voiture, String> columnMarque = new TableColumn<>("Marque");
	    columnMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
	    columnMarque.setMinWidth(75);

	    TableColumn<Voiture, String> columnModel = new TableColumn<>("Model");
	    columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
	    columnModel.setMinWidth(75);

	    TableColumn<Voiture, String> columnNumeroDeSerie = new TableColumn<>("N° de serie");
	    columnNumeroDeSerie.setCellValueFactory(new PropertyValueFactory<>("numeroDeSerie"));
	    columnNumeroDeSerie.setMinWidth(75);

	    TableColumn<Voiture, String> columnKilometrageTotal = new TableColumn<>("Kilometrage");
	    columnKilometrageTotal.setCellValueFactory(new PropertyValueFactory<>("kilometrageTotal"));
	    columnKilometrageTotal.setMinWidth(75);

        TableColumn<Voiture, Void> columnBtn = new TableColumn("");
        Callback<TableColumn<Voiture, Void>, TableCell<Voiture, Void>> cellFactory = new Callback<TableColumn<Voiture, Void>, TableCell<Voiture, Void>>() {
            @Override
            public TableCell<Voiture, Void> call(final TableColumn<Voiture, Void> param) {
                final TableCell<Voiture, Void> cell = new TableCell<Voiture, Void>() {

                    private final Button btnSupprimer = new Button("Supprimer");

                    {
                        btnSupprimer.setStyle("-fx-background-color: #FF5555");
                        btnSupprimer.setOnAction((ActionEvent event) -> {
                        	Voiture data = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(getIndex());
                            //TODO: Vérifier si il n'y a pas de jointure
                            ManipulationDesFichiers.supprimerUneLigne("/voitures.txt", data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	HBox bouttons = new HBox(btnSupprimer);
                        	bouttons.setSpacing(5);
                        	setGraphic(bouttons);
                        }
                    }
                };
                return cell;
            }
        };
        columnBtn.setMinWidth(50);

        columnBtn.setCellFactory(cellFactory);

		voitures.forEach(l -> {
			VoituresTableView.getItems().add(l);
		});
		
	    VoituresTableView.getColumns().clear();
	    VoituresTableView.getColumns().addAll(columnId, columnMarque, columnModel, columnNumeroDeSerie, columnKilometrageTotal, columnBtn);

	}
	

	public TableView<Voiture> getView() {
		return this.VoituresTableView;
	}
	
}
