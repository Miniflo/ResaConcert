package fr.eni.ResaConcert.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.eni.ResaConcert.bll.BLLException;
import fr.eni.ResaConcert.bll.ClientManager;
import fr.eni.ResaConcert.bo.Client;

public class FenetrePrincipale extends JFrame {

	int spectacleID;
	int clientID;
	
	public JPanel panelAccueil, panelReservations, panelClients, panelReservationLogIn, panelValidation;
	public GridBagConstraints gbcAccueil, gbcReservations, gbcClients;
	
	// Menu
	private JMenuBar menuBar;
	private JMenuItem menuAccueil, menuReservations, menuClients;
	
	// Accueil
	private JTextField txtAccRecherche;
	private JButton btnAccOK;
	private JLabel txtAccArtiste, txtAccSpectacle, txtAccLieu, txtAccDate;
	JButton[] tabBtnReservations = new JButton[100];
	int indexBtnReservations = 0;
	int indexRetourBtnReservations = 0;
	
	// Reservations
	private JLabel txtResNom, txtResPrenom, txtResMail;
	private JLabel txtResArtiste, txtResSpectacle, txtResLieu, txtResDate, txtResNbPlace;
	JButton[] tabBtnAnnuler = new JButton[100];
	int indexBtnAnnuler = 0;
	int indexRetourBtnAnnuler = 0;
	
	// Reservation login
	private JTextField fieldNom, fieldPrenom, fieldEmail, fieldAdresse, fieldCP, fieldVille;
	private JComboBox<Integer> comboPlaces, comboPlacesNew;
	private JComboBox<String> comboClients;
	private JButton btnValiderNew, btnValider;
	private JLabel spec;
	private JLabel info;
	private JLabel nbPlaces;
	
	// Clients
	JButton[] tabBtnReservationsCli = new JButton[100];
	int indexBtnReservationsCli = 0;
	int indexRetourBtnReservationsCli = 0;
	
	JButton[] tabBtnSupprimer = new JButton[100];
	int indexBtnSupprimer = 0;
	int indexRetourBtnSupprimer = 0;
	
	// Validation
	private JButton btnValAccueil;
	
	
	public FenetrePrincipale() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 400);
		setResizable(false);
		setTitle("Reservation Spectacle");
		initMenuBar();
		menuAccueil();
		menuReservations();
		menuClients();
		setContentPane(menuAccueil());
		
		Container contain = this.getContentPane();
		JScrollPane scroll = new JScrollPane(contain);
		this.setContentPane(scroll);
		
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
			//panelAccueil.setPreferredSize(new Dimension(800, 800));
			gbcAccueil = new GridBagConstraints();
			
			// Titre
			gbcAccueil.gridx=0;
			gbcAccueil.gridy=0;
			Font font = new Font("Arial",Font.BOLD,22);
			JLabel label = new JLabel("Listes des spectacles");
			label.setFont(font);
			panelAccueil.add(label,gbcAccueil);
			
			gbcAccueil.insets = new Insets(5, 5, 25, 5);
			
			// Zone de recherche 
			gbcAccueil.gridy = 1;
			panelAccueil.add(zoneRecherche(),gbcAccueil);
			final JScrollPane scroll = new JScrollPane(panelAccueil);
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


	public JPanel zoneRepeteeAccueil(int id, String spec, String info, int place){
		JPanel panelZoneRepetee = new JPanel();
		panelZoneRepetee.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Ligne spectacle
		gbc.gridx=0;
		gbc.gridy=0;
		panelZoneRepetee.add(new JLabel(spec), gbc);
		gbc.gridx = 1;
		gbc.gridheight = 2;
		gbc.insets = new Insets(10,150,10,10);
		panelZoneRepetee.add(getBtnResReservations(id, place),gbc);
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,0);
		
		// Ligne info
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelZoneRepetee.add(new JLabel(info),gbc);
		
		panelZoneRepetee.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		return panelZoneRepetee;
	}
	
	
