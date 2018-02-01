package fr.eni.ResaConcert.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ResaConcert.bo.Client;
import fr.eni.ResaConcert.dal.ClientDAO;
import fr.eni.ResaConcert.dal.DALException;



public class ClientDAOJdbcImpl implements ClientDAO{

	private static final String sqlSelectAll = "Select * from Client";
	private static final String sqlSelectById = "Select * from Client where id = ?";
	private static final String sqlInsert = "insert into client(nom,prenom,email,adresse,code_postal,ville) values(?,?,?,?,?,?)";

	public Client selectById(int id) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Client client = null; 
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(sqlSelectById);
				rqt.setInt(1, id);

				rs = rqt.executeQuery();
				if (rs.next()){
					client = new Client(rs.getInt("id"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("adresse"),
							rs.getString("code_postal"),
							rs.getString("ville"));
					
					}
				}
	
			catch (SQLException e) {
				throw new DALException("selectById failed - id = " + id , e);
			} finally {
				try {
					if (rs != null){
						rs.close();
					}
					if (rqt != null){
						rqt.close();
					}
					if(cnx!=null){
						cnx.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	
			}
			return client;
		}
	
		@Override
		public List<Client> selectAll() throws DALException {
			Connection cnx = null;
			Statement rqt = null;
			ResultSet rs = null;
			List<Client> liste = new ArrayList<Client>();
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.createStatement();
				rs = rqt.executeQuery(sqlSelectAll);
				Client client = null;
	
				while (rs.next()) {
						client = new Client(rs.getInt("id"),
								rs.getString("nom"),
								rs.getString("prenom"),
								rs.getString("email"),
								rs.getString("adresse"),
								rs.getString("code_postal"),
								rs.getString("ville"));
					
					
					liste.add(client);
				}
			} catch (SQLException e) {
				throw new DALException("selectAll failed - " , e);
			} finally {
				try {
					if (rs != null){
						rs.close();
					}
					if (rqt != null){
						rqt.close();
					}
					if(cnx!=null){
						cnx.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return liste;
	
		}
		
		@Override
		public void insert(Client client) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				rqt.setString(1, client.getvNom());
				rqt.setString(2, client.getvPrenom());
				rqt.setString(3, client.getvEmail());
				rqt.setString(4, client.getvAdresse());
				rqt.setString(5, client.getvCode_postal());
				rqt.setString(6, client.getvVille());
				
				rqt.executeUpdate();
				
			}catch(SQLException e){
				throw new DALException("Insert client failed - " + client, e);
			}
			finally {
				try {
					if (rqt != null){
						rqt.close();
					}
					if(cnx!=null){
						cnx.close();
					}
				} catch (SQLException e) {
					throw new DALException("close failed - ", e);
				}
	
			}
		}

	




	}
