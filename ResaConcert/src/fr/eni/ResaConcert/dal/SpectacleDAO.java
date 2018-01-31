package fr.eni.ResaConcert.dal;

public interface SpectacleDAO {

	//Selectionner un spectacle par son id
	public  Spectacle selectById(int id) throws DALException;
	
	//Selectionner tous les spectacles
	public List<Spectacle> selectAll() throws DALException;

	//Modifier le spectacle mis en parametre
	public void update(Spectacle data) throws DALException;

	//Inserer le spectacle mis en parametre
	public void insert(Spectacle data) throws DALException;

	//Supprimer le spectacle mis en parametre
 	public void delete(int id) throws DALException;

}
