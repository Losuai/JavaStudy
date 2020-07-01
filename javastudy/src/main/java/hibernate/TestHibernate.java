package hibernate;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;


public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
/*
循环添加数据
 */
//        for (int i=0; i<5; i++){
//            Product product =new Product();
//            product.setName("phone-"+i);
//            product.setPrice(100+i);
//            s.save(product);
//        }
        System.out.println("----");
        Product p = s.get(Product.class, (long)1);
        System.out.println(p.getName());
/*
HQL查询方式
 */
        String name = "0";
        Query q = s.createQuery("from Product p where p.name like ?1");
        q.setParameter(1, "%"+name+"%");
        List<Product> productList = q.list();
        for (Product pro: productList){
            System.out.println(pro.getName());
        }

/*
Criteria方式
 */
        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps = c.list();
        for (Product product : ps) {
            System.out.println(product.getName());
        }

/*
标准sql
 */
        String sql = "select * from product p where p.name like '%"+name+"%'";
        Query query = s.createSQLQuery(sql);
        List<Object[]> list = query.list();
        for (Object[] objects: list){
            for (Object filed: objects){
                System.out.println(filed);
            }
        }

        //属性延迟加载及一级缓存测试，上面已经加载id为1的数据
        Product p1 = s.load(Product.class, (long)1);
        System.out.println("log1");
        System.out.println(p1.getName());
        System.out.println("log2");


        s.getTransaction().commit();
        s.close();
        sf.close();
    }

}
