package fr.eni.ResaConcert.ihm;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				Controller.get().startApp();
			}
			
		});
		
	}


}
