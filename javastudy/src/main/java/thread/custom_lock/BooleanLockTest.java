package thread.custom_lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class BooleanLockTest {
    private final BooleanLock lock = new BooleanLock();
    public void syncMethod(){
        try {
            lock.lock();
            int randomInt = new Random().nextInt(10);
            System.out.println(currentThread() + "get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
            System.out.println(lock.getBlockedThreads());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest booleanLockTest = new BooleanLockTest();
//        IntStream.range(0,10).mapToObj(i-> new Thread(booleanLockTest::syncMethod)).forEach(Thread::start);
        new Thread(booleanLockTest::syncMethod, "T1").start();
        TimeUnit.SECONDS.sleep(2);
        Thread t2 = new Thread(booleanLockTest::syncMethod, "T2");
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();
    }
}
