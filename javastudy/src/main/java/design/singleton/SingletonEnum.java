package design.singleton;

/**
 * 枚举，极推荐使用
 * 这里的instance即为SingletonEnum类型的引用，得到它就可以调用枚举中的方法了
 * 不仅能够避免多线程同步问题，而且还能防止反序列化重新创建新对象
 */
public enum  SingletonEnum {
    instance;
    private SingletonEnum(){
        System.out.println("hello");
    }
    public void whateverMethod(){
        System.out.println("........");
    }

}
