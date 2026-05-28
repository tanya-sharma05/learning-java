# POJO, Immutable & Object Class

## POJO (Plain Old Java Object)

A simple class with no framework dependencies. Contains: fields, constructor(s), getters/setters. No hard-coded business logic.

```java
class Student {
    String name;
    int age;

    Student(String name, int age) { 
        this.name = name; 
        this.age = age; 
    }

    String getName() { 
        return name; 
    }

    void setName(String name) { 
        this.name = name; 
    }
}
```

**Anemic Model** → only fields + getters/setters (no logic inside).

**Rich Domain Model** → includes business methods like `markAttendance()` inside the class.

---

## Immutable Classes

An object whose state **cannot change** after creation. 

Benefits: thread safety, security, safe caching.

**Three rules:**
1. Mark class as `final` (prevents subclassing)
2. All fields `private final` (no reassignment, no external access)
3. No setters

```java
final class Student {
    private final String name;
    private final int age;
    private final College college; // College is class here

    Student(String name, int age, College college) {
        this.name = name;
        this.age = age;
        this.college = new College(college.name, college.addr); // defensive copy
    }

    public College getCollege() {
        return new College(this.college.name, this.college.addr); // defensive copy
    }
}
```

**Defensive copy** is needed for mutable fields (like `College`) — copy in both constructor and getter, otherwise a shallow reference allows external mutation.

---

## The Object Class

Every Java class implicitly extends `java.lang.Object`. Key methods:

### `toString()`
Default returns `ClassName@HexCode`. Override for meaningful output.

```java
@Override
public String toString() { 
    return name + ", " + age; 
}
```

### `equals()`
Default compares **references** (like `==`). Override to compare field values.

```java
Student s1 = new Student("Aditya", 28);
Student s2 = new Student("Aditya", 28);
s1.equals(s2);  // false by default (different references)
```

### `hashCode()`
Returns an integer representing the object. 

**Contract:** if `equals()` returns `true`, `hashCode()` must be equal. Used by `HashMap`, `HashSet`.

```java
return Objects.hash(name, age);  // simple implementation
```

### `getClass()`
Returns runtime class. `final` — cannot be overridden.

```java
s1.getClass().getName();  // "Student"
```

### `clone()`
Creates a **shallow copy** by default. Class must implement `Cloneable` (a marker interface) or `CloneNotSupportedException` is thrown. Override for deep copy.

### Thread methods
`wait()`, `notify()`, `notifyAll()` — for thread synchronization (every object can be a monitor).

---

## Autoboxing & Wrapper Classes

| Primitive | Wrapper |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

**Autoboxing** → `int` to `Integer` automatically.
**Unboxing** → `Integer` to `int` automatically.

```java
Integer y = 10; // autoboxing: Integer.valueOf(10) internally
int x = y; // unboxing: y.intValue() internally
```

**Always use `.equals()` for wrapper comparison** — `==` compares references, not values.

```java
Integer a = 200, b = 200;
a == b        // false (different objects)
a.equals(b)   // true
```

**Integer Cache:** Java caches `Integer` values from **-128 to +127**. Within this range, `==` returns `true` because the same cached object is returned. Outside this range, new objects are created.

```java
Integer a = 100, b = 100;  a == b  // true (cached)
Integer a = 200, b = 200;  a == b  // false (not cached)
```