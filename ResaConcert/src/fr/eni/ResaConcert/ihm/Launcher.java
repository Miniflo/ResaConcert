package fr.eni.ResaConcert.ihm;

import javax.swing.SwingUtilities;

import fr.eni.ResaConcert.bll.BLLException;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				try {
					Controller.getInstance().startApp();
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
	}


}
