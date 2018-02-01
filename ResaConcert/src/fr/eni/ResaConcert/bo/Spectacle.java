package fr.eni.ResaConcert.bo;

import java.util.Date;

public class Spectacle
{
	private int vID;
	private String vTitre;
	private String vArtiste;
	private String vLieu;
	private Date vDate;
	private int vPlaces_disponibles;
	
	public Spectacle(int aID, String aTitre, String aArtiste, String aLieu, Date aDate, int aPlaces_disponibles ){
		this.vArtiste= aArtiste;
		this.vDate = aDate;
		this.vPlaces_disponibles =aPlaces_disponibles;
		this.vID = aID;
		this.vLieu = aLieu;
		this.vTitre = aTitre;
	}
	public Spectacle(String aTitre, String aArtiste, String aLieu, Date aDate, int aPlaces_disponibles ){
		this.vArtiste= aArtiste;
		this.vDate = aDate;
		this.vPlaces_disponibles =aPlaces_disponibles;
		this.vLieu = aLieu;
		this.vTitre = aTitre;
	}

	public int getvID() {
		return vID;
	}

	public void setvID(int vID) {
		this.vID = vID;
	}

	public String getvTitre() {
		return vTitre;
	}

	public void setvTitre(String vTitre) {
		this.vTitre = vTitre;
	}

	public String getvArtiste() {
		return vArtiste;
	}

	public void setvArtiste(String vArtiste) {
		this.vArtiste = vArtiste;
	}

	public String getvLieu() {
		return vLieu;
	}

	public void setvLieu(String vLieu) {
		this.vLieu = vLieu;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public int getvPlaces_disponibles() {
		return vPlaces_disponibles;
	}

	public void setvPlaces_disponibles(int vPlaces_disponibles) {
		this.vPlaces_disponibles = vPlaces_disponibles;
	}
	@Override
	public String toString() {
		return "Spectacle [ID=" + vID + ", vTitre=" + vTitre + ", vArtiste=" + vArtiste + ", vLieu=" + vLieu
				+ ", vDate=" + vDate + ", vPlaces_disponibles=" + vPlaces_disponibles + "]";
	}
	
	
}
