## Event Loop Exercise 2 - Slides Example  1 (Macrotasks)

```

<button id=“first”></button>
<button id=“second”></button>
<script>
    const first = document.getElementById(“first”);
    const second = document.getElementById(“second”);
    first.addEventListener(“click”, () => {/* code that runs for 8 ms */});
    second.addEventListener(“click”, () => {/* code that runs for 5 ms */});
    /* code that runs for 15 ms */
</script>

clicks the first button 5 ms after the script starts executing
clicks the second button 12 ms after the script starts executing
```
<details>
<summary> Correct solution: </summary>

```
0 ms - mainline execution starts
5 ms - user clicks first button
12 ms - user clicks second button
15 ms - mainline execution finishes
15 ms - first click handler execution starts 
23 ms - first click handler execution finishes
23 ms - second click handler execution starts
28 ms - second click handler execution finishes

```

#### Its correct!! Remember to use the name of the code's handlers

</details>