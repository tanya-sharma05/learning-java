# Java Collections API

## What is the Collections API?

- `Collection` → **interface** (the API contract)
- `Collections` → **class** (utility methods like sort, shuffle)
- The Collections API lets you work with multiple data structures using a unified interface.
- **`Map` does NOT belong to the Collection API** — it is a separate interface.

## Hierarchy

```
Collection (interface)
├── List   → ordered, index-based, duplicates allowed  → ArrayList, LinkedList
├── Set    → no duplicates                             → HashSet, TreeSet, LinkedHashSet
└── Queue  → FIFO order                                → LinkedList, PriorityQueue

Map (separate interface — NOT part of Collection)
    → key-value pairs                                  → HashMap, TreeMap, Hashtable
```

## When to Use What

| Need | Use |
|---|---|
| Ordered list, duplicates OK | `ArrayList` |
| Unique values, no order needed | `HashSet` |
| Unique values, sorted | `TreeSet` |
| Unique values, insertion order | `LinkedHashSet` |
| Key-value pairs | `HashMap` |
| Sorted key-value pairs | `TreeMap` |
| Thread-safe map | `Hashtable` |

## Key Points

- `Collection` is the **interface** — use it to write generic code
- `Collections` is the **class** — use it for utility operations
- All collections (List, Set, Queue) extend the `Iterable` interface, meaning they support `iterator()`
- `Map` stands apart but is equally important