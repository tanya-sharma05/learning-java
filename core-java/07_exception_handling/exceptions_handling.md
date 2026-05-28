# Exception Handling

## What is an Exception?

An **exception** is an unexpected event during program execution that disrupts the normal flow.

## Types

| Type | When | Examples |
|---|---|---|
| **Checked** | Compile time | `IOException`, `SQLException` |
| **Unchecked** | Runtime | `NullPointerException`, `ArrayIndexOutOfBoundsException` |
| **Error** | JVM-level, don't catch | `OutOfMemoryError`, `StackOverflowError` |

## Exception Hierarchy

```
Throwable
├── Error               ← don't catch
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── Checked         ← must handle
    │   ├── IOException
    │   ├── SQLException
    │   └── FileNotFoundException
    └── RuntimeException (Unchecked)
        ├── NullPointerException
        ├── ArithmeticException
        ├── ArrayIndexOutOfBoundsException
        └── NumberFormatException
```

## try-catch-finally

```java
try {
    int result = 10 / 0;
} 
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} 
finally {
    System.out.println("This always runs.");
}
```

## Multiple catch Blocks

```java
try {
    // risky code
} 
catch (ArithmeticException e) {
    // specific first
} 
catch (NullPointerException e) {
    // specific second
} 
catch (Exception e) {
    // general last
}
```

**Multi-catch (Java 7+):**

```java
catch (ArithmeticException | NullPointerException e) {
    System.out.println("Caught: " + e.getMessage());
}
```

> Rule: More specific exceptions must come **before** more general ones.

## throw — Manually Throw an Exception

```java
void checkAge(int age) {
    if (age < 18) {
        throw new ArithmeticException("Not eligible: age < 18");
    }
    System.out.println("Eligible!");
}
```

## throws — Declare That a Method May Throw

```java
void readFile(String path) throws IOException {
    FileReader fr = new FileReader(path);
}

// Caller handles it
try {
    readFile("data.txt");
} 
catch (IOException e) {
    System.out.println("File error: " + e.getMessage());
}
```

## throw vs throws

| `throw` | `throws` |
|---|---|
| Inside method body | In method signature |
| Throws an exception object | Declares possible exceptions |
| One exception at a time | Can list multiple exceptions |

## Custom Exception

```java
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

void validate(int age) throws InvalidAgeException {
    if (age < 0) {
        throw new InvalidAgeException("Age cannot be negative!");
    }
}

try {
    validate(-5);
} 
catch (InvalidAgeException e) {
    System.out.println(e.getMessage());
}
```

## try-with-Resources

Automatically closes resources — no need for `finally`.

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
    System.out.println(line);
} 
catch (IOException e) {
    e.printStackTrace();
}
// br is automatically closed here
```

- The resource must implement `AutoCloseable`
- Multiple resources: `try (Res1 r1 = ...; Res2 r2 = ...)`

## User Input

### Scanner (Simpler)

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
String name = sc.nextLine();
int age = sc.nextInt();
sc.close();
```

### BufferedReader (Faster)

```java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String name = br.readLine();
int age = Integer.parseInt(br.readLine());
```