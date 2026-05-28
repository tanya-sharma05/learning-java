# Java Variables & Data Types

## Variables & Identifiers
A variable is a named memory container. Java is **statically typed** — every variable needs a declared type that can't change.

**Naming:** camelCase for variables/methods (`firstName`), PascalCase for classes (`Student`).

## Primitive Data Types
| Type | Size | Range / Notes |
|------|------|---------------|
| `byte` | 8-bit | -128 to +127 |
| `short` | 16-bit | -32,768 to +32,767 |
| `int` | 32-bit | ~-2B to +2B (default integer type) |
| `long` | 64-bit | Very large integers; suffix `L` |
| `float` | 32-bit | ~7 decimal digits; suffix `f` |
| `double` | 64-bit | ~15 decimal digits (default for decimals) |
| `char` | 16-bit | Unicode 0–65,535; single quotes `'a'` |
| `boolean` | — | `true` or `false` only |

> All Java integers are **signed** — one bit reserved for sign.

## Number Literal Formats
```java
int dec = 5; // decimal (default)
int bin = 0b101; // binary → 5
int oct = 07; // octal → 7
int hex = 0XA; // hex → 10
long l  = 341_256_789L; // underscores for readability (Java 7+)
```

## Declaration vs Definition
```java
int x; // declaration — memory reserved
x = 4; // definition — value assigned
int y = 5; // both at once
```

## How Negative Numbers Are Stored (2's Complement)
1. Write positive in binary
2. Flip all bits (1's complement)
3. Add 1

`byte -42` → `+42 = 00101010` → flip → `11010101` → +1 → `11010110`

## Floating Point (IEEE 754)
`float` = 1 sign + 8 exponent + 23 mantissa bits. Values like `0.7f` **cannot be stored exactly** — use `BigDecimal` for financial calculations.

## Type Conversion
```java
// implicit (widening) — safe, no cast needed
byte b = 24;
int i = b; 

// explicit (narrowing) — may lose data (300 % 256 = 44)
int x = 300;
byte y = (byte) x; 

// truncates decimal → 16
float f = 16.25f;
int z = (int) f;    
```

**Auto-promotion rule:** `byte`/`short`/`char` are promoted to `int` in expressions.

### Java Has 68 Keywords

| Category | Keywords |
|----------|----------|
| Data Types | `int`, `byte`, `short`, `long`, `float`, `double`, `char`, `boolean` |
| Access | `public`, `private`, `protected` |
| Class/OOP | `class`, `interface`, `extends`, `implements`, `new`, `this`, `super` |
| Control Flow | `if`, `else`, `for`, `while`, `do`, `switch`, `case`, `break`, `continue`, `return` |
| Other | `static`, `void`, `final`, `try`, `catch`, `throws`, `import`, `package` |
| Reserved (unused) | `goto`, `const` ← reserved but not used in Java |

> 💡 `goto` and `const` are reserved keywords in Java but are **not actually used** — they exist to prevent programmers from using them as identifiers (since they're used in C/C++).