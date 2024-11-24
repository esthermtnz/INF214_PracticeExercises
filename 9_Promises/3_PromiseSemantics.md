## Promise Semantics: Exercise 3

![alt text](image-2.png)

```
A. This rule handles the case when a pending promise is resolved.
B. This rule causes one promise to be “linked” to another.
C. This rule handles the case when a fulfill reaction is registered on a promise that is already resolved.
D. This rule registers a fulfill reaction on a pending promise.
E. None of the above. Describe what does the promise do.
```

<details>
<summary> Solution: </summary>
    E. This rule states that resolving a settled promise has no effect of the result.
</details>