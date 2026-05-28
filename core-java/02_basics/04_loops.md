# Loops

## Three Loop Types

```java
// while — checks condition BEFORE body; may never run
while (condition) {}

// do-while — checks AFTER body; runs at least once
do {} while (condition);

// for — compact; preferred when iteration count is known
for (int i = 0; i < n; i++) {}
```

**for loop flow:** init → condition check → body → increment → repeat

## Nested Loops
Inner loop completes all iterations for each step of outer loop.
```
Total iterations = outer_count × inner_count
```

## break & continue
```java
break; // exits the loop immediately
continue; // skips current iteration, moves to next
```