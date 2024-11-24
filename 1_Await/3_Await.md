## Await: Exercise 3

### What are the possible solutions to this code?
```
x = 1, y = 4
co
    < x = x + y > //process 1
|| 
    < x = y - x > //process 2
oc
```

<details>
<summary> Solution: </summary>
    In this case, both are atomic operations executing as a transaction.

    1: P1 executes, P2 executes
    2: P2 executes, P1 executes

    Initial scenario:
        x = 1, y = 4
    Case 1:
        P1 executes:
        x = 1 + 4 = 5
        P2 executes:
        x = 4 - 5 = -1

        Result:
        x = -1, y = 4
    Case 2:
        P2 executes:
        x = 4 -1 = 3
        P1 executes:
        x = 3 + 4 = 7

        Result:
        x = 7, y = 4

</details>