package SAE;

public class PersonneSup extends Prestation {
	private int tarifSemaine;
	private int age;

	public PersonneSup(int nbSem, int age) {
		super(nbSem);
		this.age = age;
	}

	public PersonneSup(String nom) {
		super(nom);
	}

	public int creerTarif(int nbSem) {
		if (this.age > 7) {
			this.tarifSemaine = 28;
		} else if (this.age > 2) {
			this.tarifSemaine = 14;
		} else {
			this.tarifSemaine = 0;
		}
		return nbSem*tarifSemaine;
	}
}
