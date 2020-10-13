package bean;

import java.io.Serializable;
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

@ManagedBean(name = "barChartTv")
@SessionScoped
public class BarChartTv implements Serializable {

	private List<Media> tvs;
	private List<Integer> annees; 
	
	private Map<String, Integer> tvHash;
	private Map<String, Integer> anneesHash; 

	private int positionTv;
	private int annee; 

	private BarChartModel barModelParTv;
	private BarChartModel barModelParAnnee;
	
	private Media media;
	private List<Data> datasParTv;
	private List<Data> datasParAnnee;

	@PostConstruct
	public void init() {
		tvs = MediaDAO.getAllTv();
		
		tvHash = new TreeMap<String, Integer>();
		for (int i = 0; i < tvs.size(); i++) {
			tvHash.put(tvs.get(i).getNom(), i);
		}
		
		media = tvs.get(0);		
		datasParTv = DataDAO.getByMedia(media);		
		createBarModelParMedia();
		
		annees = DataDAO.getAllAnnee(2);
		
		anneesHash = new TreeMap<String, Integer>();
		for(Integer annee : annees) {
			anneesHash.put(annee.toString(), annee);
		}
		
		annee = annees.get(0);
		datasParAnnee = DataDAO.getByMediaAnnee(2, annee);
		createBarModelParAnnee();
		
	}

	//Getter et Setter TV
	public Map<String, Integer> getTvHash() {
		return tvHash;
	}

	public void setTvHash(Map<String, Integer> tvHash) {
		this.tvHash = tvHash;
	}

	public int getPositionTv() {
		return positionTv;
	}

	public void setPositionTv(int positionTv) {
		this.positionTv = positionTv;
	}

	public BarChartModel getBarModelParTv() {
		return barModelParTv;
	}

	public void setBarModelParTv(BarChartModel barModelParTv) {
		this.barModelParTv = barModelParTv;
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
	private void createBarModelParMedia() {
		barModelParTv = initBarModelParMedia();

		barModelParTv.setTitle("Taux de paroles des femmes pour " + media.getNom());
		barModelParTv.setLegendPosition("ne");

		Axis xAxis = barModelParTv.getAxis(AxisType.X);
		xAxis.setLabel("Année");

		Axis yAxis = barModelParTv.getAxis(AxisType.Y);
		yAxis.setLabel("Taux de parole des femmes");
		yAxis.setMin(0);
		yAxis.setMax(100);
	}

	private BarChartModel initBarModelParMedia() {
		BarChartModel model = new BarChartModel();

		ChartSeries taux = new ChartSeries();
		taux.setLabel("Taux de Parole des femmes(%)");
		for (Data dt : datasParTv) {
			taux.set(dt.getAnnee() + "", dt.getTauxFemme());
		}

		model.addSeries(taux);

		return model;
	}

	public void onTvChange() {
		media = tvs.get(positionTv);
		datasParTv = DataDAO.getByMedia(media);
		createBarModelParMedia();
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
		datasParAnnee = DataDAO.getByMediaAnnee(2, annee);
		createBarModelParAnnee();
	}

}
