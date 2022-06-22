package SAE;

public abstract class Prestation {
	private int nbSem;
	private String nom;

	public Prestation(String nom) {
		this.nom = nom;
	}
	public Prestation(int nbSem) {
		this.nbSem = nbSem;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getNbSem() {
		return this.nbSem;
	}

	public void setNbSem(int nbSem) {
		this.nbSem = nbSem;
	}

	public abstract int creerTarif(int nbSem);
}
