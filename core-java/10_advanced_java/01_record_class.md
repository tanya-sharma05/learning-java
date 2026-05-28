# Record Classes

## What is a Record?

A **record** (Java 16+) is a special class designed for **immutable data carriers**. It auto-generates boilerplate: constructor, getters, `equals()`, `hashCode()`, and `toString()`.

## The Problem Records Solve

Before records, a simple data class needed a lot of boilerplate:

```java
// Old way — lots of boilerplate
class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return x; }
    public int y() { return y; }

    @Override
    public boolean equals(Object o) { ... }

    @Override
    public int hashCode() { ... }

    @Override
    public String toString() { return "Point[x=" + x + ", y=" + y + "]"; }
}
```

## Record — Same Thing, One Line

```java
record Point(int x, int y) {}
```

Java auto-generates everything above.

## Usage

```java
Point p = new Point(3, 4);

System.out.println(p.x());       // 3
System.out.println(p.y());       // 4
System.out.println(p);           // Point[x=3, y=4]

Point p2 = new Point(3, 4);
System.out.println(p.equals(p2)); // true
```

## Records are Immutable

```java
record Person(String name, int age) {}

Person person = new Person("Alice", 25);
// person.name = "Bob"; ❌ Cannot modify — fields are final
```

## Custom Compact Constructor

Add validation inside the record:

```java
record Person(String name, int age) {
    Person {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        name = name.trim(); // normalize
    }
}
```

## Custom Methods in Record

```java
record Circle(double radius) {
    public double area() {
        return Math.PI * radius * radius;
    }
}

Circle c = new Circle(5.0);
System.out.println(c.area()); // 78.539...
```

## Record vs Class

| Feature | Record | Regular Class |
|---|---|---|
| Boilerplate | None — auto-generated | Manual |
| Mutability | Immutable (final fields) | Mutable by default |
| Inheritance | Cannot extend classes | Can extend |
| Implements interface | ✅ Yes | ✅ Yes |
| Use case | Data carriers, DTOs | General purpose |

## Records Can Implement Interfaces

```java
interface Printable {
    void print();
}

record Student(String name, int marks) implements Printable {
    public void print() {
        System.out.println(name + ": " + marks);
    }
}
```