# OOPS in Java

## Why OOP?

Plain variables to represent an entity (e.g., Student) are messy — too many independent variables, no grouping, no access control.

**OOP solves this** by bundling data + behaviour into objects.

Main pillars: **Encapsulation · Inheritance · Polymorphism · Abstraction**

---

## Class & Object

```java
class Student {
    // Data (Characteristics)
    String name;
    int age;
    int rollNo;
    String college;

    // Behaviour
    void markAttendance() {}
}

Student s1 = new Student(); // s1 is a reference on Stack; object lives on Heap
s1.name = "Test";
```

- **Class** → blueprint / user-defined type
- **Object** → instance of a class
- Primitives → compile-time (Stack); Objects → runtime, dynamic (Heap)

---

## Constructors

A constructor initializes an object when it's created. 

- same name as class
- no return type 
- auto-called on `new`.

```java
// No-arg (Java provides one by default if none defined)
Student() { 
    this.name = "Default"; 
}

// Parameterized
Student(String name, int age) {
    this.name = name; // 'this' disambiguates field vs parameter (refers to current object)
    this.age  = age;
}
```

**Constructor Overloading** — multiple constructors with different parameters.

**Constructor Chaining** — call one constructor from another using `this(...)` as the **first statement**.

```java
Student() { 
    this("Default", 0); 
}  // chains to parameterized constructor
```

> Once you define any constructor, Java stops providing the default no-arg one.

---

## Memory Layout

```
Object Size = Header (12 bytes) + Fields + Padding (to nearest 8 bytes)

Student { 
    String name, 
    int age, 
    int rollNo, 
    String college 
}
= 12 (header) + 16 (fields) + 4 (padding) = 32 bytes on Heap
```

- **Mark Word** (8 bytes): lock state, GC info
- **Class Pointer** (4 bytes): points to class definition
- String/Object fields store a **4-byte reference**, not the actual value

---

## Call by Value vs Reference

Java is **always call-by-value**.

- **Primitives** → a copy is passed; original unchanged.
- **Objects** → the reference (address) is copied; but it points to the same heap object, so field mutations are visible outside.

```java
void addTen(Random r) { 
    r.x += 10; 
}  // modifies original object's field
```

---

## Deep Copy vs Shallow Copy

```java
Random r2 = r1; // Shallow copy — same object, aliased reference
Random r2 = new Random(r1); // Deep copy — new object, independent values
```

Use a **copy constructor** for deep copy:

```java
Random(Random r) { 
    this.x = r.x; 
    this.y = r.y; 
}
```

---

## Naming Conventions

| Element | Convention | Example |
|---|---|---|
| Variables | camelCase | `firstName` |
| Classes | PascalCase | `Student` |
| Methods | camelCase | `markAttendance()` |
| Constants | ALL_CAPS | `MAX_SIZE` |