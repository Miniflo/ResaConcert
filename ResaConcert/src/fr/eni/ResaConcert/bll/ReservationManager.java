package fr.eni.ResaConcert.bll;

import java.util.List;

import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.bo.Reservation;
import fr.eni.ResaConcert.dal.DALException;
import fr.eni.ResaConcert.dal.DAOFactory;
import fr.eni.ResaConcert.dal.ReservationDAO;

public class ReservationManager {
	private static ReservationDAO daoReservation;
	
	public ReservationManager() throws BLLException{
		daoReservation = DAOFactory.getReservationDAO();
	}
	
	public List<Reservation> getReservation() throws BLLException{
		List<Reservation> reservations = null;
		try{
			reservations = daoReservation.selectAll();
		} catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération réservation", e);
		}
		return reservations;
	}
	
	public void addReservation (Reservation newReservation) throws BLLException{
		try{
			daoReservation.insert(newReservation);
		}catch(DALException e) {
			throw new BLLException("Echec d'ajout de la reservation", e);
		}
	}
	
	public void removeReservation (Reservation reservation) throws BLLException{
		try{
			daoReservation.delete(reservation.getvCode_reservation());
		}catch(DALException e){
			throw new BLLException("Echec de la suppresion de la reservation.", e);
		}
	}
	
	public List<Reservation> getReservationByClient(int numero_Client) throws BLLException{
		List<Reservation> ResaByClient = null;
		try{
			ResaByClient = daoReservation.selectByClient(numero_Client);
		}catch(DALException e){
			throw new BLLException("Erreur récuperation réservation du client", e);
		}
		return ResaByClient;
	}
	
	
	
	

}
