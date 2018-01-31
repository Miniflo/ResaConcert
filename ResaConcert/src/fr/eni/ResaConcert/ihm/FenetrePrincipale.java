package fr.eni.ResaConcert.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetrePrincipale extends JFrame {
	
	public JPanel panelAccueil, panelReservations;
	public GridBagConstraints gbcAccueil, gbcReservations, gbcClients;
	
	// Menu
	private JMenuBar menuBar;
	private JMenuItem menuAccueil, menuReservations, menuClients;
	
	// Accueil
	private JTextField txtAccRecherche;
	private JButton btnAccOK;
	private JLabel txtAccArtiste, txtAccSpectacle, txtAccLieu, txtAccDate;
	
	// Reservations
	private JButton btnResReservations, btnResAnnuler;
	private JLabel txtResNom, txtResPrenom, txtResMail;
	private JLabel txtResArtiste, txtResSpectacle, txtResLieu, txtResDate, txtResNbPlace;
	
	// Clients
	private JLabel txtCliNom, txtCliPrenom, txtCliMail;
	private JButton btnCliReservations, btnCliSupprimer;
	
	
	public FenetrePrincipale() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 400);
		setResizable(false);
		setTitle("Reservation Spectacle");
		initMenuBar();
		menuAccueil();
		menuReservations();
		setContentPane(menuAccueil());
		setVisible(true);
	}
	
	private void initMenuBar(){
		menuBar = new JMenuBar();
		
		menuBar.add(getMenuAccueil());
		menuBar.add(getMenuReservations());
		menuBar.add(getMenuClients());
		
		menuBar.setVisible(true);
		setJMenuBar(menuBar);
	}
	

/*********************************** Menu ACCUEIL ***********************************/		
	public JPanel menuAccueil(){
		if (panelAccueil == null){
			panelAccueil = new JPanel();
			panelAccueil.setLayout(new GridBagLayout());
			gbcAccueil = new GridBagConstraints();
	
			// Titre
			gbcAccueil.gridx=0;
			gbcAccueil.gridy=0;
			panelAccueil.add(new JLabel("Liste des spectacles"), gbcAccueil);
			
			gbcAccueil.insets = new Insets(30, 5, 5, 5);
			
			// Zone de recherche 
			gbcAccueil.gridy = 1;
			panelAccueil.add(zoneRecherche(),gbcAccueil);
		}
		return panelAccueil;
	}
	
	public JPanel zoneRecherche(){
		JPanel panelRecherche = new JPanel();
		panelRecherche.setLayout(new FlowLayout());
		
		panelRecherche.add(new JLabel("Recherche par Artiste : "));
		panelRecherche.add(getTxtAccRecherche());
		panelRecherche.add(getBtnAccOK());
		
		return panelRecherche;
	}


	public JPanel zoneRepeteeAccueil(String spec, String art, String lieu, String date){
		JPanel panelZoneRepetee = new JPanel();
		panelZoneRepetee.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Ligne spectacle
		gbc.gridx=0;
		gbc.gridy=0;
		panelZoneRepetee.add(zoneSpectacle(spec, art), gbc);
		gbc.gridx = 1;
		gbc.gridheight = 2;
		gbc.insets = new Insets(10,150,10,10);
		panelZoneRepetee.add(getBtnResReservations(),gbc);
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,0);
		
		// Ligne info
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelZoneRepetee.add(this.zoneInfo(lieu, date),gbc);
		
		panelZoneRepetee.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		return panelZoneRepetee;
	}
	
	private JPanel zoneSpectacle(String spec, String art){
		JPanel panelSpectacle = new JPanel();
		panelSpectacle.setLayout(new FlowLayout());
		
		panelSpectacle.add(getTxtAccArtiste(art));
		panelSpectacle.add(getTxtAccSpectacle(spec));

		return panelSpectacle;
	}
	
	private JPanel zoneInfo(String lieu, String date){
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new FlowLayout());
		
		panelInfo.add(getTxtAccLieu(lieu));
		panelInfo.add(getTxtAccDate(date));

		return panelInfo;
	}
	
	
/*********************************** Menu RESERVATIONS ***********************************/
	
	public JPanel menuReservations(){
		if (panelReservations == null) {
			panelReservations = new JPanel();
			panelReservations.setLayout(new GridBagLayout());
			gbcReservations = new GridBagConstraints();
			
			gbcReservations.insets = new Insets(5, 5, 5, 5);
			
			// Ligne 1
			gbcReservations.gridx=0;
			gbcReservations.gridy=0;
			panelReservations.add(new JLabel("Réservations"),gbcReservations);
		}
		return panelReservations;
	}
	
	public JPanel zoneRepeteeReservations(String nom, String prenom, String mail, String art, String spec, String date, int nbPlace){
		JPanel panelZoneRepetee = new JPanel();
		panelZoneRepetee.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Ligne 1
		gbc.gridx=0;
		gbc.gridy=0;
		panelZoneRepetee.add(zoneReservationPersonne(nom, prenom,mail),gbc);
		gbc.gridx = 1;
		gbc.insets = new Insets(10,50,10,10);
		gbc.gridheight = 2;
		panelZoneRepetee.add(this.getBtnAnnuler(),gbc);
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridheight = 1;
		panelZoneRepetee.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		// Ligne 2
		gbc.gridx=0;
		gbc.gridy=1;
		panelZoneRepetee.add(zoneReservationSpectacle(art,spec,date,nbPlace),gbc);
		
		return panelZoneRepetee;
	}
	
	public JPanel zoneReservationPersonne(String nom, String prenom, String mail){
		JPanel panelPersonne = new JPanel();
		panelPersonne.setLayout(new FlowLayout());
		
		panelPersonne.add(this.getTxtResNom(nom));
		panelPersonne.add(this.getTxtResPrenom(prenom));
		panelPersonne.add(new JLabel(" / "));
		panelPersonne.add(this.getTxtResMail(mail));
		
		return panelPersonne;
	}
	
	public JPanel zoneReservationSpectacle(String art, String spec, String date, int nbPlace){
		JPanel panelSpectacle = new JPanel();
		panelSpectacle.setLayout(new FlowLayout());
		
		panelSpectacle.add(this.getTxtResArtiste(art));
		panelSpectacle.add(this.getTxtResSpectacle(spec));
		panelSpectacle.add(this.getTxtResDateReservation(date));
		panelSpectacle.add(this.getTxtResNbPlace(String.valueOf(nbPlace)));
		panelSpectacle.add(new JLabel(" places"));
		
		return panelSpectacle;
	}
	
