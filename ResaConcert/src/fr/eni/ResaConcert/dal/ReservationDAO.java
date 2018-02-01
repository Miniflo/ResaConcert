package fr.eni.ResaConcert.dal;

import java.util.List;

import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.bo.Reservation;

public interface ReservationDAO {


	//Selectionner une reservation par son id
	public Reservation selectById(int id) throws DALException;
	
	//Selectionner toutes les reservations
	public List<Reservation> selectAll() throws DALException;

	//Modifier la reservation mis en parametre
	public void update(Reservation data) throws DALException;

	//Inserer la reservation mis en parametre
	public void insert(Reservation data) throws DALException;

	//Supprimer la reservation mis en parametre
 	public void delete(String vCode_Reservation) throws DALException;
 	
 	//Selectionner une reservation par un client
 	public Reservation selectByClient(Client client) throws DALException;
 	
}
