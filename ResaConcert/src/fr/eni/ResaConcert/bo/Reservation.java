package fr.eni.ResaConcert.bo;


import java.util.Date;

public class Reservation {


	private String vCode_reservation;
	private int vSpectacle_id;
	private int vClient_id;
	private int vNombre_places;
	private Date vDate_reservation;
	public Reservation(String vCode_reservation, int vSpectacle_id, int vClient_id, int vNombre_places,
			Date vDate_reservation) {
		super();
		this.vCode_reservation = vCode_reservation;
		this.vSpectacle_id = vSpectacle_id;
		this.vClient_id = vClient_id;
		this.vNombre_places = vNombre_places;
		this.vDate_reservation = vDate_reservation;
	}
	public String getvCode_reservation() {
		return vCode_reservation;
	}
	public void setvCode_reservation(String vCode_reservation) {
		this.vCode_reservation = vCode_reservation;
	}
	public int getvSpectacle_id() {
		return vSpectacle_id;
	}
	public void setvSpectacle_id(int vSpectacle_id) {
		this.vSpectacle_id = vSpectacle_id;
	}
	public int getvClient_id() {
		return vClient_id;
	}
	public void setvClient_id(int vClient_id) {
		this.vClient_id = vClient_id;
	}
	public int getvNombre_places() {
		return vNombre_places;
	}
	public void setvNombre_places(int vNombre_places) {
		this.vNombre_places = vNombre_places;
	}
	public Date getvDate_reservation() {
		return vDate_reservation;
	}
	public void setvDate_reservation(Date vDate_reservation) {
		this.vDate_reservation = vDate_reservation;
	}
	@Override
	public String toString() {
		return "Reservation [vCode_reservation=" + vCode_reservation + ", vSpectacle_id=" + vSpectacle_id
				+ ", vClient_id=" + vClient_id + ", vNombre_places=" + vNombre_places + ", vDate_reservation="
				+ vDate_reservation + "]";
	}
	
	
}
