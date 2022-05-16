package it.prova.gestioneappartamento.model;

import java.util.Date;

public class Appartamento {
	private long id;
	private String quartiere;
	private int metriQuadri;
	private int prezzo;
	private Date dataCostruzione;

	public Appartamento(String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		super();
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}

	public Appartamento(long id, String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		super();
		this.id = id;
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}

	public Appartamento() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	public int getMetriQuadri() {
		return metriQuadri;
	}

	public void setMetriQuadri(int metriQuadri) {
		this.metriQuadri = metriQuadri;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataCostruzione() {
		return dataCostruzione;
	}

	public void setDataCostruzione(Date dataCostruzione) {
		this.dataCostruzione = dataCostruzione;
	}

}
