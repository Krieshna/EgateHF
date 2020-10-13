package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.MediaType;

public class MediaTypeDAO {
	
	/**
	 * Retourne la liste de tous les types de media
	 * @return List<MediaType>
	 */
	public static List<MediaType> getAll(){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<MediaType> query = session.createQuery("FROM MediaType", MediaType.class);

        List<MediaType> types = query.list();

        session.close();

        return types;
    }

}
