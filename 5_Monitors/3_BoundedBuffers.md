## Bounded Buffers Exercise: Monitors

Implement a monitor named monitorBounder_Buffer in AWAIT that manages a circular buffer with a maximum capacity of n elements. 

This monitor should allow:
- Adding an element to the buffer if space is available.
- Removing an element from the buffer if data is available.
Use conditional variables to synchronize operations and ensure processes wait when the buffer is either full or empty, as needed.

<details>
<summary>My answer in await: </summary>

    ```

    monitor monitorBounder_Buffer{

        T buf[n];
        int front = 0, rear = 0, count = 0;
        cond not_full;
        cond not_empty;

        procedure add_Element(T data){
            while (count == n) wait(not_full);
            buf[rear] = data;
            rear = (rear + 1) mod n;
            count ++;
            signal(not_empty);
        }

        procedure remove_Element(out T result){
            while(count == 0) wait(not_empty);
            result = buf[front];
            front = (front + 1) mod n; 
            count --;
            signal(not_full);
        }

    }

    ```

</details>