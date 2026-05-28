# Sealed Classes

## What are Sealed Classes?

**Sealed classes** (Java 17+) restrict **which classes can extend or implement them**. This gives you control over your class hierarchy.

## Problem Without Sealed

Without sealed, any class anywhere can extend your class — you lose control over the hierarchy.

## Sealed Class

```java
public sealed class Shape permits Circle, Rectangle, Triangle {
    // only Circle, Rectangle, Triangle can extend Shape
}
```

```java
// Permitted subclasses must be in the same package (or module)
final class Circle extends Shape {
    double radius;
}

non-sealed class Rectangle extends Shape {
    double width, height;
}

sealed class Triangle extends Shape permits EquilateralTriangle {
    double base, height;
}
```

## Permitted Subclass Types

Each permitted subclass **must** be one of:

| Modifier | Meaning |
|---|---|
| `final` | Cannot be extended further |
| `sealed` | Can be extended, but only by its own permitted list |
| `non-sealed` | Can be extended freely by anyone |

## Sealed Interface

```java
public sealed interface Drawable permits Circle, Rectangle {
    void draw();
}

final class Circle implements Drawable {
    public void draw() { 
        System.out.println("Drawing circle"); 
    }
}

non-sealed class Rectangle implements Drawable {
    public void draw() { 
        System.out.println("Drawing rectangle"); 
    }
}
```

## Why Use Sealed?

- **Exhaustive pattern matching** — compiler knows all possible subtypes
- **Better domain modeling** — express that only specific types are valid
- **Works great with `switch` expressions** (Java 21+)

```java
String describe(Shape s) {
    return switch (s) {
        case Circle c    -> "Circle with radius " + c.radius;
        case Rectangle r -> "Rectangle " + r.width + "x" + r.height;
        case Triangle t  -> "Triangle";
        // No default needed — compiler knows all cases!
    };
}
```