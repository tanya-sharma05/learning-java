# Map

## What is Map?

- `Map` is **NOT part of the Collection API** — it is a separate interface
- Stores **key-value pairs**
- **Keys are a Set** (unique — no duplicate keys)
- **Values are a List** (can be duplicates)
- A duplicate key **overwrites** the previous value

## HashMap

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> map = new HashMap<>();

// Put
map.put("Alice", 90);
map.put("Bob", 85);
map.put("Alice", 95); // overwrites → Alice = 95

// Get
System.out.println(map.get("Bob")); // 85

// Check existence
map.containsKey("Alice");   // true
map.containsValue(85);      // true

// Iterate
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Remove
map.remove("Bob");

// Size
System.out.println(map.size());
```

## HashMap vs Hashtable

| | `HashMap` | `Hashtable` |
|---|---|---|
| Thread-safe | ❌ No | ✅ Yes (synchronized) |
| Null keys | ✅ One null key | ❌ Not allowed |
| Performance | Faster | Slower |
| Recommended | ✅ For most use | Use `ConcurrentHashMap` instead |

## Map Types Comparison

| | Order | Sorted | Thread-safe |
|---|---|---|---|
| `HashMap` | No | No | No |
| `LinkedHashMap` | Insertion order | No | No |
| `TreeMap` | Sorted (by key) | Yes | No |
| `Hashtable` | No | No | Yes |

## TreeMap — Sorted Keys

```java
import java.util.TreeMap;

TreeMap<String, Integer> map = new TreeMap<>();
map.put("Banana", 2);
map.put("Apple", 5);
map.put("Cherry", 1);

System.out.println(map); // {Apple=5, Banana=2, Cherry=1} — sorted by key
```

## Key Points

- Keys → unique (`Set` behavior)
- Values → can repeat (`List` behavior)
- `HashMap` is unsynchronized; `Hashtable` is synchronized (but prefer `ConcurrentHashMap` in modern code)
- Use `TreeMap` when keys need to be sorted