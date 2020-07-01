package design.singleton;

/**
 * 单例模式懒汉式
 *
 */
public class SingletonLHan {
    private SingletonLHan(){}
    /**
     * 线程不安全
     */
    private static SingletonLHan singletonLHan;
    public static SingletonLHan getSingletonLHan(){
        if (singletonLHan == null){
            singletonLHan = new SingletonLHan();
        }
        return singletonLHan;
    }

    /**
     * 此方式线程安全，但效率低
     * 缺点：效率低，每个线程想获取类实例都要执行同步方法，而其实这个方法只需要执行一次实例化，后面想要获取的线程直接return就行了
     */
    private static SingletonLHan singletonLHanTwo;
    public static synchronized SingletonLHan getSingletonLHanTwo(){
        if (singletonLHanTwo == null){
            singletonLHanTwo = new SingletonLHan();
        }
        return singletonLHanTwo;
    }

    /**
     * 线程不安全
     * 虽然加了锁，但是当第一个线程执行完singletonLHanThree = new SingletonLHan()跳出锁时未返回时，
     * 另一个进入if语句的线程同样会再实例化一个对象
     */
    private static SingletonLHan singletonLHanThree = null;
    public static SingletonLHan getSingletonLHanThree(){
        if (singletonLHanThree == null){
            synchronized(SingletonLHan.class){
                singletonLHanThree = new SingletonLHan();
            }
        }
        return singletonLHanThree;
    }

    /**
     *推荐使用双重校验锁
     * 保证了延时加载和线程安全
     */
    private static SingletonLHan singletonLHanFour;
    public static SingletonLHan getSingletonLHanFour(){
        if (singletonLHanFour == null){
            synchronized (SingletonLHan.class){
                if (singletonLHanFour == null){
                    singletonLHanFour = new SingletonLHan();
                }
            }
        }
        return singletonLHanFour;
    }
}
