package fr.eni.ResaConcert.dal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.bo.Spectacle;


public class AppliTestDAL {

	public static void main(String[] args) throws DALException, ParseException {

		ClientDAO clientDAO = DAOFactory.getClientDAO();
		SpectacleDAO spectacleDAO = DAOFactory.getSpectacleDAO();




		
			//SÃ©lection de tous les clients
			//TODO...
			Client vClient = new Client("Chevalié","Floryan","flolebg@wanadoo.èfère","La Remaudiere", "60000","POuet");
			
			clientDAO.insert(vClient);
			List<Client> clients = clientDAO.selectAll();
			System.out.println("Selection de tous les clients  : " + clients.toString() );
			Client client = clientDAO.selectById(1);
			System.out.println("Selection du client  : " + client.toString() );
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
			Date vdate = simpleDateFormat.parse("2018/12/31");
			
			Spectacle vSpectacle = spectacleDAO.selectById(1);
			System.out.println("Premier Spectacle : " + vSpectacle.toString() );

	
	}

}
