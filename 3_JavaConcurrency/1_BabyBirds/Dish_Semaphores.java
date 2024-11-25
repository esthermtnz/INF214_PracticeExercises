/* This code would be the equivalent as implementing the monitors on Java*/


/*
 * MEMORIZAR:
 * 
 * RESTAR
 * P(s) es lo mismo que s.acquire();
 * 
 * SUMAR
 * V(s) es lo mismo que s.release();
 * 
 */

import java.util.concurrent.Semaphore;

public class Dish_Semaphores{

    private final int F = 5; //F portions
    private int portions = F;
    private Semaphore refill = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);



    public void eatPortion() throws InterruptedException {
        
        mutex.acquire();
        portions --;
        System.out.println(Thread.currentThread().getName() + " ate a portion. Portions remaining: " + portions);
        if(portions == 0){
            refill.release();
        }else{
            mutex.release();
        }
        
    }

    public void refillPortions() throws InterruptedException{
        
        refill.acquire();
        portions = F;
        System.out.println("Parent bird filled the dish with " + F + " portions.");
        mutex.release();
        
    }

    /* -------------------MAIN------------------ */
    public static void main(String[] args) {
        Dish_Semaphores dish = new Dish_Semaphores();

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