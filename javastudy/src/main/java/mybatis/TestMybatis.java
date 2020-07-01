package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        Bird bird = new Bird();
        bird.setAge(100);
        bird.setName("loo");
        session.insert("addBird", bird);

//        Bird b = session.selectOne("selectBird", 2);
//        b.setName("ooo");
//        session.update("updateBird", b);
//
//        Bird bird1 = new Bird();
//        bird1.setId(2);
//        session.delete("deleteBird", bird1);

        List<Bird> birds = session.selectList("listBirds");
        for (Bird b1: birds){
            System.out.println(b1.getId());
            System.out.println(b1.getName());
            System.out.println(b1.getAge());
        }

        System.out.println("------");
        List<Bird> birdList = session.selectList("selectBirdByName", "l");
        for (Bird b2: birdList) {
            System.out.println(b2.getName());
        }
        System.out.println("-------");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "l");
        List<Bird> birdList1 = session.selectList("selectBirdByIdAndName", map);
        for (Bird b3: birdList1) {
            System.out.println(b3.getId()+":"+b3.getName());
        }

        session.commit();
        session.close();
    }
}
