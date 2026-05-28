# Comparator

## What is Comparator?

`Comparator` is an **interface** through which you can write your **own custom sorting logic** — without modifying the original class.

## Comparable vs Comparator

| | `Comparable` | `Comparator` |
|---|---|---|
| Defined in | The class itself | External / separate |
| Method | `compareTo()` | `compare()` |
| Sort type | Natural / default | Custom |
| Modifies class? | Yes | No |

## Comparable — Natural Order (inside the class)

```java
class Student implements Comparable<Student> {
    String name;
    int marks;

    public int compareTo(Student other) {
        return this.marks - other.marks; // ascending by marks
    }
}

List<Student> students = new ArrayList<>();
Collections.sort(students); // uses compareTo
```

## Comparator — Custom Order (outside the class)

```java
import java.util.Comparator;

// By name
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
students.sort(byName);

// By marks descending
students.sort((s1, s2) -> s2.marks - s1.marks);

// Using Comparator.comparing (cleaner)
students.sort(Comparator.comparing(s -> s.name));

// Chained comparators
students.sort(Comparator.comparing((Student s) -> s.marks)
                        .thenComparing(s -> s.name));
```

## With TreeSet / TreeMap

```java
TreeSet<Student> set = new TreeSet<>(Comparator.comparing(s -> s.name));
set.add(new Student("Zara", 80));
set.add(new Student("Alice", 90));
// Sorted alphabetically by name
```

## Key Points

- `Comparator` is the interface for writing custom sorting logic
- Use it when you can't (or don't want to) modify the class
- Works with `sort()`, `TreeSet`, `TreeMap`, streams
- Lambda expressions make Comparator very clean to write
- `Comparator.comparing()` and `.thenComparing()` allow multi-level sorting