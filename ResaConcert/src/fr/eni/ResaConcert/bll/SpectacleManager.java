package fr.eni.ResaConcert.bll;

import java.util.List;

import fr.eni.ResaConcert.bo.Spectacle;
import fr.eni.ResaConcert.dal.ClientDAO;
import fr.eni.ResaConcert.dal.DALException;
import fr.eni.ResaConcert.dal.DAOFactory;
import fr.eni.ResaConcert.dal.SpectacleDAO;

public class SpectacleManager {

	
	
	private static SpectacleDAO daoSpectacle;
	
	private static SpectacleManager instance;
	
	public static SpectacleManager getInstance() throws BLLException {
        if (null == instance) { // Premier appel
                if (null == instance) {
                    instance = new SpectacleManager();
                }
        }
        return instance;
    }
	
	public SpectacleManager() throws BLLException{
		daoSpectacle = DAOFactory.getSpectacleDAO();
	}

	public Spectacle getSpectacle(int id)throws BLLException{
		Spectacle spectacle = null;
	try{
		spectacle = daoSpectacle.selectById(id);
	}catch(DALException e){
		e.printStackTrace();
		throw new BLLException("Erreur récupération spectacle",e);
	}
	return spectacle;
	}
	
	public List<Spectacle> getSpectacle()throws BLLException{
		List<Spectacle> spectacle = null;
	try{
		spectacle = daoSpectacle.selectAll();
	}catch(DALException e){
		e.printStackTrace();
		throw new BLLException("Erreur récupération spectacle",e);
	}
	return spectacle;
	}
	
	public void updateSpectacle(Spectacle spectacle, int nb_places) throws BLLException{
		try{
			daoSpectacle.update_nb_places(spectacle, nb_places);
		}catch(DALException e){
			throw new BLLException("Erreur Mise a jour places disponibles",e);
		}
	}
}
