package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Data;
import entity.Media;

public class DataDAO {

	/**
	 * Retourne la liste de toutes les donnees
	 * 
	 * @return List<Data>
	 */
	public static List<Data> getAll() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		Query<Data> query = session.createQuery("FROM Data", Data.class);

		List<Data> datas = query.list();

		session.close();

		return datas;
	}

	/**
	 * Retourne la liste des données selon une chaine media donnée
	 * 
	 * @param media
	 * @return
	 */
	public static List<Data> getByMedia(Media media) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		Query<Data> query = session.createQuery("FROM Data where media_id=:idMedia order by annee", Data.class);
		query.setParameter("idMedia", media.getId());

		List<Data> datas = query.list();

		session.close();

		return datas;
	}

	/**
	 * Retourne la liste des données selon le type de média et par année Int
	 * mediaType => 1 pour la radio / 2 pour la tv Int year => année du set
	 * souhaitée
	 * 
	 * @return List<Data>
	 */
	public static List<Data> getByMediaAnnee(int mediaType, int year) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		Query<Data> query = session.createQuery("FROM Data WHERE media.mediaType.id = :mediaType  AND  annee = :annee ",
				Data.class);
		query.setParameter("mediaType", mediaType);
		query.setParameter("annee", year);
		List<Data> datas = query.list();

		session.close();

		return datas;
	}

	/**
	 * Retourne la liste de toutes les années en base de données selon le type de média
	 * @param mediaType => 1 pour la radio / 2 pour la tv
	 * @return List<Integer>
	 */
	public static List<Integer> getAllAnnee(int mediaType) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		Query<Integer> query = session.createQuery("select DISTINCT annee from Data d where media.mediaType.id = :mediaType order by annee ");
		query.setParameter("mediaType", mediaType);

		List<Integer> annees = query.list();

		session.close();

		return annees;
	}
	
    /**
     * Retourne la moyenne du taux de parole selon le type de média et par annéee
     * @param Int mediaType => 1 pour la radio / 2 pour la tv
     * @param Int year => année du set souhaitée
     * @return double
     */
    public static double getAVGByMediaTypeAndByYear(int mediaType, int year ){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<Double> query = session.createQuery("select AVG(tauxFemme) from Data WHERE media.mediaType.id = :mediaType AND annee = :annee ");
           
        query.setParameter("mediaType",mediaType);
        query.setParameter("annee",year);
        
        double avg = query.getSingleResult();
        
        return avg;
    } 
    
    /**
     * Retourne la moyenne du taux de parole selon le type de financement et par annéee
     * @param boolean funder => false pour les média privé / true pour les média publique.
     * @param Int year => année du set souhaitée
     * @return double
     */
    public static double getAVGByFunderAndByYear(boolean publique, int year ){
        Session session = HibernateConnect.getSessionFactory().openSession();
        Query<Double> query = session.createQuery("select AVG(tauxFemme) from Data WHERE media.publique  = :publique AND annee = :annee ");
           
        query.setParameter("publique",publique);
        query.setParameter("annee",year);
        
        double avg = query.getSingleResult();
        return avg;
    }  
    
    /**
     * Retourne le taux moyen de taux parole pour les 10  chaines avec le taux de parole le plus élevés
     * Int mediaType => 1 pour la radio / 2 pour la tv
     * [0] pour récupérée le nom de la chaine, [1] pour le taux
     * @return 
     * @return List<Object[]>
     */
    public static List<Object[]>  getAVGForMedia(int mediaType){
        Session session = HibernateConnect.getSessionFactory().openSession();
        TypedQuery<Object[]> query  = session.createQuery("select media.nom, AVG(tauxFemme) FROM Data  WHERE media.mediaType.id = :mediaType GROUP BY media.nom ORDER BY tauxParole DESC ");
        query.setParameter("mediaType",mediaType);
        query.setMaxResults(10);
        List<Object[]> results = query.getResultList();

        return results;
    }   


}
