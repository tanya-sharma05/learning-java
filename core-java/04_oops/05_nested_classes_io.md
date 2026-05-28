# Nested Classes & Java I/O

## Nested Classes

A class defined inside another class. 

Reasons to use: logical grouping, and inner classes can access private members of the outer class.

### Four Types

**1. Static Nested Class**
Does not need an outer instance. Can only access static members of the outer class directly.

```java
class Outer {
    static class Inner { }
}
Outer.Inner obj = new Outer.Inner();
```

**2. Inner Class (Non-static)**
Tied to an outer instance. Can access all outer class members including private ones.

```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();

// Disambiguate same-name fields:
System.out.println(Outer.this.x);  // outer's x
System.out.println(this.x);        // inner's x
```

Cannot declare static members (before Java 16).

**3. Local Class**
Defined inside a method/block. Scoped to that block. Can capture **effectively final** variables from the enclosing scope.

```java
void fun() {
    int y = 10;  // effectively final
    class Local {
        void show() { 
            System.out.println(y); 
        }
    }
    new Local().show();
}
```

**4. Anonymous Class**
A one-off class defined and instantiated in a single expression, typically to override a method inline.

```java
Person p = new Person() {
    @Override
    void introduce() { 
        System.out.println("Hi, I am a Guest"); 
    }
};

p.introduce();
```

---

## Java I/O

### Console Output

`System.out` is a `PrintStream` (subclass of `OutputStream`).

```java
System.out.println("text");  // prints + newline
System.out.print("text");    // prints, no newline
System.out.printf("%s %d", name, age);  // formatted
```

### Console Input — Three Ways

**1. Raw `System.in`** (byte-by-byte, tedious):
```java
int data = System.in.read();  // reads one byte; returns ASCII value
```

**2. BufferedReader** (faster, thread-safe):
```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
int n = Integer.parseInt(line);  // must parse manually
```

Flow: `System.in` (bytes) → `InputStreamReader` (bytes→chars) → `BufferedReader` (buffered lines)

**3. Scanner** (easiest, Java 1.5+):
```java
Scanner sc = new Scanner(System.in);
String name = sc.nextLine();
int age = sc.nextInt();
```

Scanner tokenizes input by whitespace. Can also read from `File` or `String`.

### BufferedReader vs Scanner

| Feature | BufferedReader | Scanner |
|---|---|---|
| Speed | Faster | Slower (parsing overhead) |
| Type parsing | Manual (`parseInt`) | Built-in (`nextInt()`) |
| Thread-safe | ✅ | ❌ |
| Package | `java.io` | `java.util` |

Use **BufferedReader** for competitive programming / large input. 

Use **Scanner** for convenience.