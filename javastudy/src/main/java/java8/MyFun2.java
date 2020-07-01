package java8;

/**
 * 函数式接口，只有一个抽象方法的接口
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface MyFun2<T, R> {
    public R getValue(T t1, T t2);
}
