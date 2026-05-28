# Stream API

## What is a Stream?

A **Stream** is a sequence of elements that supports **functional-style operations**. It does NOT store data — it processes it.

> Streams give you **new values** from existing collections — the original data is unchanged.

## Creating Streams

```java
// From a collection
list.stream();

// From an array
Arrays.stream(arr);

// From specific values
Stream.of("A", "B", "C");
```

## Types of Operations

| Type | Description | Examples |
|---|---|---|
| Intermediate | Returns a stream (lazy — not executed yet) | `filter`, `map`, `sorted`, `distinct` |
| Terminal | Triggers execution, produces result | `collect`, `forEach`, `count`, `reduce` |

## filter — keep elements matching a condition

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

List<Integer> evens = nums.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// [2, 4, 6]
```

## map — transform each element

```java
List<Integer> doubled = nums.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());
// [2, 4, 6, 8, 10, 12]
```

## reduce — combine all elements into one value

```java
int sum = nums.stream()
    .reduce(0, (a, b) -> a + b);
// or
int sum = nums.stream()
    .reduce(0, Integer::sum);
// 21
```

## sorted — sort elements

```java
// Natural order
list.stream().sorted().collect(Collectors.toList());

// Reverse order
list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

// Custom (by field)
students.stream().sorted(Comparator.comparing(s -> s.name)).collect(Collectors.toList());
```

## forEach — iterate

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

names.forEach(name -> System.out.println(name));

// Method reference
names.forEach(System.out::println);

// On Map
map.forEach((key, value) -> System.out.println(key + ": " + value));
```

## Chaining Example

```java
List<String> result = students.stream()
    .filter(s -> s.marks > 75)
    .sorted(Comparator.comparing(s -> s.name))
    .map(s -> s.name.toUpperCase())
    .collect(Collectors.toList());
```

## Parallel Stream

Splits work across **multiple CPU cores** automatically.

```java
// Sequential
list.stream().forEach(System.out::println);

// Parallel
list.parallelStream().forEach(System.out::println);
```

**Use when:** large datasets, independent operations, CPU-intensive tasks.  
**Avoid when:** small collections, order matters, operations have shared state.

## Optional Class

`Optional<T>` is a container that **may or may not contain a value** — avoids `NullPointerException`.

```java
Optional<String> opt = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(null);

// Safe usage
opt.ifPresent(n -> System.out.println(n));

String result = opt.orElse("Unknown");     // default if empty
String result2 = opt.orElseGet(() -> "?"); // lazy default

Optional<Integer> length = opt.map(String::length);
```

```java
// Real-world usage with streams
Optional<Student> found = students.stream()
    .filter(s -> s.name.equals("Alice"))
    .findFirst();

found.ifPresent(s -> System.out.println(s.marks));
```

## Key Points

- Streams don't modify original data — they produce new values
- Operations are **lazy** — nothing runs until a terminal operation is called
- Chain multiple intermediate operations before one terminal operation
- `parallelStream()` = easy parallelism, but use carefully
- `Optional` = null-safe container, prefer `ifPresent`/`orElse` over `.get()`