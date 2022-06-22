package SAE;

public class Client {
	private static int nbCli = 0;
	private int numCli;
	private String nom;
	private String prenom;
	private String adresse;
	private String civilite;
	private int tel;
	private String mail;
	private String ville;
	private int codePostal;
	private String pays;

	public Client(String nom, String prenom, String adresse, String civilite, int tel, String mail, String ville, int codePostal, String pays) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.civilite = civilite;
		this.tel = tel;
		this.mail = mail;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
		nbCli++;
		this.numCli = nbCli;
	}


	public int getNumCli() {
		return this.numCli;
	}

	public void setNumCli(int numCli) {
		this.numCli = numCli;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public int getTel() {
		return this.tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
