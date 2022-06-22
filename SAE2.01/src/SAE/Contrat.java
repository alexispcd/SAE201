package SAE;

import java.time.LocalDate;

public class Contrat {
	private static int nbContrat = 0;
	private int numContrat;
	private LocalDate dDebut;
	private LocalDate dFin;
	private double accompte;
	private Emplacement emp;
	private int numEmp;
	private int  numCli;
	private String nomCli;
	private Client cli;
	private String etat;


	public Contrat(LocalDate dateDebut, LocalDate dateFin, double accompte, Emplacement emp, Client cli) {
		nbContrat++;
		this.numContrat = nbContrat;
		this.dDebut = dateDebut;
		this.dFin = dateFin;
		this.accompte = accompte;
		this.emp = emp;
		this.cli = cli;
		this.numEmp = emp.getNumEmp();
		this.numCli = cli.getNumCli();
		this.nomCli = cli.getNom()+" "+cli.getPrenom();
	}

	public LocalDate getDDebut() {
		return this.dDebut;
	}

	public void setDDebut(LocalDate dDebut) {
		this.dDebut = dDebut;
	}

	public LocalDate getDFin() {
		return this.dFin;
	}

	public void setDFin(LocalDate dFin) {
		this.dFin = dFin;
	}

	public String getNomCli() {
		return this.nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public int getNumEmp() {
		return this.numEmp;
	}

	public void setNumEmp(int numEmp) {
		this.numEmp = numEmp;
	}

	public int getNumCli() {
		return this.numCli;
	}

	public void setNumCli(int numCli) {
		this.numCli = numCli;
	}

	public int getNumContrat() {
		return this.numContrat;
	}

	public void setNumContrat(int numContrat) {
		this.numContrat = numContrat;
	}

	public double getAccompte() {
		return this.accompte;
	}

	public void setAccompte(float accompte) {
		this.accompte = accompte;
	}

	public Emplacement getEmp() {
		return this.emp;
	}

	public void setEmp(Emplacement emp) {
		this.emp = emp;
	}

	public Client getCli() {
		return this.cli;
	}

	public void setCli(Client cli) {
		this.cli = cli;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
