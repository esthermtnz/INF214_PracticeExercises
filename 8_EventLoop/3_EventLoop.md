## Event Loop Exercise 3 - Slides Example  2 (Macrotasks & Microtasks)

```

 <button id=“first”></button>
 <button id=“second”></button>
 <script>
    const first = document.getElementById(“first”);
    const second = document.getElementById(“second”);
    first.addEventListener(“click”, () => {
    Promise.resolve().then( () =>
    { /* some promise handling code that runs for 4 ms */ })
        /* some click handling code that runs for 8 ms */
    });
    second.addEventListener(“click”, () => { /* code that runs for 5 ms */ });
    /* code that runs for 15 ms */
 </script>

 • first button is clicked after 5 ms
 • second button is clicked after 12 ms


```

<details>
<summary> Correct solution: </summary>

```

0 ms - mainline execution starts
5 ms - first button is clicked
12 ms - second button is clicked
15 ms - mainline execution finishes
15 ms - first click handler execution starts
a tiny bit after 15 ms - promise resolved
23 ms - first click handler execution finishes
23 ms - promise handler execution starts
27 ms - promise handler execution finishes
27 ms - second click handler execution starts
32 ms - second click handler execution finishes

```


#### Its correct!! Remember to use the name of the code's handlers

</details>