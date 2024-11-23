## Baby Birds Exercise: Semaphores

We have N baby birds and one parent bird.
The baby birds eat out of a common dish that initially contains F portions of food.

Each baby repeatedly:
- eats one portion of food at a time,
- sleeps for a while, and then comes back to eat.

When the dish becomes empty, the baby bird who empties the dish awakens the parent bird.
The parent refills the dish with F portions, then waits for the dish to become empty again.

This pattern repeats forever.
Represent the birds as processes and develop pseudo-code in the Await Language that simulates their actions. Use semaphores for synchronization.

<details>
<summary>My answer in await: </summary>

    ```

    int portions = F;
    sem eat = 1;    
    sem refill = 0;

    process BabyBird[i=1 to N]{
        while(true){

            P(eat);
            eats_portion();
            portions --;

            if (portions == 0){
                V(refill);
            } else{
                V(eat); //let babies eat
            }

            sleep(random());
        }
    }
    
    process ParentBird {
        while(true){
            P(refill);
            refills_portion();
            portions = F;
            V(eat); //let babies eat
        }
    }

    ```

</details>

<details>
<summary>Anya's Solution: </summary>

    ```

    int portions = F;
    sem mutex = 1;  

    sem full = F;  
    sem empty = 0;

    process BabyBird[i=1 to N] {
        while(true){

            P(full);

            P(mutex);
            eats_portion();
            portions --;

            if (portions == 0){
                V(empty);
            
            V(mutex);
            
            sleep(random());
        }
    }
    
    process ParentBird {
        while(true){
            P(empty);

            P(mutex)
            refills_portion();
            portions = F;
            V(mutex);

            for (int i = 0; i <= F; i++){
                V(full); 
            } 
        }
    }

    ```

</details>

