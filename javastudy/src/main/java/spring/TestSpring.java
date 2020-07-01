package spring;

import hibernate.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});

//        Product p = (Product) context.getBean("p");
//        System.out.println(p.getName());
//        System.out.println(p.getCategory().getName());

        //切面编程
        ProductService ps = (ProductService) context.getBean("s");
        ps.doService();
    }

    @Autowired
    Product product;

//    @Test
//    public void test(){
//        System.out.println(product.getName());
//    }
}
