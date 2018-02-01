package fr.eni.ResaConcert.ihm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;

import fr.eni.ResaConcert.bll.BLLException;
import fr.eni.ResaConcert.bll.ClientManager;
import fr.eni.ResaConcert.bll.ReservationManager;
import fr.eni.ResaConcert.bll.SpectacleManager;
import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.bo.Reservation;
import fr.eni.ResaConcert.bo.Spectacle;

public class Controller {
	
	private static Controller instance;
	private List<Client> clients;
	private List<Reservation> reservations;
	private List<Spectacle> spectacles;
	FenetrePrincipale fen;
	JPanel[] tabSpec = new JPanel[20];
	
	private Controller() throws BLLException{
		clients = ClientManager.getInstance().getClient();
		reservations = ReservationManager.getInstance().getReservation();
		spectacles = SpectacleManager.getInstance().getSpectacle();
	}
	
	public static Controller getInstance() throws BLLException{
		if ( Controller.instance == null){
			Controller.instance = new Controller();
		}
		return Controller.instance;
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
		while (i < spectacles.size()){
			fen.gbcAccueil.gridy++;
			String spec = spectacles.get(i).getvArtiste() + ", " + spectacles.get(i).getvTitre();
			String info = spectacles.get(i).getvLieu() + " / " + spectacles.get(i).getvDate();
			int nbPlace = spectacles.get(i).getvPlaces_disponibles();
			tabSpec[i] = fen.zoneRepeteeAccueil(i, spec, info, nbPlace);
			fen.panelAccueil.add(tabSpec[i],fen.gbcAccueil);
			i++;
		}
	}
	
	public void setReservations(){	
		int i = 0;
		while (i < reservations.size()-1){
			fen.gbcReservations.gridy++;
			int clientID = reservations.get(i).getvClient_id();
			String client = clients.get(clientID-1).getvPrenom() + " " + clients.get(clientID-1).getvNom() + " / " + clients.get(clientID-1).getvEmail();
			
			int specID = reservations.get(i).getvSpectacle_id();
			String spec = spectacles.get(specID-1).getvArtiste() + ", " + spectacles.get(specID-1).getvTitre();
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String info = df.format(reservations.get(i).getvDate_reservation());
			int nbPlace = reservations.get(i).getvNombre_places();
			fen.panelReservations.add(fen.zoneRepeteeReservations(i, client, spec, info, nbPlace),fen.gbcReservations);
			i++;
		}
	}
	
	public void setClients(){
		int i = 0;
		while (i<clients.size()-1){
			fen.gbcClients.gridy++;
			String client = clients.get(i).getvPrenom() + " " + clients.get(i).getvNom() + " / " +  clients.get(i).getvEmail();
			fen.panelClients.add(fen.zoneRepeteeClients(i,client),fen.gbcClients);
			i++;
		}
	}
	
	public void reserver(int specIndex){
		String spec = spectacles.get(specIndex).getvArtiste() + ", " + spectacles.get(specIndex).getvTitre();
		String info = spectacles.get(specIndex).getvLieu() + " / " + spectacles.get(specIndex).getvDate();
		int nbPlace = spectacles.get(specIndex).getvPlaces_disponibles();
		fen.changnerFen(fen.menuReservationLogIn(specIndex,spec, info, nbPlace));
	}
	
	public void supprimer(int index) throws BLLException{
		ClientManager.getInstance().deleteClient(index);
		clients = ClientManager.getInstance().getClient();
	}
	
	public void annuler(int index) throws BLLException{
		ReservationManager.getInstance().deleteReservation(String.valueOf(index));
		reservations = ReservationManager.getInstance().getReservation();
	}
	
	public void reservationsClient(int index) throws BLLException{
		reservations = ReservationManager.getInstance().getReservationByClient(index);
		setReservations();
		fen.changnerFen(fen.panelReservations);
	}
	
	public void validerNew() throws BLLException{
		ajouterClient();
		System.out.println("Creation reservation");
		fen.changnerFen(fen.menuValidation("spec", "info", 50, "ABCDEF"));
	}
	
	public void valider(int spectacleID){
		System.out.println("ID spectacle" + spectacleID);
		System.out.println("Creation reservation");
		fen.changnerFen(fen.menuValidation("spec", "info", 50, "ABCDEF"));
	}
	
	public void retourAccueil(){
		fen.changnerFen(fen.menuAccueil());
	}
	
	private void ajouterClient() throws BLLException{
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
	
	public void reservationsAll() throws BLLException{
		fen.panelReservations = null;
		clients = ClientManager.getInstance().getClient();
		setReservations();
		fen.changnerFen(fen.menuReservations());
	}
	
}
