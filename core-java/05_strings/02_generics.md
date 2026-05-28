# Generics

## What are Generics?

Generics enforce **type safety at compile time**. They let you write general-purpose, reusable code that works with different types — while catching type errors early.

## The Problem Without Generics

Before Generics, `Object` was used as a catch-all type. This caused:
- Type information lost — you don't know what's inside
- Wrong objects could be inserted — no type check
- Manual casting required when reading — error-prone
- Errors shift to **runtime** instead of compile time

## Generic Class

```java
class Box<T> {
    T value;
}

Box<String> b1 = new Box<>();   // T = String
Box<Integer> b2 = new Box<>();  // T = Integer
```

`T` is a **type parameter** — a placeholder for any type you specify.

> Only **reference types** allowed: `Integer`, `String`, `Student`, etc.  
> Primitives (`int`, `double`) are NOT allowed directly.

## Generic Method

```java
public class Demo {
    public static void main(String[] args) {
        Integer result = getResult(23);
        String text = getResult("Hello");
    }

    public static <T> T getResult(T x) {
        return x;
    }
}
```

## Upcasting & Downcasting

### Upcasting (Child → Parent) — automatic, always safe

```java
class Animal {}
class Dog extends Animal {}

Animal a = new Dog(); // Upcasting — safe
```

### Downcasting (Parent → Child) — explicit, may fail at runtime

```java
Object obj = "Hello";
String s = (String) obj; // explicit downcast — must be correct type
```

| Scenario | Error Type |
|---|---|
| `int x = "Hello"` | Compile time |
| Wrong downcast | Runtime (`ClassCastException`) |

## Upper Bounded Generics

Restrict the type to a specific class or its subclasses:

```java
class Box<T extends Number> {
    T value;
    public void printDouble() {
        System.out.println(value.doubleValue());
    }
}

Box<Integer> b = new Box<>(); // OK
Box<String> b2 = new Box<>(); // ❌ Error — String is not a Number
```

## Generics with Interfaces

```java
<T extends InterfaceName>

interface Swimmable {
    void swim();
}

class Fish implements Swimmable {
    public void swim() {
        System.out.println("Fish is swimming");
    }
}
```

## Wildcard

```java
// Accepts any type
public void print(Box<?> box) {}

// Upper bounded wildcard
public void sum(List<? extends Number> list) {}

// Lower bounded wildcard
public void add(List<? super Integer> list) {}
```