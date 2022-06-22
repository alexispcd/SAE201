package SAE;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FenNouvelleCategorie extends Stage {
	private GridPane 				racine			 = new GridPane();
	private HBox					zoneBoutons		 = new HBox();
	private HBox					zoneRb			 = new HBox();
	private GridPane				zoneTableView	 = new GridPane();
	private Label					lblNom			 = new Label("Nom :");
	private Label					lblTarif		 = new Label("Tarif :");
	private Label					lblNbMax		 = new Label("Nombre personnes max :");
	private Label					lblPresta		 = new Label("Prestation :");
	private Label					lblSurface		 = new Label("Surface :");
	private Label					lblPrestas		 = new Label("Prestations :");
	private Label					lblPrestaDispo	 = new Label("Prestations disponibles :");
	private Label					lblPrestaAjoute  = new Label("Prestations ajoutée(s) :");
	private TextField				txtNom			 = new TextField();
	private TextField				txtTarif		 = new TextField();
	private TextField				txtNbMax		 = new TextField();
	private RadioButton				rbPresta		 = new RadioButton("Oui");
	private RadioButton				lbPresta		 = new RadioButton("Non");
	private ToggleGroup 			group			 = new ToggleGroup();
	private TextField				txtSurface		 = new TextField();
	private Button 					bnOK 			 = new Button("OK");
	private Button 					bnAnnuler 		 = new Button("Annuler");
	private Tooltip					tooltipNom		 = new Tooltip("Nom de la catégorie");
	private Tooltip					tooltipTarif	 = new Tooltip("Tarif de la catégorie");
	private Tooltip					tooltipNbMax	 = new Tooltip("Nombre maximum de personnes pour cette catégorie");
	private Tooltip					tooltipSurface	 = new Tooltip("Surface de la catégorie");
	private TableView<Prestation>	tablePrestaDispo = new TableView<Prestation>();
	private TableView<Prestation>	tablePrestaAjoute= new TableView<Prestation>();


	public FenNouvelleCategorie() {
		this.setTitle("Nouvelle catégorie");
		this.sizeToScene();
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));

	}

	public Parent creerContenu() {

		txtNom.setTooltip(tooltipNom);
		txtTarif.setTooltip(tooltipTarif);
		txtNbMax.setTooltip(tooltipNbMax);
		txtSurface.setTooltip(tooltipSurface);

		lbPresta.setToggleGroup(group);
		rbPresta.setToggleGroup(group);
		
		TableColumn<Prestation, String>	colonneNom = new TableColumn<Prestation, String>("Nom");
		colonneNom.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom"));
		tablePrestaDispo.getColumns().add(colonneNom);

		tablePrestaDispo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		tablePrestaDispo.setOnDragDetected(event -> {
			Dragboard db = tablePrestaDispo.startDragAndDrop(TransferMode.COPY);
			ClipboardContent content = new ClipboardContent();
			Prestation p = tablePrestaDispo.getSelectionModel().getSelectedItem();
			content.put(DataFormat.PLAIN_TEXT, p.getNom());
			db.setContent(content);
		});

		tablePrestaAjoute.setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY);
		});

		//tablePrestaAJoute setOnDragDropped prestation from tablePrestaDispo
		tablePrestaAjoute.setOnDragDropped(e -> {
			Prestation p = tablePrestaDispo.getSelectionModel().getSelectedItem();
			tablePrestaAjoute.getItems().add(p);
			e.setDropCompleted(true);
		});


		TableColumn<Prestation, String>	colonneNom2 = new TableColumn<Prestation, String>("Nom");
		colonneNom2.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom"));
		tablePrestaAjoute.getColumns().add(colonneNom2);

		BooleanBinding rien = Bindings.isNull(group.selectedToggleProperty());

		//booleanbinding on lbpresta not selected
		BooleanBinding lbPrestaNotSelected = Bindings.not(lbPresta.selectedProperty());

		zoneTableView.disableProperty().bind(
			Bindings.or(
				Bindings.when(rien).then(true).otherwise(false),
				Bindings.when(lbPrestaNotSelected).then(false).otherwise(true))
		);


		BooleanBinding manque = Bindings.or(
			Bindings.or(
				txtNom.textProperty().isEmpty(), 
				txtTarif.textProperty().isEmpty()
			), 
			Bindings.or(
				txtNbMax.textProperty().isEmpty(),
				Bindings.or(
					txtSurface.textProperty().isEmpty(), 
					group.selectedToggleProperty().isNull()
				)
			)
		);


		bnOK.setPrefWidth(100);
		bnOK.disableProperty().bind(Bindings.when(manque).then(true).otherwise(false));
		bnOK.setOnAction(e -> {
			Categorie cat = new Categorie(
				txtNom.getText(), 
				Integer.parseInt(txtTarif.getText()), 
				Integer.parseInt(txtNbMax.getText()), 
				rbPresta.isSelected(), 
				Integer.parseInt(txtSurface.getText())
			);
			for(Prestation p : tablePrestaAjoute.getItems()) {
				cat.ajouterUnePresta(p);
			}
			FenCategorie.tableCategorie.getItems().add(cat);
			this.close();
		});
		bnAnnuler.setPrefWidth(100);
		bnAnnuler.setOnAction(e -> {
			this.close();
		});

		
		tablePrestaDispo.setPrefWidth(200);
		tablePrestaDispo.setPrefHeight(200);
		tablePrestaAjoute.setPrefWidth(200);
		tablePrestaAjoute.setPrefHeight(200);
		zoneTableView.addRow(0, lblPrestaDispo, lblPrestaAjoute);
		zoneTableView.addRow(1, tablePrestaDispo, tablePrestaAjoute);
		zoneBoutons.getChildren().addAll(bnOK, bnAnnuler);
		zoneBoutons.setSpacing(10);
		zoneRb.getChildren().addAll(rbPresta, lbPresta);
		zoneRb.setSpacing(10);
		racine.addRow(0, lblNom, txtNom);
		racine.addRow(1, lblTarif, txtTarif);
		racine.addRow(2, lblNbMax, txtNbMax);
		racine.addRow(4, lblPresta, zoneRb);
		racine.addRow(3, lblSurface, txtSurface);
		racine.addRow(5, lblPrestas, zoneTableView);
		racine.add(zoneBoutons, 1, 10);
		racine.setHgap(10);
		racine.setVgap(10);
		racine.setPadding(new Insets(10));
		return racine;
	}

	public void initPrestas(ArrayList<Prestation> prestas) {
		tablePrestaDispo.setItems(FXCollections.observableArrayList(prestas));
	}
}
