## Baby Birds Exercise: Monitors

Consider the following [somewhat biologically inaccurate] problem (illustrated in Fig. 1):

We have N baby birds and one parent bird.
The baby birds eat out of a common dish that initially contains F portions of food.
Each baby repeatedly

eats one portion of food at a time,
sleeps for a while, and then
comes back to eat.


When the dish becomes empty, the baby bird who empties the dish awakens the parent bird.

The parent refills the dish with F portions, then
waits for the dish to become empty again.


This pattern repeats forever.

Represent the birds as processes and develop pseudo-code in the Await Language that simulates their actions. Use monitors for synchronization.

<details>
<summary>My answer in await: </summary>

    ```
    monitor BirdFeeding{
        int portions = F;
        cond full_dish;
        cond refill;
        
        procedure eatDish(){
            while (portions == 0) wait (full_dish);
            portions = portions - 1;
            if (portions == 0){
                signal(refill);
            }
        }

        procedure refillDish(){
            while (portions > 0) wait(refill);
            portions = F;
            signal_all(full_dish);
        }
    }

    ```

</details>