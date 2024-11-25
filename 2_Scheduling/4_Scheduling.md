## Scheduling policies:  Exercise 1

### Will this program terminate if it has a strongly fair policy? Why?
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

Yes it will terminate eventually, because if it is strongly fair we assume that signal = true will be **infinitely often true**, allowing the process 
`<await (signal) should_continue = false;>` to be executed.
</details>
