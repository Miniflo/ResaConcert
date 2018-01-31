package fr.eni.ResaConcert.dal;

import java.util.List;

import fr.eni.ResaConcert.bo.Client;

public interface ClientDAO {

	//Selectionner un client par son id
	public Client selectById(int id) throws DALException;
	
	//Selectionner tous les clients
	public List<Client> selectAll() throws DALException;

	//Modifier le client mis en parametre
	public void update(Client data) throws DALException;

	//Inserer le client mis en parametre
	public void insert(Client data) throws DALException;

	//Supprimer le client mis en parametre
 	public void delete(int id) throws DALException;


 	
}
