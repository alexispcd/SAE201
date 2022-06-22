package SAE;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private static FenDetailCategorie fenDetailCategorie = new FenDetailCategorie();
	private static FenNouvelleCategorie fenNouvelleCategorie = new FenNouvelleCategorie();
	private static FenCategorie fenCategorie = new FenCategorie();
	private static FenReservation fenReservation = new FenReservation();

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage f) throws Exception {
		Donnees.initDonnees();
		fenDetailCategorie.initPrestas(Donnees.getPrestations());
		fenNouvelleCategorie.initPrestas(Donnees.getPrestations());
		fenReservation.initContrats(Donnees.getContrats());
		FenCategorie.initCategories(Donnees.getCategories());
		fenCategorie.show();	
	}

	public static void ouvrirCategorie() {
		fenCategorie.show();
	}

	public static void ouvrirDetailCategorie(Categorie cat) {
		fenDetailCategorie.initCategorie(cat);
		fenDetailCategorie.show();
	}

	public static void ouvrirNouvelleCategorie() {
		fenNouvelleCategorie.show();
	}

	public static void ouvrirReservation() {
		fenReservation.show();
	}
}
