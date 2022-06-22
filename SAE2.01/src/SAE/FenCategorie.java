package SAE;

import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FenCategorie extends Stage {
	private AnchorPane				racine			 = new AnchorPane();
	public static TableView<Categorie>	tableCategorie	 = new TableView<Categorie>();
	private Button					bnAjouter		 = new Button("Ajouter");
	private Button 					bnModifier	 	 = new Button("Modifier");
	private Button 					bnSupprimer		 = new Button("Supprimer");
	private Button 					bnFermer		 = new Button("Fermer");
	private Button					bnReservation	 = new Button("Réservation");
	private MenuItem 				optionAjouter	 = new MenuItem("Ajouter...");
	private MenuItem 				optionModifier	 = new MenuItem("Modifier...");
	private MenuItem 				optionSupprimer	 = new MenuItem("Supprimer");
	private ContextMenu 			menu			 = new ContextMenu(
		optionAjouter,
		new SeparatorMenuItem(),
		optionModifier,
		new SeparatorMenuItem(),
		optionSupprimer
	);


	public FenCategorie() {
		this.setTitle("Categories");
		this.setResizable(true);
		this.setMinHeight(250);
		this.setMinWidth(505);
		this.sizeToScene();
		this.setScene(new Scene(creerContenu()));
	}

	private Parent creerContenu() {
		TableColumn<Categorie, String>	colonneNom = new TableColumn<Categorie, String>("Nom");
		colonneNom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));
		tableCategorie.getColumns().add(colonneNom);
		TableColumn<Categorie, Integer>	colonneTarif = new TableColumn<Categorie, Integer>("Tarif");
		colonneTarif.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("tarif"));
		tableCategorie.getColumns().add(colonneTarif);
		TableColumn<Categorie, Integer>	colonneNbMax = new TableColumn<Categorie, Integer>("NbMax");
		colonneNbMax.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("nbMax"));
		tableCategorie.getColumns().add(colonneNbMax);
		TableColumn<Categorie, Boolean>	colonnePresta = new TableColumn<Categorie, Boolean>("Presta");
		colonnePresta.setCellValueFactory(new PropertyValueFactory<Categorie, Boolean>("presta"));
		tableCategorie.getColumns().add(colonnePresta);
		TableColumn<Categorie, Integer>	colonneSurface = new TableColumn<Categorie, Integer>("Surface");
		colonneSurface.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("surface"));
		tableCategorie.getColumns().add(colonneSurface);

		tableCategorie.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tableCategorie.setContextMenu(menu);
		tableCategorie.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2 && e.getTarget() instanceof Text) {
				Main.ouvrirDetailCategorie(tableCategorie.getSelectionModel().getSelectedItem());
			}
		});



		BooleanBinding rien = Bindings.equal(tableCategorie.getSelectionModel().selectedIndexProperty(), -1);

		bnAjouter.setPrefWidth(100);
		bnAjouter.setOnAction(e -> {
			Main.ouvrirNouvelleCategorie();
		});
		optionAjouter.setOnAction(e -> {
			Main.ouvrirNouvelleCategorie();
		});	
		

		bnModifier.setPrefWidth(100);
		bnModifier.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		bnModifier.setOnAction(e -> {
			Main.ouvrirDetailCategorie(tableCategorie.getSelectionModel().getSelectedItem());
		});
		optionModifier.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		optionModifier.setOnAction(e -> {
			Main.ouvrirDetailCategorie(tableCategorie.getSelectionModel().getSelectedItem());
		});
		

		bnSupprimer.setPrefWidth(100);
		bnSupprimer.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		bnSupprimer.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette catégorie ?", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Suppression d'une catégorie");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				supprimerCategorie(tableCategorie.getSelectionModel().getSelectedItem());
			}
		});
		optionSupprimer.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		optionSupprimer.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette catégorie ?", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Suppression d'une catégorie");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				supprimerCategorie(tableCategorie.getSelectionModel().getSelectedItem());
			}
		});

		bnFermer.setPrefWidth(100);
		bnFermer.setOnAction(e -> {
			this.close();
		});
		

		bnReservation.setPrefWidth(100);
		bnReservation.setPrefHeight(50);
		bnReservation.setOnAction(e -> {
			this.close();
			Main.ouvrirReservation();
		});
		
		AnchorPane.setTopAnchor(tableCategorie, 10.0);
		AnchorPane.setLeftAnchor(tableCategorie, 10.0);
		AnchorPane.setRightAnchor(tableCategorie, 120.0);
		AnchorPane.setBottomAnchor(tableCategorie, 10.0);
		AnchorPane.setTopAnchor(bnAjouter, 30.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setTopAnchor(bnModifier, 80.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setTopAnchor(bnSupprimer, 130.0);
		AnchorPane.setRightAnchor(bnSupprimer, 10.0);
		AnchorPane.setBottomAnchor(bnReservation, 150.0);
		AnchorPane.setRightAnchor(bnReservation, 10.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		racine.getChildren().addAll(tableCategorie, bnAjouter, bnModifier, bnSupprimer, bnFermer, bnReservation);
		return racine;
	}

	public static void modifierCategorie(Categorie categorie) {
		int index = tableCategorie.getSelectionModel().getSelectedIndex();
		tableCategorie.getItems().set(index, categorie);
	}

	public static void supprimerCategorie(Categorie c) {
		tableCategorie.getItems().remove(c);
	}	

	public static void initCategories(ArrayList<Categorie> categories) {
		tableCategorie.setItems(FXCollections.observableArrayList(categories));
	}
}