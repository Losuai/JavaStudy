package design.observer.custom;

/**
 * 所有观察者需要实现此接口
 * @author Loo
 */
public interface Observer {
    public void update(String msg);
}
