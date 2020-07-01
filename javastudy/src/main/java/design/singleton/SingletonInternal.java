package design.singleton;

/**
 * 内部类
 * 类似饿汉式采用类加载机制来保证初始化实例时只有一个线程。
 *不同处：饿汉式中Singleton只要被装载就会被实例化，内部类是在需要的时候实例化，才会装载SingletonHelper类
 * 避免了线程不安全，延时加载，效率高
 */
public class SingletonInternal {
    private SingletonInternal(){}

    private static class SingletonHelper{
        private static SingletonInternal singletonInternal = new SingletonInternal();
    }
    public SingletonInternal getSingletonInternal(){
        return SingletonHelper.singletonInternal;
    }
}
