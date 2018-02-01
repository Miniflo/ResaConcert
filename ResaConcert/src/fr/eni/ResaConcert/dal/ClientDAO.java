package fr.eni.ResaConcert.dal;

import java.util.List;

import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.dal.DALException;

public interface ClientDAO {
	
	public Client selectById(int id) throws DALException;
	
	
	public List<Client> selectAll() throws DALException;


	
	public void insert(Client data) throws DALException;




 	
}
