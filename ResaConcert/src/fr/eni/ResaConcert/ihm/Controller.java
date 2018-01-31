package fr.eni.ResaConcert.ihm;

import javax.swing.JPanel;

public class Controller {
	
	private static Controller instance;
	FenetrePrincipale fen;
	
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
		fen.setVisible(true);
	}
	
	public void setSpectacleAccueil(){
		int i = 0;
		while (i < 2){
			fen.gbcAccueil.gridy++;
			fen.panelAccueil.add(fen.zoneRepeteeAccueil("Johnny", "Haliday", "Nantes", "31/01/18"),fen.gbcAccueil);
			i++;
		}
	}
	
	public void setReservations(){	
		int i = 0;
		while (i < 3){
			fen.gbcReservations.gridy++;
			fen.panelReservations.add(fen.zoneRepeteeReservations("jean", "pierre", "abc@abc.com", "johnny haliday", "spectacle johnny", "31/01/2018", 2),fen.gbcReservations);
			i++;
		}
	}
	
	public void Reserver(){
		
	}
	
	public void supprimer(){
		
	}
	
	public void annuler(){
		
	}
	
	public void reservations(){
		
	}
}
