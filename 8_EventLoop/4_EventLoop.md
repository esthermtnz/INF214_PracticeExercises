## Event Loop Exercise 4 - Slides Example  3 (Timeout & Intervals)

```

<button id=“myButton”></button>
<script>
    setTimeout(function timeoutHandler() {
        /* code that runs for 6 ms */
    }, 10);

    setInterval(function intervalHandler() {
        /* code that runs for 8 ms */
    }, 10);

    const myButton = document.getElementById(“myButton”);
    myButton.addEventListener(“click”, function clickHandler() {
        /* code that runs for 10 ms */
    });
    
    /* code that runs for 18 ms */
</script>

A super quick user who clicks the button 6 ms after the script starts executing

```
<details>
<summary> Correct solution: </summary>

```
0 ms - mainline execution starts

6 ms - user clicks button

10 ms - timeout fires

10 ms - interval fires for the first time

18 ms - mainline execution finishes

18 ms - clickHandler execution starts

20 ms - interval fires for the second time

28 ms - clickHandler execution finishes

28 ms - timeoutHandler execution starts

30 ms - interval fires for the third time

34 ms - timeoutHandler execution finishes

34 ms - intervalHandler execution starts

40 ms - interval executes for the forth time

42 ms - intervalHandler execution finishes

```


#### Its correct!! Remember to use the name of the code's handlers

</details>