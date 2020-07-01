package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Lambda表达式
 */
public class Syntax {
    @Test
    public void testSyntax(){
        //无参数，无返回值
        Runnable r1 = ()-> System.out.println("Hello World");
        r1.run();

        //一个参数，无返回值
        Consumer<String> con = x-> System.out.println(x);
//        Consumer<String> con = System.out::println;
        con.accept("一个参数，无返回值");

        //有参数，有返回值
        Comparator<Integer> com = (x, y)->{
            System.out.println("比较大小");
            return Integer.compare(x, y);
        };
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com.compare(1,2));
        System.out.println(com1.compare(2,3));
    }

    @Test
    public void testFun(){
        System.out.println(operation(100, x->x*x));
        System.out.println(operation(200, x->x+100));
    }

    public Integer operation(Integer num, MyFun myFun){
        return myFun.getValue(num);
    }

    //Lambda排序
    @Test
    public void testSort(){
        List<Employee> emps = Arrays.asList(
                new Employee(1, "ljl", 10),
                new Employee(2, "loo", 20),
                new Employee(3, "ljl", 20),
                new Employee(4, "ljl", 40),
                new Employee(5, "ljl", 50)
        );
        emps.sort((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee e: emps){
            System.out.println(e);
        }
    }

    @Test
    public void testFun2(){
        System.out.println(op(100L, 200L, (x, y)->x+y));
        System.out.println(op(100L, 200L, (x, y)->x*y));
    }
    public Long op(Long l1, Long l2, MyFun2<Long, Long> mf2){
        return mf2.getValue(l1, l2);
    }

    //Consumer<T> : 消费型接口 （无返回值，需要传参数，只进不出）
    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
    @Test
    public void testHappy(){
        happy(1000, m-> System.out.println("消费："+m + "元"));
    }

    //Supplier<T> : 供给型接口（有返回值，无入参）
    public List<Integer> getNums(int num, Supplier<Integer> s){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<num; i++){
            Integer m = s.get();
            list.add(m);
        }
        return list;
    }
    @Test
    public void testGetNums(){
        List<Integer> list = getNums(5, ()-> (int) (Math.random() * 100));
        for (Integer n: list){
            System.out.print(n+" ");
        }
    }

    //Function<T, R> : 函数型接口  传进去T, 返回R
    public String strHandler(String s, Function<String, String> f){
        return f.apply(s);
    }
    @Test
    public void testStr(){
        System.out.println(strHandler("     我是很牛逼你不行啊 ", String::trim));
        System.out.println(strHandler("我是很牛逼，你不行啊", s->s.substring(1,5)));
    }

    //Predicate<T> : 断言型接口（做一个判断，符合条件的返回true）
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> result = new ArrayList<>();
        for (String s: list){
            if (predicate.test(s)){
                result.add(s);
            }
        }
        return result;
    }
    @Test
    public void testPredicate(){
        List<String> l = Arrays.asList("sadfa", "fdfdsg", "fd", "eqwq", "qwe");
        List<String> list = filterStr(l, s->s.length()>3);
        for (String s: list){
            System.out.println(s);
        }
    }

    @Test
    public void test(){
        Function<Integer, String[]> fun1 = (args)->new String[args];
        //Function<Integer, String[]> fun1 = String[]::new;
        String[] strs = fun1.apply(100);
        System.out.println(strs.length);

    }
}
