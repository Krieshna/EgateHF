
import java.util.List;

import dao.MediaDAO;
import dao.MediaTypeDAO;
import entity.Media;
import entity.MediaType;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MediaType> list = MediaTypeDAO.getAll();
		for(MediaType mT : list) {
			System.out.println(mT.getLibelle());
		}
		System.out.println("Radio :");
		List<Media> listRadio = MediaDAO.getAllRadio();
		for(Media radio : listRadio) {
			System.out.println(radio.getNom());
		}	
		System.out.println("TV :");
		List<Media> listTv = MediaDAO.getAllTv();
		for(Media tv : listTv) {
			System.out.println(tv.getNom());
		}	
		
	}
}
