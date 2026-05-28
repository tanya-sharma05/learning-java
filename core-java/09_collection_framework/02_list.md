# List & ArrayList

## What is List?

`List` is a **Collection API interface** that:
- Maintains **insertion order** (index-based access)
- **Allows duplicates**
- Uses zero-based indexing

## ArrayList

`ArrayList` is the most commonly used `List` implementation. It is a **dynamic (resizable) array** — it grows automatically as you add elements.

```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();

// Add
list.add("Apple");
list.add("Banana");
list.add(1, "Mango");       // insert at index 1

// Access
System.out.println(list.get(0)); // Apple

// Update
list.set(0, "Grapes");

// Remove
list.remove("Banana");     // by value
list.remove(0);            // by index

// Size
System.out.println(list.size());

// Iterate
for (String fruit : list) {
    System.out.println(fruit);
}
```

## Iterator

All collections extend `Iterable`, so you can use an `Iterator`:

```java
import java.util.Iterator;

ArrayList<Integer> nums = new ArrayList<>();
nums.add(10);
nums.add(20);
nums.add(30);

Iterator<Integer> values = nums.iterator();
while (values.hasNext()) {
    System.out.println(values.next());
}
```

## Performance

| Operation | Time |
|---|---|
| `get(index)` | O(1) |
| `add()` at end | O(1) amortized |
| `add()`/`remove()` at middle | O(n) |

## Key Points

- Maintains **insertion order**
- Allows **duplicate** values
- Backed by an array internally; resizes automatically
- Not thread-safe (use `Collections.synchronizedList()` if needed)