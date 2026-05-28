# Enums & Interfaces

## Enums

A special class representing a **fixed set of constants**. Extends `java.lang.Enum`. Each constant is a `public static final` object of that enum type.

```java
enum Direction { 
    NORTH, 
    SOUTH, 
    EAST, 
    WEST; 
}

Direction d = Direction.NORTH;
```

### Enums with Fields (States)

```java
enum Direction {
    NORTH(0), 
    SOUTH(180), 
    EAST(90), 
    WEST(270);

    private final int degrees;

    Direction(int degrees) { 
        this.degrees = degrees; 
    }

    public int getDegrees() { 
        return degrees;
    }
}

Direction.NORTH.getDegrees();  // 0
```

### Enums with Abstract Methods (Behaviour per Constant)

```java
enum Direction {
    NORTH { 
        public void move() { 
            System.out.println("Moving Up"); 
        } 
    },

    SOUTH { 
        public void move() { 
            System.out.println("Moving Down"); 
        } 
    };

    public abstract void move();
}
```

### Built-in Enum Methods

| Method | Description |
|---|---|
| `values()` | Array of all constants (compiler-generated) |
| `valueOf(String)` | Constant matching the name (compiler-generated) |
| `name()` | Name of the constant as String |
| `ordinal()` | Position/index of the constant |

> `name()` and `toString()` do the same thing, but `toString()` can be overridden.

---

## Interfaces

Defines **what an object can do** (behaviour contract), not what it is. A class is a blueprint of an object; an interface is a blueprint of behaviour.

```java
interface Flyable { 
    void fly(); 
}

class Bird implements Flyable { 
    public void fly() { 
        // implementation 
    } 
}
```

- All variables are implicitly `public static final`
- All methods are implicitly `public abstract` (unless default/static/private)
- No constructors, no instance fields
- Supports **multiple inheritance** (a class can implement many interfaces)

### Default Methods (Java 8+)

Provide a method body inside an interface. Useful for adding new methods without breaking existing implementations.

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
        B.super.fun();  // call specific interface's default
        C.super.fun();
    }
}
```

If two interfaces have the same default method, the implementing class **must** override it.

### Resolution Priority

When a method exists in multiple places, Java resolves in this order:
1. **Class method** (highest priority)
2. Child interface default method
3. Parent interface default method

```java
class B { 
    public void fun() { 
        ... 
    } 
}

interface A { 
    default void fun() { 
        ... 
    } 
}

class C extends B implements A {}

new C().fun();  // calls B's version — class wins over interface
```

### Special Interface Types

**Functional Interface** — exactly one abstract method. Enables lambda expressions.

```java
interface Runnable { 
    void run(); 
}  // functional interface

Runnable r = new Runnable() {
            public void run() {
                System.out.println("running");
            }
        };

Runnable r = () -> System.out.println("running");
```

**Marker Interface** — empty interface. Signals a capability to the JVM.

```java
interface Cloneable {}      // marks class as safe to clone
interface Serializable {}   // marks class as serializable
```