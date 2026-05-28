# Conditional Statements

## if / if-else
```java
if (condition) {}
else {}
```
Use `if (b)` not `if (b == true)` — they're the same.

## switch
```java
switch (i) {
    case 1: 
        ...; 
        break;
    case 2: 
        ...; 
        break;
    default: 
        ...; 
        break;
}
```

## switch vs if-else-if
| | `switch` | `if-else-if` |
|--|----------|--------------|
| Condition | Equality only | Equality & inequality |
| Performance | O(1) via jump tables | Linear |
| Use case | Fixed discrete values | Flexible/range conditions |

## JVM Jump Tables (Switch Internals)
- **`tableswitch`** — dense/consecutive case values → O(1) array-index lookup
- **`lookupswitch`** — sparse values → key-value search

## Nested switch
A `switch` can be placed inside another `switch`, just like nested `if`.