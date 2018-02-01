package fr.eni.ResaConcert.ihm;

import java.util.List;
import javax.swing.JPanel;

public class Controller {
	
	private static Controller instance;
	private List<Client> clients;
	private List<Reservation> reservations;
	private List<Spectacle> spectacles;
	FenetrePrincipale fen;
	JPanel[] tabSpec = new JPanel[20];
	
	public static Controller getInstance(){
		if ( Controller.instance == null){
			Controller.instance = new Controller();
		}
		return Controller.instance;
	}
	
	public static synchronized Controller get(){
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp(){
		fen = new FenetrePrincipale();
		setSpectacleAccueil();
		setReservations();
		setClients();
		fen.setVisible(true);
	}
	
	public void setSpectacleAccueil(){
		int i = 0;
		while (i < 3){
			fen.gbcAccueil.gridy++;
			String spec = "Johnny Haliday" + ", " + "good bye tour";
			String info = "Nantes" + " / " + "31-01-18";
			tabSpec[i] = fen.zoneRepeteeAccueil(i, spec, info, 20);
			fen.panelAccueil.add(tabSpec[i],fen.gbcAccueil);
			i++;
		}
	}
	
	public void setReservations(){	
		int i = 0;
		while (i < 3){
			fen.gbcReservations.gridy++;
			String client = "Mickaël" + " " + "VIAUD" + " / " +  "mickael.viaud@gmail.com";
			String spec = "Johnny Haliday" + ", " + "good bye tour";
			String info = "31-01-18";
			fen.panelReservations.add(fen.zoneRepeteeReservations(i, client, spec, info, 2),fen.gbcReservations);
			i++;
		}
	}
	
	public void setClients(){
		int i = 0;
		while (i<3){
			fen.gbcClients.gridy++;
			String client = "Mickaël" + " " + "VIAUD" + " / " +  "mickael.viaud@gmail.com";
			fen.panelClients.add(fen.zoneRepeteeClients(i,client),fen.gbcClients);
			i++;
		}
	}
	
	public void reserver(int index){
		fen.changnerFen(fen.menuReservationLogIn("spec", "info", 50));
	}
	
	public void supprimer(int index){
		ClientManager.getInstance().removeClient(clients.get(index));
		Clients = ClientManager.getInstance().getClient();
	}
	
	public void annuler(int index){
		ReservationManager.getInstance().remove(reservations.get(index));
		reservations = ReservationManager.getInstance().getReservation();
	}
	
	public void reservationsClient(int index){
		System.out.println("reservations client index : " + index);
		fen.changnerFen(fen.panelReservations);
	}
	
	public void validerNew(){
		ajouterClient();
		System.out.println("Creation reservation");
		fen.changnerFen(fen.menuValidation("spec", "info", 50, "ABCDEF"));
	}
	
	public void valider(){
		System.out.println("Creation reservation");
		fen.changnerFen(fen.menuValidation("spec", "info", 50, "ABCDEF"));
	}
	
	public void retourAccueil(){
		fen.changnerFen(fen.menuAccueil());
	}
	
	private void ajouterClient(){
		String nom = fen.getFieldNom().getText();
		String prenom = fen.getFieldPrenom().getText();
		String email = fen.getFieldEMail().getText();
		String adresse = fen.getFieldAdresse().getText();
		String CP = fen.getFieldCP().getText();
		String ville = fen.getFieldVille().getText();
		Client newClient = new Client(nom,prenom,email,adresse,CP,ville);
		ClientManager.getInstance().addClient(newClient);
		clients = ClientManager.getInstance().getClient();
	}
	
}
