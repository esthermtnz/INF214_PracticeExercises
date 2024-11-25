## Scheduling policies:  Exercise 1

### Will this program terminate if it has a strongly fair policy? Why?
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

Yes it will terminate eventually, because if it is strongly fair we assume that can_proceed = true will be **infinitely often true**, allowing the process 
`<await (can_proceed) should_continue = false;>` to be executed.
</details>
