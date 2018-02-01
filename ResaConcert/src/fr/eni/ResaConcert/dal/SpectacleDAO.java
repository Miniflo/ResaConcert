package fr.eni.ResaConcert.dal;

import java.util.List;

import fr.eni.ResaConcert.bo.Spectacle;
import pn.DALException;

public interface SpectacleDAO {


	//Selectionner un spectacle par son id
	public  Spectacle selectById(int id) throws DALException;
	
	//Selectionner tous les spectacles
	public List<Spectacle> selectAll() throws DALException;

	//Modifier le spectacle mis en parametre
	public void update_nb_places(Spectacle spectacle, int nb_places) throws DALException;



}
