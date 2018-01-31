package fr.eni.ResaConcert.dal;


public class DAOFactory {
	
	public static ClientDAO getClientDAO()  {
		ClientDAO clientDAO=null;
		try {
			clientDAO=(ClientDAO ) Class.forName("fr.eni.ResaConcert.dal.jdbc.ClientDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientDAO; 
	}
	
	
	
	public static SpectacleDAO getSpectacleDAO()  {
	SpectacleDAO spectacleDAO=null;
	try {
		spectacleDAO=(SpectacleDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.SpectacleDAOJdbcImpl").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return spectacleDAO; 
	}
	
		public static ReservationDAO getReservationDAO()  {
	ReservationDAO articleDAO=null;
	try {
		articleDAO=(ReservationDAO ) Class.forName("fr.eni.papeterie.dal.jdbc.ReservationDAOJdbcImpl").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return articleDAO; 
}

}
