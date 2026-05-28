# Interfaces

## What is an Interface?

An interface defines **what an object can do** without specifying how. It is a **contract / blueprint of behaviour**.

- A **class** = blueprint of an object
- An **interface** = blueprint of behaviour

```java
interface Car {
    void drive(); // abstract by default
}
```

## Rules

- Variables in interfaces are always `static` and `final`
- Methods are `abstract` by default (unless `default`/`static`/`private`)
- Interfaces **cannot** have instance fields or constructors
- Supports **multiple inheritance**

## Extends & Implements

```
class    extends   class
interface extends  interface
class    implements interface
```

## Interface vs Abstract Class

| Feature | Interface | Abstract Class |
|---|---|---|
| Fields | Only `static final` | Any type |
| Constructor | ❌ | ✅ |
| Multiple Inheritance | ✅ | ❌ |
| Methods | Public by default | Any access modifier |
| Relationship | "Can-do" | "Is-a" |

## Interface vs Class

| | Interface | Class |
|---|---|---|
| Purpose | Contract / Roles | Blueprint of object |
| Relationship | "Can-do" | "Is-a" |
| Examples | `Runnable`, `Payable` | `Car`, `Student` |

## Types of Interfaces

### 1. Normal Interface

```java
interface Animal {
    void eat();
    void sleep();
}
```

### 2. Functional Interface (exactly one abstract method)

```java
@FunctionalInterface
interface Greet {
    void sayHello(String name); // only one abstract method
}

// Use with lambda
Greet g = name -> System.out.println("Hello " + name);
```

**Built-in Functional Interfaces (`java.util.function`):**

| Interface | Takes | Returns |
|---|---|---|
| `Predicate<T>` | T | boolean |
| `Function<T,R>` | T | R |
| `Consumer<T>` | T | nothing |
| `Supplier<T>` | nothing | T |

### 3. Marker Interface (no methods)

```java
interface Cloneable {}   // marks a class as cloneable
interface Serializable {} // marks a class as serializable
```

## Default Methods (Java 8+)

Interfaces can have concrete methods using `default`:

```java
interface B {
    default void fun() { 
        System.out.println("B"); 
    }
}

interface C {
    default void fun() { 
        System.out.println("C"); 
    }
}

class D implements B, C {
    @Override
    public void fun() {
        B.super.fun(); // call specific interface method
        C.super.fun();
    }
}
```

## Resolution Priority

When a class inherits from both a class and interface with the same method:

```
1. Class methods
2. Child interface methods
3. Parent interface methods
```

```java
interface A {
    default void fun() { 
        System.out.println("Interface A"); 
    }
}

class B {
    public void fun() { 
        System.out.println("Class B"); 
    }
}

class C extends B implements A {}

new C().fun(); // prints "Class B" — class wins
```

## Why No Multiple Inheritance for Classes?

Diamond problem: if `D` inherits from both `B` and `C`, and both override `A.fun()`, it's ambiguous which `fun()` `D` gets.

**Solution:** use interfaces — if `D` is a class and `A, B, C` are interfaces, `D` must override `fun()` itself.