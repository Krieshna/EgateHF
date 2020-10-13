package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.DataDAO;
import dao.MediaDAO;
import entity.Data;
import entity.Media;

@ManagedBean(name = "barChartRadio")
@SessionScoped
public class BarChartRadio implements Serializable {

	private List<Media> radios;
	private List<Integer> annees; 
	
	private Map<String, Integer> radiosHash;
	private Map<String, Integer> anneesHash; 

	private int positionRadio;
	private int annee; 

	private BarChartModel barModelParRadio;
	private BarChartModel barModelParAnnee;
	
	private Media media;
	private List<Data> datasParRadio;
	private List<Data> datasParAnnee;

	@PostConstruct
	public void init() {
		radios = MediaDAO.getAllRadio();
		
		radiosHash = new TreeMap<String, Integer>();
		for (int i = 0; i < radios.size(); i++) {
			radiosHash.put(radios.get(i).getNom(), i);
		}
		
		media = radios.get(0);		
		datasParRadio = DataDAO.getByMedia(media);		
		createBarModelParRadio();
		
		annees = DataDAO.getAllAnnee(1);
		
		anneesHash = new TreeMap<String, Integer>();
		for(Integer annee : annees) {
			anneesHash.put(annee.toString(), annee);
		}
		
		annee = annees.get(0);
		datasParAnnee = DataDAO.getByMediaAnnee(1, annee);
		createBarModelParAnnee();
		
	}

	//Getter et Setter Radio
	public Map<String, Integer> getRadiosHash() {
		return radiosHash;
	}

	public void setRadiosHash(Map<String, Integer> radiosHash) {
		this.radiosHash = radiosHash;
	}
	
	public int getPositionRadio() {
		return positionRadio;
	}

	public void setPositionRadio(int positionRadio) {
		this.positionRadio = positionRadio;
	}
	
	public BarChartModel getBarModelParRadio() {
		return barModelParRadio;
	}

	public void setBarModelParRadio(BarChartModel barModel) {
		this.barModelParRadio = barModel;
	}
	
	//Getter et Setter Annee	
	public Map<String, Integer> getAnneesHash() {
		return anneesHash;
	}

	public void setAnneesHash(Map<String, Integer> anneesHash) {
		this.anneesHash = anneesHash;
	}	
		
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}	

	public BarChartModel getBarModelParAnnee() {
		return barModelParAnnee;
	}

	public void setBarModelParAnnee(BarChartModel barModelParAnnee) {
		this.barModelParAnnee = barModelParAnnee;
	}

	//Gestion diagramme de barre - changement Radio
	private void createBarModelParRadio() {
		barModelParRadio = initBarModelParRadio();

		barModelParRadio.setTitle("Taux de paroles des femmes pour " + media.getNom());
		barModelParRadio.setLegendPosition("ne");

		Axis xAxis = barModelParRadio.getAxis(AxisType.X);
		xAxis.setLabel("Année");

		Axis yAxis = barModelParRadio.getAxis(AxisType.Y);
		yAxis.setLabel("Taux de parole des femmes");
		yAxis.setMin(0);
		yAxis.setMax(100);
	}

	private BarChartModel initBarModelParRadio() {
		BarChartModel model = new BarChartModel();

		ChartSeries taux = new ChartSeries();
		taux.setLabel("Taux de Parole des femmes(%)");
		for (Data dt : datasParRadio) {
			taux.set(dt.getAnnee() + "", dt.getTauxFemme());
		}

		model.addSeries(taux);

		return model;
	}

	public void onRadioChange() {
		media = radios.get(positionRadio);
		datasParRadio = DataDAO.getByMedia(media);
		createBarModelParRadio();
	}
	
	//Gestion diagramme de bar - changement annee
	private void createBarModelParAnnee() {
		barModelParAnnee = initBarModelParAnnee();
		
		barModelParAnnee.setTitle("Taux de paroles des femmes pour l'année " + annee);
		barModelParAnnee.setLegendPosition("ne");

		Axis xAxis = barModelParAnnee.getAxis(AxisType.X);
		xAxis.setLabel("Radio");
		xAxis.setTickAngle(50);

		Axis yAxis = barModelParAnnee.getAxis(AxisType.Y);
		yAxis.setLabel("Taux de parole des femmes");
		yAxis.setMin(0);
		yAxis.setMax(100);
		
	}

	private BarChartModel initBarModelParAnnee() {
		BarChartModel model = new BarChartModel();
		
		ChartSeries taux = new ChartSeries();
		taux.setLabel("Taux de Parole des femmes (%)");
		for(Data dt: datasParAnnee) {
			taux.set(dt.getMedia().getNom(), dt.getTauxFemme());
		}
		
		model.addSeries(taux);
		
		return model;
	}
	
	public void onAnneeChange() {
		datasParAnnee = DataDAO.getByMediaAnnee(1, annee);
		createBarModelParAnnee();
	}

}
