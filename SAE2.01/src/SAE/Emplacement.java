package SAE;

public class Emplacement {
	private int numEmp;
	private static int nbEmp = 0;
	private Categorie cat;

	public Emplacement(Categorie cat) {
		nbEmp++;
		this.numEmp = nbEmp;
		this.cat = cat;
		cat.ajouterUnEmplacement(this);
	}

	public int getNumEmp() {
		return this.numEmp;
	}

	public void setNumEmp(int numEmp) {
		this.numEmp = numEmp;
	}

	public Categorie getCat() {
		return this.cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}
}
