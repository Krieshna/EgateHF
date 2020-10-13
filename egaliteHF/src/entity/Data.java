package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class Data {
	private int id;
	private int annee;
	private Double tauxFemme;
	private Double tauxParole;
	private Float nbHeure;
	private Media media;
	
	public Data() {
	}

	public Data(int annee, Double tauxFemme, Double tauxParole, Float nbHeure, Media media) {
		super();
		this.annee = annee;
		this.tauxFemme = tauxFemme;
		this.tauxParole = tauxParole;
		this.nbHeure = nbHeure;
		this.media = media;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Double getTauxFemme() {
		return tauxFemme;
	}

	public void setTauxFemme(Double tauxFemme) {
		this.tauxFemme = tauxFemme;
	}

	public Double getTauxParole() {
		return tauxParole;
	}

	public void setTauxParole(Double tauxParole) {
		this.tauxParole = tauxParole;
	}

	public Float getNbHeure() {
		return nbHeure;
	}

	public void setNbHeure(Float nbHeure) {
		this.nbHeure = nbHeure;
	}

	@ManyToOne
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
}
