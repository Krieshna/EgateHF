package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="media")
public class Media {
	private int id;
	private String nom;
	private Boolean publique;
	private MediaType mediaType;
	private List<Data> datas;
	
	public Media() {
	}

	public Media(String nom, Boolean publique, MediaType mediaType) {
		super();
		this.nom = nom;
		this.publique = publique;
		this.mediaType = mediaType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getPublique() {
		return publique;
	}

	public void setPublique(Boolean publique) {
		this.publique = publique;
	}

	@ManyToOne
	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@OneToMany(mappedBy = "media")
	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}	

}
