package SAE;

public class Raccordement extends Prestation {
	private final int tarifSemaine = 21;

	public Raccordement(int nbSem) {
		super(nbSem);
	}

	public Raccordement(String nom) {
		super(nom);
	}

	public int creerTarif(int nbSem) {
		return nbSem*tarifSemaine;
	}
}
