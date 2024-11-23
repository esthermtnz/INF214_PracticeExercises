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

```


#### Its correct!! Remember to use the name of the code's handlers

</details>