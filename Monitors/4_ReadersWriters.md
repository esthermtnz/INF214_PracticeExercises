## Readers and Writers Exercise: Monitors

Implement a monitor named ReadersWriters in AWAIT to solve the classic readers-writers problem, where multiple readers can access a shared resource simultaneously, but writers require exclusive access.
Use conditional variables to coordinate access between readers and writers, ensuring mutual exclusion and prioritization as required.

<details>
<summary>My answer in await: </summary>

    ```

    monitor ReadersWriters{

        int nr = 0;
        int nw = 0;
        cond ok_to_read;
        cond ok_to_write;

        procedure request_read() {
            while (nw>0) wait(ok_to_read);
            nr = nr + 1;
        }

        procedure release_read(){
            nr = nr - 1;
            if (nr == 0) signal (ok_to_write);
        }

        procedure request_write(){
            while (nr>0 OR nw> 0) wait(ok_to_read);
            nw = nw + 1;
        }

        procedure release_write(){
            nw = nw - 1; // no hace falta hacer if nw == 0 pq solo hay un writer en la db
            signal(ok_to_write);
            signal_all(ok_to_read);
        }
    }

    ```

</details>