/*********************************** Menu RESERVATIONS ***********************************/
	
	public JPanel menuReservations(){
		if (panelReservations == null) {
			panelReservations = new JPanel();
			panelReservations.setLayout(new GridBagLayout());
			gbcReservations = new GridBagConstraints();
			
			gbcReservations.insets = new Insets(25, 5, 5, 5);
			
			// Ligne 1
			gbcReservations.gridx=0;
			gbcReservations.gridy=0;
			Font font = new Font("Arial",Font.BOLD,22);
			JLabel res = new JLabel("Reservations");
			res.setFont(font);
			panelReservations.add(res);
		}
		return panelReservations;
	}
	
	public JPanel zoneRepeteeReservations(int id, String client,String spec, String info, int nbPlace){
		JPanel panelZoneRepetee = new JPanel();
		panelZoneRepetee.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Ligne 1
		gbc.gridx=0;
		gbc.gridy=0;
		panelZoneRepetee.add(new JLabel(client),gbc);
		gbc.gridx = 1;
		gbc.insets = new Insets(10,50,10,10);
		gbc.gridheight = 2;
		panelZoneRepetee.add(this.getBtnAnnuler(id),gbc);
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridheight = 1;
		panelZoneRepetee.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		// Ligne 2
		gbc.gridx=0;
		gbc.gridy=1;
		panelZoneRepetee.add(zoneReservationSpectacle(spec,info,nbPlace),gbc);
		
		return panelZoneRepetee;
	}
	
	public JPanel zoneReservationSpectacle(String spec, String date, int nbPlace){
		JPanel panelSpectacle = new JPanel();
		panelSpectacle.setLayout(new FlowLayout());
	
		panelSpectacle.add(this.getTxtResSpectacle(spec));
		panelSpectacle.add(this.getTxtResDateReservation(date));
		panelSpectacle.add(this.getTxtResNbPlace(String.valueOf(nbPlace)));
		panelSpectacle.add(new JLabel(" places"));
		
		return panelSpectacle;
	}
	
/*********************************** Menu CLIENTS ***********************************/
	
	public JPanel menuClients(){
		if (panelClients == null){
			panelClients = new JPanel();
			panelClients.setLayout(new GridBagLayout());
			gbcClients = new GridBagConstraints();
			
			gbcClients.insets = new Insets(5, 5, 5, 5);

			gbcClients.gridx=0;
			gbcClients.gridy=0;
			panelClients.add(new JLabel("Clients"));
		}
		return panelClients;
	}
	
	public JPanel zoneRepeteeClients(int id, String clients) {
		JPanel panelClient = new JPanel();		
		panelClient.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		
		panelClient.add(new JLabel(clients));
		panelClient.add(this.zoneRepeteeClientsBtn(id));
		
		panelClient.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
		
		return panelClient;
	}
	
	private JPanel zoneRepeteeClientsBtn(int id) {
		JPanel panelClient = new JPanel();		
		panelClient.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 10));
		
		panelClient.add(this.getBtnCliReservations(id));
		panelClient.add(this.getBtnCliSupprimer(id));

		return panelClient;
	}
	
	
