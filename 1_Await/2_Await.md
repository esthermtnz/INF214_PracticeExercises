## Await: Exercise 2

### What are the possible solutions to this code?
```
int x = 1; int y = 4; 

co 
    < x = x * y; > //process 1
|| 
    x = y - x; //process 2
oc
```

<details>
<summary> Solution: </summary>
    Now P1 is an atomic operation, which means that it is executed as a whole transaction.

    1: P1 executes, P2 reads, P2 writes 
    2: P2 reads, P1 executes, P2 writes
    3: P2 reads, P2 writes, P1 executes

    Initial scenario:
    x = 1, y = 4

    Case 1:
        P1 executes:
        x = 1 * 4  = 4
        P2 reads:
        x = 4, y = 4
        P2 writes
        x = 4 - 4 = 0

        Result:
        x = 0, y = 4
    Case 2:
        P2 reads:
        x = 1, y = 4
        P1 executes:
        x = 1 * 4 = 4
        P2 writes:
        x = 4 - 1 = 3

        Result:
        x = 3, y = 4
    Case 3:
        P2 reads:
        x = 1, y = 4
        P2 writes:
        x = 4 - 1 = 3
        P1 executes:
        x = 3 * 4 = 12

        Result:
        x = 12, y = 4

</details>