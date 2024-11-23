## Traffic Roads Exercise: Semaphores

There exits an intersection where two main roads Road A and Road B, intersect. To manage traffic at this intersection, the city has installed a traffic light system. However, due to the complexity and the high volume of traffic, special rules have been established to ensure a smooth and fair flow of vehicles.

- Vehicles arriving on Road A or Road B can queue up at the intersection and wait for the green light.

- The traffic light system alternates between allowing vehicles from Road A and Road B to pass.

- A group of vehicles from one road (say, Road A) can only proceed when the light for Road A is green, and during that time, no vehicles from Road B are allowed to enter the intersection, and vice versa.

- To prevent any one road from monopolizing the intersection, after a set number of vehicles from Road A (say, 5 vehicles), the light will automatically switch to allow vehicles from Road B, even if there are more vehicles waiting on Road A. Similarly, after a set number of vehicles from Road B pass, the light will switch to allow vehicles from Road A.


<details>
<summary>My answer in await: </summary>

    ```
    sem greenA = 1;
    sem greenB = 0;

    sem roadA = 1;
    sem roadB = 1;

    int carsA = 0;
    int carsB = 0;
    int cars_crossed = 0;

        
    process RoadACar [i=1 to N]{
        while (true){
            P(roadA);
            carsA ++;
            V(roadB);
            
            P(greenA); //wait until light is green
            cars_crossed ++;

            /*cross the road*/
            
            P(roadA);
            carsA --;
            V(roadA);

            if(cars_crossed == 5){
                cars_crossed = 0;
                V(greenB); // signal cars on roadB
            } else{
                V(greenA); //signal next car A
            }
 
        }
    }

    process RoadBCar [i=1 to N]{
        while (true){
            P(roadB);
            carsB ++;
            V(roadB);
            
            P(greenB); //wait until light is green
            cars_crossed ++;

            /*cross the road*/
            
            P(roadB);
            carsB --;
            V(roadB);

            if(cars_crossed == 5){
                cars_crossed = 0;
                V(greenA); // signal cars on roadB
            } else{
                V(greenB); //signal next car B
            }
        }
    }

    ```

</details>

<details>
<summary>Solution: </summary>
```

    sem greenA = 1; //green light for road A
    sem greenB = 0;

    int cars_crossed_A = 0;
    int cars_crossed_B = 0;

        
    process RoadACar [i=1 to N]{
        while (true){
            
            P(greenA); //wait until light is green
            
            cars_crossed_A ++; /*cross the road*/
            
            if(cars_crossed_A == 5){
                cars_crossed_A = 0;
                V(greenB); // signal cars on road B
            } else{
                V(greenA); //signal next car A
            }
 
        }
    }

    process RoadBCar [i=1 to N]{
        while (true){
            
            P(greenB); //wait until light is green
            cars_crossed_B ++; /*cross the road*/

            if(cars_crossed_B == 5){
                cars_crossed_B = 0;
                V(greenA); // signal cars on road A
            } else{
                V(greenB); //signal next car B
            }
        }
    }

```
</details>