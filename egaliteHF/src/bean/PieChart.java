package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import dao.DataDAO;

@ManagedBean(name = "pieChart")
@SessionScoped
public class PieChart {
	private PieChartModel pieModelMedia;
	private PieChartModel pieModelFunder;
	private PieChartModel pieModelAVGRadio;
	private PieChartModel pieModelAVGTv;
	

	@PostConstruct
	public void init() {
		createPieModelMedia();
		createPieModelFunder();
		createPieModelAVGRadio();
		createPieModelAVGTv();
	}

	public PieChartModel getPieModelMedia() {
		return pieModelMedia;
	}

	public void setPieModelMedia(PieChartModel pieModelMedia) {
		this.pieModelMedia = pieModelMedia;
	}

	public PieChartModel getPieModelFunder() {
		return pieModelFunder;
	}

	public void setPieModelFunder(PieChartModel pieModelFunder) {
		this.pieModelFunder = pieModelFunder;
	}

	public PieChartModel getPieModelAVGRadio() {
		return pieModelAVGRadio;
	}

	public void setPieModelAVGRadio(PieChartModel pieModelAVGRadio) {
		this.pieModelAVGRadio = pieModelAVGRadio;
	}

	public PieChartModel getPieModelAVGTv() {
		return pieModelAVGTv;
	}

	public void setPieModelAVGTv(PieChartModel pieModelAVGTv) {
		this.pieModelAVGTv = pieModelAVGTv;
	}

	private void createPieModelMedia() {
		pieModelMedia = new PieChartModel();

		double radio = DataDAO.getAVGByMediaTypeAndByYear(1, 2019);
		double tv = DataDAO.getAVGByMediaTypeAndByYear(2, 2019);
		pieModelMedia.set("Radio", radio);
		pieModelMedia.set("TV", tv);

		pieModelMedia.setTitle("Différence Radio/TV sur l'année 2019");
		pieModelMedia.setLegendPosition("w");
		pieModelMedia.setShadow(true);
	}

	private void createPieModelFunder() {
		pieModelFunder = new PieChartModel();

		double publique = DataDAO.getAVGByFunderAndByYear(true, 2019);
		double prive = DataDAO.getAVGByFunderAndByYear(false, 2019);
		pieModelFunder.set("Publique", publique);
		pieModelFunder.set("Privée", prive);

		pieModelFunder.setTitle("Différence entre les chaines privées et publiques sur l'année 2019");
		pieModelFunder.setLegendPosition("w");
		pieModelFunder.setShadow(true);
	}

	private void createPieModelAVGRadio() {
		pieModelAVGRadio = new PieChartModel();

		List<Object[]> list = DataDAO.getAVGForMedia(1);

		for (Object[] dt : list) {
			pieModelAVGRadio.set(dt[0].toString(),(Double)dt[1]);
		}

		pieModelAVGRadio.setTitle("Moyennes du temps de paroles des femmes pour les 10 radios ayant le plus de paroles");
		pieModelAVGRadio.setLegendPosition("w");
		pieModelAVGRadio.setShadow(true);
	}
	
	private void createPieModelAVGTv() {
		pieModelAVGTv = new PieChartModel();

		List<Object[]> list = DataDAO.getAVGForMedia(2);

		for (Object[] dt : list) {
			pieModelAVGTv.set(dt[0].toString(),(Double)dt[1]);
		}

		pieModelAVGTv.setTitle("Moyennes du temps de paroles des femmes pour les 10 chaines télé ayant le plus de paroles");
		pieModelAVGTv.setLegendPosition("w");
		pieModelAVGTv.setShadow(true);
	}

}
