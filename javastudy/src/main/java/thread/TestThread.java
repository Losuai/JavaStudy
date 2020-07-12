package thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0,2).mapToObj(TestThread::create).forEach(Thread::start);
        Thread thread = new Thread(()->{
            try {
                System.out.println("--");
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        for (int i=0; i<10; i++)
            System.out.println("isInterrupted:" + thread.isInterrupted());

    }
    public static Thread create(int index){
        return new Thread(()->{
           if (index == 0)
               Thread.yield();
            System.out.println(index);
        });
    }
    @Test
    public void testThreadIsInterrupted() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){

            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
    @Test
    public void testThreadInterrupted() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (true){
                //如果当前线程被打断了，那么第一次调用interrupted会返回true，并立即檫除interrupt标识，第二次包括以后调用永远返回false
                //除非此期间线程在一次被打断
                System.out.println(Thread.interrupted());
            }
        });
        thread1.setDaemon(true);
        thread1.start();
        TimeUnit.SECONDS.sleep(2);
        thread1.interrupt();
    }
}
