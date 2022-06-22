package SAE;

import java.util.ArrayList;
import java.util.Collection;

public class Categorie {
	private final int nbPrestaMax = 6;

	private String nom;
	private int tarif;
	private int nbMax;
	private boolean presta;
	private int surface;
	private Collection<Prestation> prestations;
	private Collection<Emplacement> emplacements;

	public Categorie(String nom, int tarif, int nbMax, boolean presta, int surface) {
		this.nom = nom;
		this.tarif = tarif;
		this.nbMax = nbMax;
		this.presta = presta;
		this.surface = surface;
		this.prestations = new ArrayList<Prestation>(nbPrestaMax);
		this.emplacements = new ArrayList<Emplacement>();
	}


	public void ajouterEmplacement(Emplacement emp) {
		this.emplacements.add(emp);
	}

	public void enleverEmplacement(Emplacement emp) {
		this.emplacements.remove(emp);
	}

	public boolean contientEmplacement(Emplacement emp) {
		return this.emplacements.contains(emp);
	}

	public void ajouterUnEmplacement(Emplacement emp) {
		if (!contientEmplacement(emp)) {
			ajouterEmplacement(emp);
		}
	}

	public void enleverUnEmplacement(Emplacement emp) {
		if (contientEmplacement(emp)) {
			enleverEmplacement(emp);
		}
	}

	public void ajouterPresta(Prestation p) {
		if (this.presta) {
			this.prestations.add(p);
		}
	}

	public void enleverPresta(Prestation p) {
		if (this.presta) {
			this.prestations.remove(p);
		}
	}

	public boolean contientPresta(Prestation p) {
		if (this.presta) {
			return this.prestations.contains(p);
		}
		return false;
	}

	public void ajouterUnePresta(Prestation p) {
		if (!contientPresta(p)) {
			ajouterPresta(p);
		}
	}

	public void enleverUnePresta(Prestation p) {
		if (contientPresta(p)) {
			enleverPresta(p);
		}
	}

	public int getNbPrestaMax() {
		return this.nbPrestaMax;
	}


	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTarif() {
		return this.tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public int getNbMax() {
		return this.nbMax;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public boolean isPresta() {
		return this.presta;
	}

	public boolean getPresta() {
		return this.presta;
	}

	public void setPresta(boolean presta) {
		this.presta = presta;
	}

	public int getSurface() {
		return this.surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public Collection<Prestation> getPrestations() {
		return this.prestations;
	}

	public void setPrestations(Collection<Prestation> prestations) {
		this.prestations = prestations;
	}

	public Collection<Emplacement> getEmplacements() {
		return this.emplacements;
	}

	public void setEmplacements(Collection<Emplacement> emplacements) {
		this.emplacements = emplacements;
	}
	
}
