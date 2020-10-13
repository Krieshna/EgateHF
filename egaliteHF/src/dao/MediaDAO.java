package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Media;

public class MediaDAO {
	
	/**
	 * Retourne la liste de tous les medias
	 * @return List<Media>
	 */
	public static List<Media> getAll(){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<Media> query = session.createQuery("FROM Media", Media.class);

        List<Media> medias = query.list();

        session.close();

        return medias;
    }
	
	/**
	 * Retourne la liste de toutes les radios
	 * @return List<Media>
	 */
	public static List<Media> getAllRadio(){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<Media> query = session.createQuery("FROM Media where mediaType_id=1", Media.class);

        List<Media> medias = query.list();

        session.close();

        return medias;
    }
	
	/**
	 * Retourne la liste de toutes les chaines tv
	 * @return List<Media>
	 */
	public static List<Media> getAllTv(){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<Media> query = session.createQuery("FROM Media where mediaType_id=2", Media.class);

        List<Media> medias = query.list();

        session.close();

        return medias;
    }


}
