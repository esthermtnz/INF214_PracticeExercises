## Scheduling policies:  Exercise 2

### Will this program terminate if it has a weakly fair policy? Why?

```

bool should_continue = true;
bool can_proceed = false;
co
    while (should_continue) {
    can_proceed = true;
    can_proceed = false;
}
||
    <await (can_proceed) should_continue = false;>
oc

```

<details>
<summary> Solution: </summary>

No the program might not terminate, because if it is weakly fair can_proceed will be **infinitely often false**, so
`<await (can_proceed) should_continue = false;>` might not be executed.

</details>