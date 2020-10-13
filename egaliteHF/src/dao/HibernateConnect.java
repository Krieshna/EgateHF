package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Classe de connexion a la base de donnee
 * @author ChainSilver
 *
 */
public class HibernateConnect {

    private static final SessionFactory sessionFactory;

    static{
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

