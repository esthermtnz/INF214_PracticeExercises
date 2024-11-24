## Await: Exercise 4

### Does it fill the A.M.O property?

1. 
```
int x=0, y=0;
co 
    x=x+1 
|| 
    y=y+1 
oc
```

<details>
<summary> Solution: </summary>
    Yes, because there isn't any critical reference from P1 to P2 and viceversa.
</details>

2. 
```
int x=0, y=0;
co 
    x=y+1 
|| 
    y=x+1 
oc
```

<details>
<summary> Solution: </summary>
    No, because, P1 reads y and P2 reads x. There are 2 creitical references.
</details>

3. 
```
int x=0, y=0;
co 
    x=y+1 
|| 
    y=y+1 
oc
```

<details>
<summary> Solution: </summary>
    Yes, because it cointains only one critical reference, P1 reads y, but P2 doesn't read x.
</details>
