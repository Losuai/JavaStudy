package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class TestHibernateCache {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.openSession();
        s1.beginTransaction();
        Product p1 = s1.get(Product.class, (long)1);
        System.out.println("一级缓存");
        Product p2 = s1.get(Product.class, (long)1);
        s1.getTransaction().commit();
        s1.close();

        Session s2 = sf.openSession();
        s2.beginTransaction();
        System.out.println("----加入二级缓存后不再直接查询数据库");
        Product p3 = s2.get(Product.class, (long)1);
        s2.getTransaction().commit();
        s2.close();

        Session s3 = sf.openSession();
        s3.beginTransaction();
        System.out.println("----");
        Product p4 = s3.get(Product.class, (long)1);
        System.out.println(p4.getName());
        s3.getTransaction().commit();
        s3.close();

        sf.close();
    }
}
