package fr.eni.ResaConcert.bo;

public class Client {

	private int ID;
	private String vNom;
	private String vPrenom;
	private String vEmail;
	private String vAdresse;
	private String vCode_postal;
	private String vVille;
	public Client(int iD, String vNom, String vPrenom, String vEmail, String vAdresse, String vCode_postal,
			String vVille) {
		super();
		ID = iD;
		this.vNom = vNom;
		this.vPrenom = vPrenom;
		this.vEmail = vEmail;
		this.vAdresse = vAdresse;
		this.vCode_postal = vCode_postal;
		this.vVille = vVille;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getvNom() {
		return vNom;
	}
	public void setvNom(String vNom) {
		this.vNom = vNom;
	}
	public String getvPrenom() {
		return vPrenom;
	}
	public void setvPrenom(String vPrenom) {
		this.vPrenom = vPrenom;
	}
	public String getvEmail() {
		return vEmail;
	}
	public void setvEmail(String vEmail) {
		this.vEmail = vEmail;
	}
	public String getvAdresse() {
		return vAdresse;
	}
	public void setvAdresse(String vAdresse) {
		this.vAdresse = vAdresse;
	}
	public String getvCode_postal() {
		return vCode_postal;
	}
	public void setvCode_postal(String vCode_postal) {
		this.vCode_postal = vCode_postal;
	}
	public String getvVille() {
		return vVille;
	}
	public void setvVille(String vVille) {
		this.vVille = vVille;
	}
	
	
}
