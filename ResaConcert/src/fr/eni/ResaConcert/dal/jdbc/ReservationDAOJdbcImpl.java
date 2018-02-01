package fr.eni.ResaConcert.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ResaConcert.bo.Reservation;
import fr.eni.ResaConcert.dal.DALException;
import fr.eni.ResaConcert.dal.ReservationDAO;



public class ReservationDAOJdbcImpl implements ReservationDAO{

	private static final String sqlSelectAll = "Select * from Reservation";
	private static final String sqlSelectById = "Select * from Reservation where code_reservation = ?";
	private static final String sqlSelectByClient= "Select * from Reservation where client_id = ?";
	private static final String sqlInsert = "insert into reservation(code_reservation,spectacle_id,client_id,nombre_places,date_reservation) values(?,?,?,?,?)";
	private static final String sqlDelete ="delete * from reservation where code_reservation=?";
	public Reservation selectById(String id) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			Reservation reservation = null; 
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(sqlSelectById);
				rqt.setString(1, id);

				rs = rqt.executeQuery();
				if (rs.next()){
					reservation = new Reservation(rs.getString("code_reservation"),
							rs.getInt("spectacle_id"),
							rs.getInt("client_id"),
							rs.getInt("nombre_places"),
							rs.getDate("date_reservation")
							);
					
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
			return reservation;
		}
	
	
		public List<Reservation> selectAll() throws DALException {
			Connection cnx = null;
			Statement rqt = null;
			ResultSet rs = null;
			List<Reservation> liste = new ArrayList<Reservation>();
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.createStatement();
				rs = rqt.executeQuery(sqlSelectAll);
				Reservation reservation = null;
	
				while (rs.next()) {
						reservation = new Reservation(rs.getString("code_reservation"),
								rs.getInt("spectacle_id"),
								rs.getInt("client_id"),
								rs.getInt("nombre_places"),
								rs.getDate("date_reservation")
								);
					
					
					liste.add(reservation);
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
					e.printStackTrace();
				}
			}
			return liste;
	
		}
		

		public void insert(Reservation reservation) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JDBCTools.getConnection();

				rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				rqt.setString(1, reservation.getvCode_reservation());
				rqt.setInt(2, reservation.getvSpectacle_id());
				rqt.setInt(3, reservation.getvClient_id());
				rqt.setInt(4, reservation.getvNombre_places());
				rqt.setDate(5, reservation.getvDate_reservation());
				
				rqt.executeUpdate();
				
			}catch(SQLException e){
				throw new DALException("Insert reservation failed - " + reservation, e);
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


		public List<Reservation> selectByClient(int idClient) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			ResultSet rs = null;
			List<Reservation> listeResasClient = new ArrayList<Reservation>();

			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(sqlSelectByClient);
				rqt.setInt(1, idClient);
				Reservation reservation = null; 

				rs = rqt.executeQuery();
				while (rs.next()) {
					reservation = new Reservation(rs.getString("code_reservation"),
							rs.getInt("spectacle_id"),
							rs.getInt("client_id"),
							rs.getInt("nombre_places"),
							rs.getDate("date_reservation")
							);
					listeResasClient.add(reservation);

					}
				}
	
			catch (SQLException e) {
				throw new DALException("selectByClient failed - id = " + idClient , e);
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
			return listeResasClient;
		}
		
		
		public void delete(String vCode_Reservation) throws DALException {
			Connection cnx = null;
			PreparedStatement rqt = null;
			try {
				cnx = JDBCTools.getConnection();
				/*rs.getString("code_reservation"),
							rs.getInt("spectacle_id"),
							rs.getInt("client_id"),
							rs.getInt("nombre_places"),
							rs.getDate("date_reservation")
							);*/
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(sqlDelete);
				rqt.setString(1, vCode_Reservation);

				rqt.executeQuery();
				
				
				rqt.executeUpdate();
				
			}catch(SQLException e){
				throw new DALException("Delete reservation failed - " + vCode_Reservation, e);
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
