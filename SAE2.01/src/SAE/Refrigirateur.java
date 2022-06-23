package SAE;

public class Refrigirateur extends Prestation {
	private final int tarifSemaine = 35;

	public Refrigirateur(int nbSem) {
		super(nbSem);
	}

	public Refrigirateur(String nom) {
		super(nom);
	}

	public int creerTarif(int nbSem) {
		return nbSem*tarifSemaine;
	}
}
