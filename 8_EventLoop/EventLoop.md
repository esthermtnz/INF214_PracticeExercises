## Event Loop Exercise

```
<button id="myButton"></button>
<script>
 setTimeout(function timeoutHandler() {
    /* code that runs for 6 ms */
 }, 10);
 setInterval(function intervalHandler() {
    /* code that runs for 8 ms */
 }, 10);
 const myButton = document.getElementById("myButton");
 myButton.addEventListener("click", function clickHandler() {
    Promise.resolve().then( () => {
        /* some promise handling code that runs for 4 ms */ }
     );

    /* click-handling code that runs for 10 ms */
 });

 /* code that runs for 18 ms */

</script>

```
<details>
<summary> Correct solution: </summary>

```
0 ms - mainline execution starts
6 ms - user clicks button
10 ms - Timer fires
10 ms - Interval fires for the 1º time
18 ms - mainline execution finishes
18 ms - clickHandler execution starts
a tiny bit after 18 ms - promise is resolved
20 ms - Interval fires for the 2º time
28 ms - clickHandler execution finishes
28 ms - promise handler execution starts
30 ms - Interval fires for the 3º time
32 ms - promise handler execution finishes
32 ms - timeoutHandler execution starts
38 ms - timeoutHandler execution finishes
38 ms - intervalHandler execution starts
40 ms - Interval fires for the 4º time
46 ms - intervalHandler execution finishes
```


#### Its correct!! Remember to use the name of the code's handlers

</details>