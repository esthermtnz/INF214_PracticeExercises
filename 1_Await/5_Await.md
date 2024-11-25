## How would you implement this code?

```
in1 = false;
in2 = false;

process CS[1] {
    while(true) {
        <await (!in2) in1=true;>
        critical section;
        in1 = false;
        non-critical section;
    }
}

process CS[2] {
    while(true) {
        <await (!in1) in2=true;>
        critical section;
        in2 = false;
        non-critical section;
    }
}

```

<details>
<summary> Solution: </summary>

Implementing the Ticket Algorithm.

```
in1 = false;
in2 = false;
last = 1;

process CS[1] {
    while(true) {
        in1 = true;
        last = 1;
        while (in2==true and last == 1) {}; //mientras in2 quiera pasar y yo haya sido el ultimo en pasar => ESPERO
        critical section;
        in1 = false;
        non-critical section;
    }
}

process CS[2] {
    while(true) {
        in2 = true;
        last = 2;
        while (in1==true and last == 2) in2=true; //mientras in1 quiera pasar y yo haya sido el ultimo en pasar => ESPERO
        critical section;
        in2 = false;
        non-critical section;
    }
}

```
</details>