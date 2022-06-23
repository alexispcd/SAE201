package SAE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FenReservation extends Stage {
	private AnchorPane				racine		 = new AnchorPane();
	private HBox					hboxDu			 = new HBox();
	private HBox					hboxAu			 = new HBox();
	private TableView<Contrat> 		tableContrat	 = new TableView<Contrat>();
	private Button					bnAfficher		 = new Button("Chercher");
	private Button 					bnCategorie	 	 = new Button("Catégorie");
	private Button 					bnFermer 		 = new Button("Fermer");
	private Label					lblDu			 = new Label("Du");
	private Label					lblAu			 = new Label("Au");
	private DatePicker				dpDu			 = new DatePicker();
	private DatePicker				dpAu			 = new DatePicker();
	private Tooltip					tooltipDu		 = new Tooltip("Date de début de la réservation");
	private Tooltip					tooltipAu		 = new Tooltip("Date de fin de la réservation");
	private Tooltip					tooltipAfficher	 = new Tooltip("Afficher les contrats de la période");
	private Tooltip					tooltipCategorie = new Tooltip("Afficher les catégories");

	public FenReservation() {
		this.setTitle("Réservations");
		this.setResizable(false);
		this.setMinHeight(250);
		this.setMinWidth(800);
		this.sizeToScene();
		this.setScene(new Scene(creerContenu()));
	}


	public Parent creerContenu() {
		TableColumn<Contrat, Integer>	colonneNumero = new TableColumn<Contrat, Integer>("Contrat");
		colonneNumero.setCellValueFactory(new PropertyValueFactory<Contrat, Integer>("numContrat"));
		tableContrat.getColumns().add(colonneNumero);
		TableColumn<Contrat, Integer>	colonneEmplacement = new TableColumn<Contrat, Integer>("Emplacement");
		colonneEmplacement.setCellValueFactory(new PropertyValueFactory<Contrat, Integer>("numEmp"));
		tableContrat.getColumns().add(colonneEmplacement);
		TableColumn<Contrat, String>	colonneClient = new TableColumn<Contrat, String>("Client");
		colonneClient.setCellValueFactory(new PropertyValueFactory<Contrat, String>("nomCli"));
		tableContrat.getColumns().add(colonneClient);
		TableColumn<Contrat, LocalDate>	colonneDeb = new TableColumn<Contrat, LocalDate>("Début");
		colonneDeb.setCellValueFactory(new PropertyValueFactory<Contrat, LocalDate>("dDebut"));
		tableContrat.getColumns().add(colonneDeb);
		TableColumn<Contrat, LocalDate>	colonneFin = new TableColumn<Contrat, LocalDate>("Fin");
		colonneFin.setCellValueFactory(new PropertyValueFactory<Contrat, LocalDate>("dFin"));
		tableContrat.getColumns().add(colonneFin);
		tableContrat.setPrefWidth(500);


		Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();

		dpDu.setValue(LocalDate.now());
		dpDu.setShowWeekNumbers(true);
        dpDu.setDayCellFactory(dayCellFactory);
		dpDu.setPrefWidth(120);


		dpAu.setValue(LocalDate.now());
		dpAu.setShowWeekNumbers(true);
		dpAu.setDayCellFactory(dayCellFactory);
		dpAu.setPrefWidth(120);

		
		bnAfficher.setPrefWidth(100);
		bnAfficher.setOnAction(e -> {
			ArrayList<Contrat> contrats = new ArrayList<Contrat>();
			LocalDate dateDu = dpDu.getValue();
			LocalDate dateAu = dpAu.getValue();
			for(Contrat c : Donnees.getContrats()) {
				if(c.getDDebut().isAfter(dateDu.plusDays(-1)) && c.getDFin().isBefore(dateAu.plusDays(1))) {
					contrats.add(c);
				}
			}
			tableContrat.setItems(FXCollections.observableArrayList(contrats));
		});

		bnCategorie.setPrefWidth(100);
		bnCategorie.setPrefHeight(50);
		bnCategorie.setOnAction(e -> {
			this.close();
			Main.ouvrirCategorie();
		});

		bnFermer.setPrefWidth(100);
		bnFermer.setOnAction(e -> {
			this.close();
		});

		dpDu.setTooltip(tooltipDu);
		dpAu.setTooltip(tooltipAu);
		bnAfficher.setTooltip(tooltipAfficher);
		bnCategorie.setTooltip(tooltipCategorie);
		
		hboxDu.getChildren().addAll(lblDu, dpDu);
		hboxAu.getChildren().addAll(lblAu, dpAu);

		hboxDu.setSpacing(10);
		hboxAu.setSpacing(10);
		AnchorPane.setTopAnchor(tableContrat, 10.0);
		AnchorPane.setLeftAnchor(tableContrat, 10.0);
		AnchorPane.setRightAnchor(tableContrat, 170.0);
		AnchorPane.setBottomAnchor(tableContrat, 10.0);
		AnchorPane.setTopAnchor(hboxDu, 10.0);
		AnchorPane.setRightAnchor(hboxDu, 10.0);
		AnchorPane.setTopAnchor(hboxAu, 60.0);
		AnchorPane.setRightAnchor(hboxAu, 10.0);
		AnchorPane.setTopAnchor(bnAfficher, 120.0);
		AnchorPane.setRightAnchor(bnAfficher, 30.0);
		AnchorPane.setTopAnchor(bnCategorie, 220.0);
		AnchorPane.setRightAnchor(bnCategorie, 30.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 30.0);

		racine.getChildren().addAll(tableContrat, hboxDu, hboxAu, bnAfficher, bnCategorie, bnFermer);
		return racine;
	}

	public void initContrats(ArrayList<Contrat> contrats) {
		tableContrat.setItems(FXCollections.observableArrayList(contrats));
	}


	private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.getDayOfWeek() != DayOfWeek.SATURDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #cccccc;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
