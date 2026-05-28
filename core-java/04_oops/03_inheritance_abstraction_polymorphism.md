# Inheritance, Abstraction & Polymorphism

## Inheritance

One class acquires properties and behaviours of another. 
> Models an **"is-a"** relationship.

Benefits: **code reusability** and enables **polymorphism**.

```java
class Vehicle { 
    void start() {} 
}

class Car extends Vehicle {
    void setGear() {}
}

Car c = new Car();
c.start();    // inherited ✅
c.setGear();  // own method ✅
```

### `super` Keyword

Reference to the parent class. Three uses:

```java
super.x          // access parent's field (when child shadows it)
super.method()   // call parent's method
super()          // call parent's constructor — must be first statement
```

### No Multiple Inheritance (Diamond Problem)

If `D extends B, C` and both `B`, `C` inherit `fun()` from `A`, it's ambiguous which `fun()` `D` gets. Java solves this with **interfaces**.

---

## Abstraction

Focus on **what** something does, hide **how** it does it.

### Abstract Classes — Partial Abstraction

Mix of abstract (no body) and concrete (with body) methods. Cannot be instantiated.

```java
abstract class Car {
    // concrete
    void start() { 
        System.out.println("Car started"); 
    }  

    // must be implemented
    abstract void accelerate(); 
    abstract void brake();
}

class ElectricCar extends Car {
    void accelerate() { 
        System.out.println("Silent acceleration"); 
    }

    void brake() { 
        System.out.println("Regen braking"); 
    }
}
```

**Use when:** objects share a common family (Car → ElectricCar, FuelCar).

### Interfaces — Pure Abstraction

Only defines *what*, never *how*. All methods are abstract by default. Cannot be instantiated.

```java
interface Car {
    void start();
    void accelerate();
    void brake();
}

class ElectricCar implements Car {
    public void start()      {}
    public void accelerate() {}
    public void brake()      {}
}

Car c = new ElectricCar();  // polymorphic reference ✅
```

**Use when:** defining capabilities/roles shared across unrelated classes (e.g., `Flyable`, `Comparable`).

### Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---|---|---|
| Methods | Abstract + concrete | Abstract by default |
| Fields | ✅ | Only `static final` |
| Constructor | ✅ | ❌ |
| Multiple inheritance | ❌ | ✅ |
| Keyword | `extends` | `implements` |
| Relationship | "is-a" | "can-do" |

---

## Polymorphism

Same method name, different behaviour depending on context.

### Compile-Time (Method Overloading)

Same name, different parameters — resolved at **compile time**.

```java
void run() {}
void run(boolean fast) {}

h.run();       // calls first
h.run(true);   // calls second
```

### Runtime (Method Overriding)

Child class re-implements a parent method — resolved at **runtime** based on the actual object.

```java
abstract class Animal { 
    abstract void run(); 
}

class Dog extends Animal { 
    void run() { 
        System.out.println("48 km/h"); 
    } 

}
class Human extends Animal { 
    void run() { 
        System.out.println("12 km/h"); 
    } 
}

Animal a = new Dog();
a.run();  // → "48 km/h"  (decided at runtime, not compile time)
```

Use `@Override` annotation (optional but recommended) to catch mistakes.

### Overloading vs Overriding

| Feature | Overloading | Overriding |
|---|---|---|
| Resolved at | Compile time | Runtime |
| Location | Same class | Parent + child |
| Parameters | Must differ | Must match |

### What Cannot Be Overridden

- `static` methods → **method hiding** (resolved by reference type, not object)
- `final` methods → compile error
- `private` methods → not visible to child; a same-name method in child is a new method

```java
A a = new B();
a.fun();  // if fun() is static → prints A's version (reference type wins)
          // if fun() is non-static → prints B's version (object type wins)
```