/*********************************** Reservation LogIn * @throws BLLException ***********************************/
	
	public JPanel menuReservationLogIn(String spec, String info, int places, int specindex) throws BLLException{
		spectacleID = specindex+1;
		if (panelReservationLogIn == null){
			panelReservationLogIn = new JPanel();
			panelReservationLogIn.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			// Titre
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			panelReservationLogIn.add(zoneTitreReservationLogIn(),gbc);
			gbc.gridwidth = 1;
			
			gbc.insets = new Insets(5, 5, 5, 5);
			
			// Ligne 2
			gbc.gridy = 1;
			panelReservationLogIn.add(this.getTxtSpec(spec),gbc);
			gbc.gridx = 1;
			gbc.gridheight = 2;
			panelReservationLogIn.add(getTxtnbPlaces(String.valueOf(places)),gbc);
			gbc.gridheight = 1;
			
			// Ligne 3
			gbc.gridx = 0;
			gbc.gridy = 2;
			panelReservationLogIn.add(this.getTxtInfo(info),gbc);
					
			gbc.gridx = 0;
			gbc.gridy = 3;
			panelReservationLogIn.add(zoneNouveauClient(), gbc);
			gbc.gridx = 1;
			panelReservationLogIn.add(zoneConnexion(), gbc);
			
			
		}
		else{
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridy = 1;
			gbc.gridx = 0;
			gbc.gridwidth = 1;

			panelReservationLogIn.add(getTxtSpec(spec),gbc);
			gbc.gridx = 1;
			gbc.gridheight = 2;
			panelReservationLogIn.add(getTxtnbPlaces(String.valueOf(places)),gbc);
			gbc.gridheight = 1;
			gbc.gridx = 0;
			gbc.gridy = 2;
			panelReservationLogIn.add(this.getTxtInfo(info),gbc);
			
			
		}
		return panelReservationLogIn;
	}
	
	private JPanel zoneNbPlaces(int places){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Places disponibles : "));
		panel.add(new JLabel(String.valueOf(places)));
		return panel;
	}
	
	private JPanel zoneTitreReservationLogIn(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Font font = new Font("Arial",Font.BOLD,22);
		JLabel res = new JLabel("Reservation");
		res.setFont(font);
		panel.add(res);
		return panel;
	}
	
	private JPanel zoneConnexion() throws BLLException{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(40, 5, 5, 5);
		
		panel.setBorder(BorderFactory.createTitledBorder("Client existant"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panel.add(this.getComboClients(),gbc);
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Places : "), gbc);
		gbc.gridx = 1;
		panel.add(this.getComboPlaces(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panel.add(this.getBtnValider(), gbc);
		gbc.gridwidth = 1;
		
		return panel;
	}
	
	private JPanel zoneNouveauClient(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 0, 5);
		
		panel.setBorder(BorderFactory.createTitledBorder("Nouveau client"));
		
		// Nom
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Nom : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldNom(), gbc);
		
		// Prenom
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Prenom : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldPrenom(), gbc);
		
		// Email
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Email : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldEMail(), gbc);
		
		// Adresse
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Adresse : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldAdresse(), gbc);
		
		// Code Postal
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(new JLabel("Code Postal : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldCP(), gbc);
		
		// Ville
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(new JLabel("Ville : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getFieldVille(), gbc);
		
		// Nb places
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(new JLabel("Places : "),gbc);
		gbc.gridx = 1;
		panel.add(this.getComboPlacesNew(), gbc);
		
		// Valider
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		panel.add(this.getBtnValiderNew(),gbc);
		
		
		return panel;
	}
	
/*********************************** Menu validation ***********************************/

	public JPanel menuValidation(String spec, String info, int places, String num){	
		if (panelValidation == null){	
			panelValidation = new JPanel();
			panelValidation.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.insets = new Insets(10, 10, 10, 10);
			
			// Titre
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			panelValidation.add(zoneTextValidation("Reservation", 22),gbc);
			gbc.gridwidth = 1;		
		
			// Ligne 2
			gbc.gridy = 1;
			panelValidation.add(new JLabel(spec),gbc);
			gbc.gridx = 1;
			gbc.gridheight = 2;
			panelValidation.add(zoneNbPlaces(places),gbc);
			gbc.gridheight = 1;
			
			// Ligne 3
			gbc.gridx = 0;
			gbc.gridy = 2;
			panelValidation.add(new JLabel(info),gbc);
			
			// Ligne 4
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			panelValidation.add(zoneTextValidation("Reservation Effectuée !", 32),gbc);
			gbc.gridwidth = 1;
			
			// Ligne 5
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 2;
			panelValidation.add(zoneNumero(num),gbc);
			gbc.gridwidth = 1;
			
			// Ligne 6
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.gridwidth = 2;
			panelValidation.add(this.getBtnValidAccueil(),gbc);
			gbc.gridwidth = 1;
			
		}
		return panelValidation;
	}
	
	private JPanel zoneTextValidation(String text, int taille){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Font font = new Font("Arial",Font.BOLD,taille);
		JLabel res = new JLabel(text);
		res.setFont(font);
		panel.add(res);
		return panel;
	}
	
	private JPanel zoneNumero(String num){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Font font = new Font("Arial",Font.BOLD,20);
		
		JLabel text = new JLabel("Numéro de réservation : ");
		text.setFont(font);
		JLabel res = new JLabel(num);
		res.setFont(font);

		panel.add(text);
		panel.add(res);
		return panel;
	}
	
/*********************************** Methodes d'Affichage ***********************************/	

	public void changnerFen(JPanel panel){
		setContentPane(panel);
		Container contain = this.getContentPane();
		JScrollPane scroll = new JScrollPane(contain);
		this.setContentPane(scroll);
		repaint();
		revalidate();
	}	
	
/*********************************** JLabel ***********************************/	
	
	public JLabel getTxtnbPlaces(String text){
		if(nbPlaces==null){
			nbPlaces = new JLabel("Nombre de places : "+text);
		}
		else{
			nbPlaces.setText("Nombre de places : "+text);
		}
		return nbPlaces;
	}
	
	public JLabel getTxtInfo(String text){
		if(info==null){
			info = new JLabel(text);
		}
		else{
			info.setText(text);
		}
		return info;
	}
	public JLabel getTxtSpec(String text){
		if(spec==null){
			spec = new JLabel(text);
		}
		else{
			spec.setText(text);
		}
		return spec;
	}
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
	
	public JButton getBtnResReservations(int id, int place){
		if (place <= 0){
			tabBtnReservations[indexBtnReservations] = new JButton("Indisponible");
			tabBtnReservations[indexBtnReservations].setName(String.valueOf(id));
			tabBtnReservations[indexBtnReservations].setBackground(Color.RED);
			tabBtnReservations[indexBtnReservations].setEnabled(false);
		} else {
			tabBtnReservations[indexBtnReservations] = new JButton("Reservations");
			tabBtnReservations[indexBtnReservations].setName(String.valueOf(id));
			tabBtnReservations[indexBtnReservations].setBackground(Color.GREEN);
		}
		tabBtnReservations[indexBtnReservations].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indexRetourBtnReservations = Integer.parseInt(e.toString().substring(e.toString().length() - 2).trim());				
				try {
					Controller.getInstance().reserver(indexRetourBtnReservations);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		indexBtnReservations++;
		return tabBtnReservations[indexBtnReservations-1];
	}
	
	public JButton getBtnAnnuler(int id){
		tabBtnAnnuler[indexBtnAnnuler] = new JButton("Annuler");
		tabBtnAnnuler[indexBtnAnnuler].setName(String.valueOf(id));
		tabBtnAnnuler[indexBtnAnnuler].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indexRetourBtnAnnuler = Integer.parseInt(e.toString().substring(e.toString().length() - 2).trim());				
				try {
					Controller.getInstance().annuler(indexRetourBtnAnnuler);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		indexBtnAnnuler++;
		return tabBtnAnnuler[indexBtnAnnuler-1];
	}
	
	public JButton getBtnCliReservations(int id){
		tabBtnReservationsCli[indexBtnReservationsCli] = new JButton("Reservations");
		tabBtnReservationsCli[indexBtnReservationsCli].setName(String.valueOf(id));
		tabBtnReservationsCli[indexBtnReservationsCli].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indexRetourBtnReservationsCli = Integer.parseInt(e.toString().substring(e.toString().length() - 2).trim());				
				try {
					Controller.getInstance().reservationsClient(indexRetourBtnReservationsCli);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		indexBtnReservationsCli++;
		return tabBtnReservationsCli[indexBtnReservationsCli-1];
	}
	
	public JButton getBtnCliSupprimer(int id){
		tabBtnSupprimer[indexBtnSupprimer] = new JButton("Supprimer");
		tabBtnSupprimer[indexBtnSupprimer].setName(String.valueOf(id));
		tabBtnSupprimer[indexBtnSupprimer].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indexRetourBtnSupprimer = Integer.parseInt(e.toString().substring(e.toString().length() - 2).trim());				
				try {
					Controller.getInstance().supprimer(indexRetourBtnSupprimer);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		indexBtnSupprimer++;
		return tabBtnSupprimer[indexBtnSupprimer-1];
	}
	
	public JButton getBtnValider(){
		if (btnValider == null){
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						try {
							Controller.getInstance().valider(spectacleID);
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnValider;
	}
	
	public JButton getBtnValiderNew(){
		if (btnValiderNew == null){
			btnValiderNew = new JButton("Valider");
			btnValiderNew.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Controller.getInstance().validerNew(spectacleID);
					} catch (BLLException | NoSuchAlgorithmException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnValiderNew;
	}
	
	public JButton getBtnValidAccueil(){
		if (btnValAccueil == null){
			btnValAccueil = new JButton("Accueil");
			btnValAccueil.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Controller.getInstance().retourAccueil();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnValAccueil;
	}
	
/*********************************** JTextField ***********************************/
	
	
	
	public JTextField getFieldNom(){
		if (fieldNom == null){
			fieldNom = new JTextField(15);
		}
		return fieldNom;
	}
	
	public JTextField getFieldPrenom(){
		if (fieldPrenom == null){
			fieldPrenom = new JTextField(15);
		}
		return fieldPrenom;
	}
	
	public JTextField getFieldEMail(){
		if (fieldEmail == null){
			fieldEmail = new JTextField(15);
		}
		return fieldEmail;
	}
	
	public JTextField getFieldAdresse(){
		if (fieldAdresse == null){
			fieldAdresse = new JTextField(15);
		}
		return fieldAdresse;
	}
	
	public JTextField getFieldVille(){
		if (fieldVille == null){
			fieldVille = new JTextField(15);
		}
		return fieldVille;
	}
	
	public JTextField getFieldCP(){
		if (fieldCP == null){
			fieldCP = new JTextField(15);
		}
		return fieldCP;
	}

/*********************************** JComboBox ***********************************/	

	public JComboBox<Integer> getComboPlaces(){
		if (comboPlaces == null){
			Integer tab[] = {1,2,3,4,5,6,7,8,9};
			comboPlaces = new JComboBox<Integer>(tab);
		}
		return comboPlaces;
	}
	
	public JComboBox<Integer> getComboPlacesNew(){
		if (comboPlacesNew == null){
			Integer tab[] = {1,2,3,4,5,6,7,8,9};
			comboPlacesNew = new JComboBox<Integer>(tab);
		}
		return comboPlacesNew;
	}
	
	public JComboBox<String> getComboClients() throws BLLException{
		if (comboClients == null){
			String[] vListeClients = new String[ClientManager.getInstance().getClient().size()];
			List<Client> vListeClientele = ClientManager.getInstance().getClient();
			for(int i=0;i<vListeClientele.size();i++){
				vListeClients[i]=vListeClientele.get(i).getvNom()+" "+vListeClientele.get(i).getvPrenom()+" - "+vListeClientele.get(i).getvEmail();
				
			}
			comboClients = new JComboBox<String>(vListeClients);
			
			
		}
		return comboClients;
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
					try {
						Controller.getInstance().reservationsAll();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	
}
