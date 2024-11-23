/* This code would be the equivalent as implementing the monitors on Java*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Dish{

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
            System.out.println(Thread.currentThread().getName() + " ate a portion. Portions remaining: " + portions);
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
            System.out.println("Parent bird filled the dish with " + F + " portions.");
            fullDish.signalAll();
        } finally{
            lock.unlock();
        }
    }

    /* -------------------MAIN------------------ */
    public static void main(String[] args) {
        Dish dish = new Dish();

        // Thread for Parent Bird
        Thread parentBird = new Thread(() -> {
            try {
                while (true) {
                    dish.refillPortions();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Parent Bird");

        // Thread for Baby Birds
        int N = 3; // num of baby birds
        Thread[] babyBirds = new Thread[N];
        for (int i = 0; i < N; i++) {
            babyBirds[i] = new Thread(() -> {
                try {
                    while (true) {
                        dish.eatPortion();
                        Thread.sleep((int) (Math.random() * 1000)); // Sleep
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Baby Bird " + (i + 1));
        }

        // Initialise threads
        parentBird.start();
        for (Thread bird : babyBirds) {
            bird.start();
        }
    }
}