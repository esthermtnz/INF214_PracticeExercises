## Scheduling policies:  Exercise 2

### Will this program terminate if it has a weakly fair policy? Why?

```

bool should_continue = true;
bool signal = false;
co
    while (should_continue) {
        signal = !signal;
    }
|| 
    <await (signal) should_continue = false;>
oc

```

<details>
<summary> Solution: </summary>

No the program might not terminate, because if it is weakly fair signal will be **infinitely often false**, so
`<await (signal) should_continue = false;>` might not be executed.

</details>