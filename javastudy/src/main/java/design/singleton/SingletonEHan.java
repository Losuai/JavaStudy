package design.singleton;

/**
 * 单例模式饿汉式
 * 私有构造函数，new一个类对象实例，定义公有方法返回该实例
 * 优点：实现方式比较简单，在类加载的时候完成实例化，避免了线程的同步问题
 * 缺点：没做到懒加载的效果，当不使用时会造成内存的浪费
 */
public class SingletonEHan {
    private SingletonEHan() {
    }

    private static SingletonEHan singletonEHan = new SingletonEHan();
    public static SingletonEHan getSingletonEHan(){
        return singletonEHan;
    }

    private static SingletonEHan singletonEHanTwo = null;
    static {
        singletonEHanTwo = new SingletonEHan();
    }
    public static SingletonEHan getSingletonEHanTwo(){
        if (singletonEHanTwo == null){
            singletonEHanTwo = new SingletonEHan();
        }
        return singletonEHanTwo;
    }

}
