package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
        private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();


        static{

            try{

                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            }catch(Throwable t){
                throw new ExceptionInInitializerError(t);
            }


        }

	public static Session getSession() {

          Session session = (Session) threadLocal.get();
          session = sessionFactory.openSession();
          threadLocal.set(session);
          return session;
	}

}

