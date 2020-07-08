package thread.join;

import java.util.concurrent.TimeUnit;

public class TestThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("a");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        thread.join();
        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());
        System.out.println("b");
    }
}
