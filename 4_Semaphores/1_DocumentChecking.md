## Document Checking Exercise: Semaphores

Consider Multilandet, which is a country, and Multibyen, which is its capital city.
The airport of Multibyen has introduced strict entry requirements for arriving passengers because of a global pandemic.
Some of the passengers are vaccinated, while others are not vaccinated.

All passengers arriving to Multibyen must go through the document/passport control, where their vaccination certificates are checked by border guard officers.
Upon arriving to Multibyen Airport, passengers, both vaccinated and unvaccinated, are mingling in the Mingling Zone, and they are walking towards to the Documents Checking Zone.
There, the border guard checks their vaccination certificates, imposes quarantine on the unvaccinated ones, and in any case lets all the passengers into the city.

Assume that the passengers enter the Documents Checking Zone in a random order. The only requirement is that there must be never unvaccinated and vaccinated passengers in the Documents Checking Zone at the same time. However, people with the same vaccination status are allowed in the Documents Checking Zone at the same time (that is, at any given moment of time, either all passengers in the Documents Checking Zone are vaccinated, or all passengers in the Documents Checking Zone are unvaccinated).
Assume that there are N vaccinated passengers, and M unvaccinated passengers who have just landed at Multibyen Airport

💡 Your solution need NOT to be fair.

<details>
<summary>My answer in await: </summary>
    
    ```

        sem mutex = 1;
        sem vax = 1;
        sem nonVax = 1;

        int numV = 0;
        int numNV = 0;


        process Vaccinated [i=1 to N]{
            while true(){

                P(vax)
                numV ++;
                if (numV == 1){
                    P(mutex);
                }
                V(vax)

                doc_checking();

                P(vax);
                numV --;
                if (numV == 0){
                    V(mutex);
                }
                V (vax);
            }
        }

        process nonVaccinated [i=1 to N]{
        while true(){

                P(nonVax);
                numNV ++;
                if (numNV == 1){
                    P(mutex);
                }
                V(nonVax);

                doc_checking();

                P(nonVax);
                numNV --;
                if (numNV == 0){
                    V(mutex);
                }
                V (nonVax);
            }
        }


    ```
</details>