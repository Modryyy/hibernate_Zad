package pl.edu.wszib.db;

        import org.hibernate.*;
        import org.hibernate.cfg.Configuration;
        import pl.edu.wszib.model.Pytanie;


        import java.util.List;

public class DBConnector {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();


    public static void addQuestion(Pytanie pytanie) {

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(pytanie);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

    public static Pytanie getPytanie(int idPytania) {
        Session session = factory.openSession();

        Pytanie pytanie = (Pytanie) session.createQuery("FROM pl.edu.wszib.model.Pytanie WHERE idPytania = " + idPytania).uniqueResult();

        session.close();
        return pytanie;
    }


    public static void removeQuestion(int idPytania) {
       Session session = factory.openSession();

        Pytanie pytanie;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            pytanie = (Pytanie) session.load(Pytanie.class,idPytania);
            session.delete(pytanie);
            session.flush() ;
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }


    public static void updateWholeQuestion(int idPytania, Pytanie pytanie) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.load(Pytanie.class, idPytania);
            session.saveOrUpdate(pytanie);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

}
    public static void updateQuestionReward(int idPytania, Pytanie pytanie) {
        Session session = factory.openSession();
        Transaction tx = null;
        Pytanie pytanie1;
        try {
            tx = session.beginTransaction();
            pytanie1 = (Pytanie) session.load(Pytanie.class, idPytania);

            pytanie1.setNagroda(pytanie.getNagroda());


            session.update(pytanie1);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

    }
    public static void updateQuestion(int idPytania, Pytanie pytanie) {
        Session session = factory.openSession();
        Transaction tx = null;
        Pytanie pytanie1;
        try {
            tx = session.beginTransaction();
            pytanie1 = (Pytanie) session.load(Pytanie.class, idPytania);

            pytanie1.setPytanie(pytanie.getPytanie());


            session.update(pytanie1);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

    }
    public static void updateAnswer(int idPytania, Pytanie pytanie) {
        Session session = factory.openSession();
        Transaction tx = null;
        Pytanie pytanie1;
        try {
            tx = session.beginTransaction();
            pytanie1 = (Pytanie) session.load(Pytanie.class, idPytania);

            pytanie1.setOdpowiedzi(pytanie.getOdpowiedzi());


            session.update(pytanie1);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

    }
    public static void updateCorrectAnswer(int idPytania, Pytanie pytanie) {
        Session session = factory.openSession();
        Transaction tx = null;
        Pytanie pytanie1;
        try {
            tx = session.beginTransaction();
            pytanie1 = (Pytanie) session.load(Pytanie.class, idPytania);

            pytanie1.setPrawidlowaOdpowiedz(pytanie.getPrawidlowaOdpowiedz());


            session.update(pytanie1);
            //wiecej operacji
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

    }


    public static List<Pytanie> getAllQuestions() {
        Session session = factory.openSession();

        List<Pytanie> allQuestions = session.createQuery("FROM pl.edu.wszib.model.Pytanie").list();

        session.close();
        return allQuestions;
    }
}
