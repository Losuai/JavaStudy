package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Stream {

 /*
	映射
map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
*/
    @Test
    public void test1(){
        List<String> arrList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        arrList.stream().map(String::toUpperCase).forEach(System.out::println);

    }

    /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
    @Test
    public void test2(){
        List<String> arrList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        arrList.stream().sorted(String::compareTo).forEach(System.out::println);
    }

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
	/*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
	@Test
    public void test3(){
	    List<Integer> list = Arrays.asList(1,2,4,5,2,3);
	    Integer sum = list.stream().reduce(0, (x, y)->x+y);
        System.out.println(sum);
    }


    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法，
    // Collects是collect的工具类
    @Test
    public void test4() {
        List<Employee> emps = Arrays.asList(new Employee(1, "ljl", 10), new Employee(2, "loo", 20), new Employee(3, "ljl", 20), new Employee(4, "ljl", 40), new Employee(5, "ljl", 50));
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------");
        Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("------");
        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例，不能构建一个null
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */
}
