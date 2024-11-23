# Promise Graph exercise 1:
```
1:    var a = promisify({}); 
2:    var b = promisify({}); 
3:    var c = b.onResolve(...); 
4:    a.link(b); // 
5:    a.resolve(42); 
```

<details>
<summary>My answer: </summary>

![alt text](/images/image-3.png)
</details>

<details>
<summary> Correct solution: </summary>

![alt text](/images/image-4.png)
</details>

