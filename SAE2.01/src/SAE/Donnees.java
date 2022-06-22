package SAE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Donnees {
	private static ArrayList<Prestation> prestations = new ArrayList<Prestation>();
	private static ArrayList<Categorie> categories = new ArrayList<Categorie>();
	private static ArrayList<Contrat> contrats = new ArrayList<Contrat>();
	private static ArrayList<Client> clients = new ArrayList<Client>();
	private static ArrayList<Emplacement> emplacements = new ArrayList<Emplacement>();

	public static void initDonnees() {
		prestations.add(new Raccordement("Raccordement"));
		prestations.add(new Refrigirateur("Réfrigirateur"));
		prestations.add(new PersonneSup("PersonneSup + 7 ans"));
		prestations.add(new PersonneSup("PersonneSup + 2 ans"));
		prestations.add(new PersonneSup("PersonneSup - 2 ans"));

		categories.add(new Categorie("Simple", 140, 6, true, 0));
		categories.add(new Categorie("Standard", 450, 4, false, 22));
		categories.add(new Categorie("Grand Luxe", 500, 6, false, 35));
		

		clients.add(new Client("nom1", "prenom1", "adresse1", "m", 1234567890, "mail1", "ville1", 23456, "pays1"));
		clients.add(new Client("nom2", "prenom2", "adresse2", "f", 1234567891, "mail2", "ville2", 34567, "pays2"));
		clients.add(new Client("nom3", "prenom3", "adresse3", "m", 1234567892, "mail3", "ville3", 45678, "pays3"));

		emplacements.add(new Emplacement(categories.get(0)));
		emplacements.add(new Emplacement(categories.get(1)));
		emplacements.add(new Emplacement(categories.get(2)));


		for (int i = 0; i < 3; i++) {
			LocalDate dateDebut = LocalDate.now().plusDays(i*7);
			LocalDate dateFin = LocalDate.now().plusDays(7+i*7);
			while (dateDebut.getDayOfWeek() != DayOfWeek.SATURDAY) {
				dateDebut = dateDebut.plusDays(1);
			}
			while (dateFin.getDayOfWeek() != DayOfWeek.SATURDAY) {
				dateFin = dateFin.plusDays(1);
			}
			contrats.add(new Contrat(dateDebut, dateFin, 0, emplacements.get(i), clients.get(i)));
		}
	}

	public static ArrayList<Prestation> getPrestations() {
		return prestations;
	}
	
	public static ArrayList<Categorie> getCategories() {
		return categories;
	}

	public static ArrayList<Contrat> getContrats() {
		return contrats;
	}

	public static ArrayList<Client> getClients() {
		return clients;
	}

	public static ArrayList<Emplacement> getEmplacements() {
		return emplacements;
	}

	public void addCategorie(Categorie categorie) {
		categories.add(categorie);
	}

	public void removeCategorie(Categorie categorie) {
		categories.remove(categorie);
	}
}
