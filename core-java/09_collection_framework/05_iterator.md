# Iterator

## What is Iterator?

`Iterator` is an **interface** that allows you to traverse (loop through) elements of any Collection one at a time.

All Collection types (List, Set, Queue) **extend the `Iterable` interface**, which means they all provide an `iterator()` method.

## Basic Usage

```java
import java.util.ArrayList;
import java.util.Iterator;

ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);

// Get iterator
Iterator<Integer> values = list.iterator();

while (values.hasNext()) {
    System.out.println(values.next());
}
```

## Iterator Methods

| Method | Description |
|---|---|
| `hasNext()` | Returns `true` if more elements exist |
| `next()` | Returns the next element |
| `remove()` | Removes the last element returned by `next()` |

## Safe Removal During Iteration

```java
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    int val = it.next();
    if (val == 20) {
        it.remove(); // safe — won't throw ConcurrentModificationException
    }
}
```

> ⚠️ Never use `list.remove()` inside a for-each loop — it throws `ConcurrentModificationException`. Always use `iterator.remove()` instead.

## for-each vs Iterator

```java
// for-each (internally uses Iterator)
for (String s : list) {
    System.out.println(s);
}

// Explicit Iterator (needed for safe removal)
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
}
```

## Key Points

- All Collections implement `Iterable` → support `iterator()`
- `Iterator<Integer> values = array.iterator();` — store it in `Iterator<Type>`
- Use `iterator.remove()` for safe deletion during iteration
- for-each loops use Iterator internally