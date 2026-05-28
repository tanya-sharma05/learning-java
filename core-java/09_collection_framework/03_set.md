# Set, HashSet & TreeSet

## What is Set?

`Set` is a **Collection API interface** that:
- Stores **unique values only** (no duplicates)
- A duplicate `add()` is simply ignored

## HashSet

```java
import java.util.HashSet;

HashSet<String> set = new HashSet<>();
set.add("Banana");
set.add("Apple");
set.add("Banana"); // ignored — duplicate

System.out.println(set); // [Apple, Banana] — order NOT guaranteed
```

## TreeSet — Sorted Order

```java
import java.util.TreeSet;

TreeSet<String> set = new TreeSet<>();
set.add("Banana");
set.add("Apple");
set.add("Cherry");

System.out.println(set); // [Apple, Banana, Cherry] — sorted alphabetically
```

## LinkedHashSet — Insertion Order

```java
import java.util.LinkedHashSet;

LinkedHashSet<String> set = new LinkedHashSet<>();
set.add("C");
set.add("A");
set.add("B");

System.out.println(set); // [C, A, B] — insertion order preserved
```

## Comparison Table

| | Order | Sorted | Null allowed |
|---|---|---|---|
| `HashSet` | None | No | One `null` |
| `LinkedHashSet` | Insertion order | No | One `null` |
| `TreeSet` | Sorted (natural) | Yes | No `null` |

## Key Points

- **HashSet** → fastest, use when order doesn't matter
- **TreeSet** → use when you need sorted unique values
- **LinkedHashSet** → use when you need insertion-order + uniqueness
- `TreeSet` cannot contain `null` (throws `NullPointerException`)