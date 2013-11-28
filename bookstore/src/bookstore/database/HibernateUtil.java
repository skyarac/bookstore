package bookstore.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            System.out.println("Initial SessionFactory creation failed.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static final ThreadLocal tLocalsess = new ThreadLocal();

    public static final ThreadLocal tLocaltx = new ThreadLocal();

    /*
     * getting the thread-safe session for using
     */
    public static Session currentSession() {
        Session session = (Session) tLocalsess.get();

        // open a new one, if none can be found.
        try {
            if (session == null || !session.isOpen()) {
                session = openSession();
                tLocalsess.set(session);
            }
        } catch (HibernateException e) {
            // throw new HibernateException(e);
            e.printStackTrace();
        }
        return session;
    }

    /*
     * closing the thread-safe session
     */
    public static void closeSession() {

        Session session = (Session) tLocalsess.get();
        tLocalsess.set(null);
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }

        } catch (HibernateException e) {
            // throw new InfrastructureException(e);
        }
    }

    /*
     * begin the transaction
     */
    public static void beginTransaction() {
        System.out.println("begin tx");
        Transaction tx = (Transaction) tLocaltx.get();
        try {
            if (tx == null) {
                tx = currentSession().beginTransaction();
                tLocaltx.set(tx);
            }
        } catch (HibernateException e) {
            // throw new InfrastructureException(e);
        }
    }

    /*
     * close the transaction
     */
    public static void commitTransaction() {
        Transaction tx = (Transaction) tLocaltx.get();
        try {
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
                tx.commit();
            tLocaltx.set(null);
            System.out.println("commit tx");
        } catch (HibernateException e) {
            // throw new InfrastructureException(e);
        }
    }

    /*
     * for rollbacking
     */
    public static void rollbackTransaction() {
        Transaction tx = (Transaction) tLocaltx.get();
        try {
            tLocaltx.set(null);
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                tx.rollback();
            }
        } catch (HibernateException e) {
            // throw new InfrastructureException(e);
        }
    }

    private static Session openSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;// SingletonSessionFactory.getInstance();
    }
}