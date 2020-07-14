package thread.custom_lock;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock{
    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();
    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                final Thread tempThread = currentThread();
                try {
                    blockedList.add(tempThread);
                    this.wait();
                }catch (InterruptedException e){
                    blockedList.remove(tempThread);
                    throw e;
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if (mills <= 0){
                throw new TimeoutException("等待时间不合法");
            }else {
                long remainMills = mills;
                long endMills = System.currentTimeMillis() + mills;
                while (locked){
                    if (remainMills <= 0)
                        throw new TimeoutException("can not get the lock during " + mills);
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait(remainMills);
                    remainMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if (currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
