## Generators & Iterators : Exercise 2

### What would the value of p be in each step ?

function *g(x) {
    yield “Hi ” + x;
    return “Hello ” + x;
}

const p = g(“person”);
const result1 = p.next();
const result2 = p.next();

<details>
<summary> Solution: </summary>

#### Step 1
const p = g(“person”); => Generator is created and "person" is sent to the function.

#### Step 2
const result1 = p.next(); => Generator begins execution until first yield and returns the value
```
{value: “Hi person”, done: false}
```

#### Step 3
const result2 = p.next(); => Generator resumes execution until the return statement. The generator is now completed, and the return value is:
```
{value: “Hello person”, done: true}
```

</details>