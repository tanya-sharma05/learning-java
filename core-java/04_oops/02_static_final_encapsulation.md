# Static, Final & Encapsulation

## `static` Keyword

Applies to **variables** and **methods** only (not parameters or top-level classes).

### Static Variables

Shared across all instances — stored in the method area, not per-object on the heap.

```java
class Student {
    String name;            // per-object
    static String college;  // shared by all students
}

Student.college = "IIT D";  // preferred: access via class name
```

### Static Methods

Belong to the class, not an instance. Three restrictions:
1. Can only call other static methods.
2. Can only access static variables.
3. No access to `this`.


> **Why `main` is static:** JVM needs to call it before any object exists, so it must belong to the class directly.

---

## `final` Keyword

Applies to variables, methods, classes, and parameters.

| Target | Effect |
|---|---|
| Variable | Cannot be reassigned (constant) |
| Method | Cannot be overridden |
| Class | Cannot be subclassed |

```java
final double PI = 3.14;       // constant
final class ImmutableBox {}    // no subclassing
```

---

## Encapsulation

Bundling data + behaviour together, and **controlling access** to data.

### Access Modifiers

| Modifier | Same Class | Same Package | Subclass | Other Package |
|---|---|---|---|---|
| `private` | ✅ | ❌ | ❌ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

### Getters & Setters

Mark fields `private`, expose controlled access through methods:

```java
class BankAccount {
    private double balance;

    public void deposit(int amount) { 
        balance += amount; 
    }

    public void withdraw(int amount) { 
        balance -= amount; 
    }

    public double getBalance() { 
        return balance; 
    }
}
```

Direct access like `ba.balance = 1000` is blocked — only the class controls its own state.

---

## Packages

Group related classes. Import with `import packageName.ClassName;` or `import packageName.*`.

```
java.util   → ArrayList, Scanner
java.lang   → String, Integer (auto-imported)
java.io     → File I/O streams
```
