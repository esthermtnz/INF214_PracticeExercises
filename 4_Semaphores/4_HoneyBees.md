## Traffic Roads Exercise: Semaphores

Given are N honeybees and a hungry bear. They share a pot of honey. 
The pot is initially empty; its capacity is H portions of honey. The bear 
sleeps until the pot is full, then eats all the honey and goes back to 
sleep. Each bee repeatedly gathers one portion of honey and puts it in 
the pot; the bee who fills the pot awakens the bear. Represent the bear 
and honeybees as processes and develop code in the Await 
Language that simulates their actions. Use semaphores for 
synchronization.

<details>
<summary>My answer in await: </summary>


    ```

    int portions = 0;
    sem wakeup = 0;
    sem put_honey = 1;
    
    process Bees[i=1 to N]{
        while(true){
            P(put_honey);
            put_portion();
            portions++;
            
            if(portions == H){
                V(wakeup);
            } else{
                V(put_honey);
            }
        }
    }
    
    process Bear{
        while(true){
            P(wakeup);
            eat_honey();
            portions = 0;
            V(put_honey);
            sleep();
        }
    }


    ```

</details>