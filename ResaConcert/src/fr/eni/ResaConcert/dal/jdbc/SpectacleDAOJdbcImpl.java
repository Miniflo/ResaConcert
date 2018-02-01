package pn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpectacleDAOJdbcImpl implements SpectacleDAO{


	private static final String sqlSelectAll = "Select * from Spectacle";
	private static final String sqlSelectById = "Select * from Spectacle where id = ?";
	private static final String sqlUpdate = "update spectacle set places_disponibles = ?";
	
	public Spectacle selectById(int id) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Spectacle spectacle = null; 
			try {
				cnx = JdbcTools.getConnection();
				rqt = cnx.prepareStatement(sqlSelectById);
				rqt.setInt(1, id);

				rs = rqt.executeQuery();
				if (rs.next()){
					spectacle = new Spectacle(rs.getInt("id"),
							rs.getString("titre"),
							rs.getString("artiste"),
							rs.getString("lieu"),
							rs.getDate("date"),
							rs.getInt("places_disponibles"));
					
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
			return spectacle;
		}
	
		@Override
		public List<Spectacle> selectAll() throws DALException {
			Connection cnx = null;
			Statement rqt = null;
			ResultSet rs = null;
			List<Spectacle> liste = new ArrayList<Spectacle>();
			try {
				cnx = JdbcTools.getConnection();
				rqt = cnx.createStatement();
				rs = rqt.executeQuery(sqlSelectAll);
				Spectacle spectacle = null;
	
				while (rs.next()) {
						spectacle = new Spectacle(rs.getInt("id"),
								rs.getString("titre"),
								rs.getString("artiste"),
								rs.getString("lieu"),
								rs.getDate("date"),
								rs.getInt("places_disponibles"));
					
					
					liste.add(spectacle);
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
		public void update_nb_places(Spectacle spectacle, int nb_places) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JdbcTools.getConnection();
				rqt = cnx.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				rqt.setInt(1, nb_places);
		
				rqt.executeUpdate();
				
			}catch(SQLException e){
				throw new DALException("Update spectacle failed - " + spectacle, e);
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