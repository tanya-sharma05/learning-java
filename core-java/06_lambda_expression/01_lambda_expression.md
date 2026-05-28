## 1. Lambda Expressions

A **lambda expression** is a short block of code that takes parameters and returns a value. It is Java's way of writing **anonymous functions** (functions without a name).

**Syntax:**
```java
(parameters) -> expression
```

**Example:**
```java
// Without lambda
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello!");
    }
};

// With lambda
Runnable r = () -> {
        System.out.println("Hello!");
    };

Runnable r = () -> System.out.println("Hello!");    
```

**Key Points:**
- Introduced in Java 8
- Used mainly with **functional interfaces**
- Makes code shorter and more readable
- No need to write the full anonymous class boilerplate

---

## 2. Lambda Expression with Return

When a lambda has more than one statement or needs to return a value explicitly, use curly braces `{}` and the `return` keyword.

**Syntax:**
```java
(parameters) -> {
    // multiple statements
    return value;
}
```

**Example:**
```java
// Single expression (return is implicit)
MathOperation add = (a, b) -> a + b;

// Multi-line with explicit return
MathOperation multiply = (a, b) -> {
    int result = a * b;
    return result;
};
```

**Key Points:**
- If the body is a single expression → no `{}`, no `return`
- If the body has multiple lines → use `{}` and `return`