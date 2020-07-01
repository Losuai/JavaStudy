package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernateSession {
    static Session s5;
    static Session s6;
    public static void main(String[] args) {
        final SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.openSession();
        Session s2 = sf.openSession();
        System.out.println(s1==s2);

        Session s3 = sf.getCurrentSession();
        Session s4 = sf.getCurrentSession();
        System.out.println(s3==s4);


        Thread t1 = new Thread(){
            @Override
            public void run() {
                s5  = sf.getCurrentSession();
                s5.beginTransaction();
                s5.get(Product.class, (long)1);
                s5.getTransaction().commit();
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                s6  = sf.getCurrentSession();
                s6.beginTransaction();
                s6.getTransaction().commit();
            }
        };
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s5==s6);

        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
        s6.close();
        sf.close();
    }
}
