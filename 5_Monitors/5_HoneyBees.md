## Traffic Roads Exercise: Monitors

Given are N honeybees and a hungry bear. They share a pot of honey. 
The pot is initially empty; its capacity is H portions of honey. The bear 
sleeps until the pot is full, then eats all the honey and goes back to 
sleep. Each bee repeatedly gathers one portion of honey and puts it in 
the pot; the bee who fills the pot awakens the bear. Represent the bear 
and honeybees as processes and develop code in the Await 
Language that simulates their actions. Use monitors for 
synchronization.

<details>
<summary>My answer in await: </summary>


    ```
    
    monitor HoneyPot {

        int portions = 0;
        cond full_pot;
        cond empty_pot;

        process fillPot{
            while (portions == H) wait(empty_pot);
            portions = portions + 1;
            if (portions == H){
                signal(full_pot);
            }
        }

        process emptyPot{
            while (portions<H) wait(full_pot);
            portions = 0;
            signal_all(empty_pot);
        }
    }

    ```

</details>