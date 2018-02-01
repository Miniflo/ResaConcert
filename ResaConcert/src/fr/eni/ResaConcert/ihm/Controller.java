package fr.eni.ResaConcert.ihm;

import javax.swing.JPanel;

public class Controller {
	
	private static Controller instance;
	FenetrePrincipale fen;
	
	JPanel[] spectacles = new JPanel[20];
	
	private Controller(){
		
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
			String spec = "Johnny Hallyday" + ", " + "good bye tour";
			String info = "Nantes" + " / " + "31-01-18";
			spectacles[i] = fen.zoneRepeteeAccueil(i, spec, info, 20);
			fen.panelAccueil.add(spectacles[i],fen.gbcAccueil);
			i++;
		}
	}
	
	public void setReservations(){	
		int i = 0;
		while (i < 3){
			fen.gbcReservations.gridy++;
			String client = "Mickaël" + " " + "VIAUD" + " / " +  "mickael.viaud@gmail.com";
			String spec = "Johnny Hallyday" + ", " + "good bye tour";
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
		System.out.println("supprimer index : " + index);
	}
	
	public void annuler(int index){
		System.out.println("annuler index : " + index);
	}
	
	public void ReservationsClient(int index){
		System.out.println("reservations client index : " + index);
		fen.changnerFen(fen.panelReservations);
	}
	
}
