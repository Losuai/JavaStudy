package design.singleton;

public class Test {
    public static void main(String[] args) {
        SingletonEnum.instance.whateverMethod();
        System.out.println(SingletonEnum.instance);
    }
}