/*********************************** Menu CLIENTS ***********************************/
	
	public JPanel menuClients(){
		JPanel panelClients = new JPanel();
		panelClients.setLayout(new GridBagLayout());
		gbcClients = new GridBagConstraints();
		
		gbcClients.insets = new Insets(5, 5, 5, 5);
		
		// Ligne 1
		gbcClients.gridx=0;
		gbcClients.gridy=0;
		panelClients.add(new JLabel("Clients"));
		
		return panelClients;
	}
	

	private JPanel zoneRepeteeClients() {
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 10));
		
		panelClient.add(this.getTxtCliNom());
		panelClient.add(this.getTxtCliPrenom());
		panelClient.add(new JLabel(" / "));
		panelClient.add(this.getTxtCliMail());
		panelClient.add(this.getBtnCliReservations());
		panelClient.add(this.getBtnCliSupprimer());
		
		panelClient.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		return panelClient;
	}

/*********************************** Methodes Affichage ***********************************/	

	
	
/*********************************** JLabel ***********************************/	

	public JLabel getTxtAccArtiste(String text){
		txtAccArtiste = new JLabel(text);
		return txtAccArtiste;
	}
	
	public JLabel getTxtAccSpectacle(String text){
		txtAccSpectacle = new JLabel(text);
		return txtAccSpectacle;
	}
	
	public JLabel getTxtAccLieu(String text){
		txtAccLieu = new JLabel(text);
		return txtAccLieu;
	}
	
	public JLabel getTxtAccDate(String text){
		txtAccDate = new JLabel(text);
		return txtAccDate;
	}
	
	public JLabel getTxtResNom(String text){
		txtResNom = new JLabel(text);
		return txtResNom;
	}
	
	public JLabel getTxtResPrenom(String text){
		txtResPrenom = new JLabel(text);
		return txtResPrenom;
	}
	
	public JLabel getTxtResMail(String text){
		txtResMail = new JLabel(text);
		return txtResMail;
	}
	
	public JLabel getTxtResArtiste(String text){
		txtResArtiste = new JLabel(text);
		return txtResArtiste;
	}
	
	public JLabel getTxtResSpectacle(String text){
		txtResSpectacle = new JLabel(text);
		return txtResSpectacle;
	}
	
	public JLabel getTxtResLieu(String text){
		txtResLieu = new JLabel(text);
		return txtResLieu;
	}
	
	public JLabel getTxtResDateReservation(String text){
		txtResDate = new JLabel(text);
		return txtResDate;
	}
	
	public JLabel getTxtResNbPlace(String text){
		txtResNbPlace = new JLabel(text);
		return txtResNbPlace;
	}
	
	public JLabel getTxtCliNom(){
		txtCliNom = new JLabel("Nom");
		return txtCliNom;
	}
	
	public JLabel getTxtCliPrenom(){
		txtCliPrenom = new JLabel("Prenom");
		return txtCliPrenom;
	}
	
	public JLabel getTxtCliMail(){
		txtCliMail = new JLabel("Mail");
		return txtCliMail;
	}

/*********************************** JTextField ***********************************/
	public JTextField getTxtAccRecherche(){
		if (txtAccRecherche == null) {
			txtAccRecherche = new JTextField(20);
		}
		return txtAccRecherche;
	}

	
/*********************************** JButton ***********************************/
	public JButton getBtnAccOK(){
		if (btnAccOK == null){
			btnAccOK = new JButton("OK");
		}
		return btnAccOK;
	}
	
	public JButton getBtnResReservations(){
		btnResReservations = new JButton("Reservations");
		return btnResReservations;
	}
	
	public JButton getBtnAnnuler(){
		btnResAnnuler = new JButton("Annuler");
		return btnResAnnuler;
	}
	
	public JButton getBtnCliReservations(){
		btnCliReservations = new JButton("Reservations");
		return btnCliReservations;
	}
	
	public JButton getBtnCliSupprimer(){
		btnCliSupprimer = new JButton("Supprimer");
		return btnCliSupprimer;
	}
	
/*********************************** JMenu ***********************************/
	public JMenuItem getMenuAccueil(){
		if (menuAccueil == null) {
			menuAccueil = new JMenuItem("Accueil");
			menuAccueil.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					changnerFen(menuAccueil());
				}

			});
		}
		return menuAccueil;
	}
	
	public JMenuItem getMenuReservations(){
		if (menuReservations == null) {
			menuReservations = new JMenuItem("Réservations");
			menuReservations.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					changnerFen(menuReservations());
				}

			});
		}
		return menuReservations;
	}
	
	public JMenuItem getMenuClients(){
		if (menuClients == null) {
			menuClients = new JMenuItem("Clients");
			menuClients.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					changnerFen(menuClients());
				}

			});
		}
		return menuClients;
	}
	
	public void changnerFen(JPanel panel){
		setContentPane(panel);
		repaint();
		revalidate();
	}
	
	
}
