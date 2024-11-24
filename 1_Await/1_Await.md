## Await: Exercise 1

### What are the possible solutions to this code?
Imagine x is initialize as 0.

```
co 
    x = x + 1; // process 1
|| 
    x = x - 1; // process 2
oc
```

<details>
<summary> Solution: </summary>
    As the operations are not atomic, this operation can take multiple results on the shared variable x:

    R1          R2
    inc         dec
    W1          W2

    1: P1 reads and writes, P2 reads and writes
    2: P1 reads, P2 reads, P1 writes, P2 writes (overwrites)
    3: P1 reads, P2 reads, P2 writes, P1 writes (overwrites)
    4: P2 reads, P1 reads, P1 writes, P2 writes (overwrites)
    5: P2 reads, P1 reads, P2 writes, P1 writes (overwrites)
    6: P2 reads and write, P1 reads and writes

            1:  2:  3:  4:  5:  6:
            R1  R1  R1  R2  R2  R2
            W1  R2  R2  R1  R1  W2
            R2  W1  W2  W1  W2  R1
            W2  W2  W1  W2  W1  W1

Solution:   0  -1   1   -1  1   0

We get 6 results, 2 of them are correct and the 4 others should be incorrect.

</details>