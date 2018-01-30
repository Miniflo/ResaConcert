package fr.eni.ResaConcert.ihm;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetrePrincipale extends JFrame {
	
	private JTextField txtRecherche;
	private JButton btnOK, btnReservations;
	private JMenuBar menuBar;
	private JMenu menuAccueil, menuReservation, menuClients;
	private JLabel txtSpectacle, txtLieu, txtDate;
	
	
	public FenetrePrincipale() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 400);
		setResizable(false);
		setTitle("Reservation Spectacle");
		initMenuBar();
		menuAccueil();
		
		setVisible(true);
	}
	
	private void initMenuBar(){
		menuBar = new JMenuBar();
		
		menuBar.add(getMenuAccueil());
		menuBar.add(getMenuReservation());
		menuBar.add(getMenuClients());
		
		menuBar.setVisible(true);
		setJMenuBar(menuBar);
		
		setContentPane(zoneRecherche());
	}
	

/*********************************** ACCUEIL ***********************************/		
	public JPanel menuAccueil(){
		JPanel panelAccueil = new JPanel();
		panelAccueil.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// Ligne 1
		gbc.gridx=0;
		gbc.gridy=0;
		panelAccueil.add(new JLabel("Liste des spectacles"), gbc);
		
		gbc.gridy = 1;
		panelAccueil.add(zoneRecherche(),gbc);
		
		
		gbc.gridy=2;
		panelAccueil.add(zoneRepetee(), gbc);
		gbc.gridy = 3;
		panelAccueil.add(zoneRepetee(), gbc);
		
		return panelAccueil;
	}
	
	public JPanel zoneRecherche(){
		JPanel panelRecherche = new JPanel();
		panelRecherche.setLayout(new FlowLayout());
		
		panelRecherche.add(new JLabel("Recherche par Artiste : "));
		panelRecherche.add(getTxtRecherche());
		panelRecherche.add(getBtnOK());
		
		return panelRecherche;
	}
	
	public JPanel zoneRepetee(){
		JPanel panelZoneRepetee = new JPanel();
		panelZoneRepetee.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Ligne 1
		gbc.gridx=0;
		gbc.gridy=0;
		panelZoneRepetee.add(getTxtSpectacle(), gbc);
		gbc.gridx = 1;
		gbc.gridheight = 2;
		panelZoneRepetee.add(getBtnReservations(),gbc);
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelZoneRepetee.add(this.getTxtLieu(),gbc);
		panelZoneRepetee.add(this.getTxtLieu(),gbc);
		
		return panelZoneRepetee;
	}
	
/*********************************** RESERVATIONS ***********************************/
	
	public void menuReservations(){
		JPanel panelReservations = new JPanel();
		panelReservations.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// Ligne 1
		gbc.gridx=0;
		gbc.gridy=0;
		
		
		setContentPane(panelReservations);
	}
	

/*********************************** JLabel ***********************************/	

	public JLabel getTxtSpectacle(){
		txtSpectacle = new JLabel("spectacle");
		return txtSpectacle;
	}
	
	public JLabel getTxtLieu(){
		txtLieu = new JLabel("lieu");
		return txtLieu;
	}
	
	public JLabel getTxtDate(){
		txtDate = new JLabel("date");
		return txtDate;
	}

/*********************************** JTextField ***********************************/
	public JTextField getTxtRecherche(){
		if (txtRecherche == null) {
			txtRecherche = new JTextField(20);
		}
		return txtRecherche;
	}

	
/*********************************** JButton ***********************************/
	public JButton getBtnOK(){
		if (btnOK == null) {
			btnOK = new JButton("OK");
		}
		return btnOK;
	}
	
	public JButton getBtnReservations(){
		btnReservations = new JButton("Reservations");
		return btnReservations;
	}
	
/*********************************** JMenu ***********************************/
	public JMenu getMenuAccueil(){
		if (menuAccueil == null) {
			menuAccueil = new JMenu("Accueil");
		}
		return menuAccueil;
	}
	
	public JMenu getMenuReservation(){
		if (menuReservation == null) {
			menuReservation = new JMenu("Réservations");
		}
		return menuReservation;
	}
	
	public JMenu getMenuClients(){
		if (menuClients == null) {
			menuClients = new JMenu("Clients");
		}
		return menuClients;
	}
	
	
	
	
}
