package fr.eni.ResaConcert.bll;


import fr.eni.ResaConcert.dal.*;
import fr.eni.ResaConcert.bo.*;
import java.util.List;

public class ClientManager {

	private static ClientDAO daoClient;
	
	private static ClientManager instance;
	
	public static ClientManager getInstance() throws BLLException {
        if (null == instance) { // Premier appel
                if (null == instance) {
                    instance = new ClientManager();
                }
        }
        return instance;
    }
	
	
	
	public ClientManager() throws BLLException{
		daoClient = DAOFactory.getClientDAO();
		
	}
	
	
	public Client getClientById(int id) throws BLLException{
		Client clients = null;
		try {
			clients = daoClient.selectById(id);
		} catch (DALException e){
			e.printStackTrace();
			throw new BLLException("Erreur récupération Client", e);
		}
		return clients;
	}
	
	
	public List<Client> getClient() throws BLLException{
		List<Client> clients = null;
		try {
			clients = daoClient.selectAll();
		} catch (DALException e){
			e.printStackTrace();
			throw new BLLException("Erreur récupération Client", e);
		}
		return clients;
	}
	
	public int addClient(Client newClient) throws BLLException {
		if(newClient.getClass()==null){
			throw new BLLException("Client deja existant.");
		}
		try {
			validerClient(newClient);
			int IDClient = daoClient.insert(newClient);
			return IDClient;
		} catch (DALException e){
			throw new BLLException("Echec d'ajout du client.", e);
		}
	}
	
	public void deleteClient(int id) throws BLLException {
		try {
			daoClient.delete(id);
		} catch (DALException e){
			throw new BLLException("Echec de suppression du client.", e);
		}
	}
	
	
	public void validerClient(Client c) throws BLLException{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(c.getvNom()==null || c.getvNom().trim().length()==0){
			sb.append("Le nom est obligatoire.\n");
			valide = false;
	}
		if(c.getvPrenom()==null || c.getvPrenom().trim().length()==0){
			sb.append("Le prénom est obligatoire.\n");
			valide = false;
	}
		if(c.getvEmail()==null || c.getvEmail().trim().length()==0){
			sb.append("L'Email est obligatoire.\n");
			valide = false;
	}
		if(c.getvAdresse()==null || c.getvAdresse().trim().length()==0){
			sb.append("L'Adresse est obligatoire.\n");
			valide = false;
		}
		if(c.getvCode_postal()==null || c.getvCode_postal().trim().length()==0){
			sb.append("Le code postal est obligatoire.\n");
			valide = false;
		}
		if(c.getvVille()==null || c.getvVille().trim().length()==0){
			sb.append("La ville est obligatoire.\n");
			valide = false;
		}
		
	if(!valide){
		throw new BLLException(sb.toString());
	}
	}
}
