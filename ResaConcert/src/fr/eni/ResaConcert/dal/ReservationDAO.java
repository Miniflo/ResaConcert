package fr.eni.ResaConcert.dal;

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
 	public void delete(int id) throws DALException;
 	
}
