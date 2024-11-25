## Generators & Iterators : Exercise 1

### What would the value of p be in each step ?
```

function *g() {
    yield “A”;
    yield “B”;
    return "Yeaaah";
}

const p = g();
const r1 = p.next();
const r2 = p.next();
const r3 = p.next();
```

<details>
<summary> Solution: </summary>

#### Step 1
const p = g(); => Generator is created but nothing happens

#### Step 2
const r1 = p.next(); => Generator begins execution until first yield and returns the value
```
{value: “A”, done: false}
```

#### Step 3
const r2 = p.next(); => Generator resumes execution until second yield and returns the value
```
{value: “B”, done: false}
```

#### Step 4
const r3 = p.next(); => Generator resumes execution until the return statement. The generator is now completed, and the return value is:
```
{value: “Yeaaah”, done: true}
```

</details>