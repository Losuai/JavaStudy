package thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestThreadYield {
    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0,2).mapToObj(TestThreadYield::create).forEach(Thread::start);
        Thread thread = new Thread(()->{
            try {
                System.out.println("--");
                TimeUnit.SECONDS.sleep(12);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
    public static Thread create(int index){
        return new Thread(()->{
           if (index == 0)
               Thread.yield();
            System.out.println(index);
        });
    }
}
