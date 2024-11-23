/* This code would be the equivalent as implementing the monitors on Java*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class BabyBirds{

    private final int F = 5; //F portions
    private int portions = F;

    private final Lock lock = new ReentrantLock();
    private Condition emptyDish = lock.newCondition();
    private Condition fullDish = lock.newCondition();

    public void eatPortion() throws InterruptedException {
        lock.lock();
        try{
            while(portions == 0) fullDish.await();
            portions --;
            if (portions == 0) emptyDish.signal();
        } finally{
            lock.unlock();
        }
    }

    public void refillPortions() throws InterruptedException{
        lock.lock();
        try{
            while(portions > 0) emptyDish.await();
            portions = F;
            fullDish.signalAll();
        } finally{
            lock.unlock();
        }
    }
}