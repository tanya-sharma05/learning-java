# Strings in Java

A `String` is a sequence of characters (non-primitive / object type).

- Alphabets: `'A'`, `'a'`, `'z'`, etc.
- Digits: `1`, `2`, `3`, ...
- Special symbols: `'!'`, `','`, `'-'`, etc.
- Unicode characters: `'\u0000'` to `'\uFFFF'` (0 → 255 for basic ASCII)

## Basics
```java
String s = "Hello"; // literal (preferred) → goes to String Pool
String s = new String("Hi"); // always creates new Heap object
```
`String` is **immutable** — once created, never modified.

## The Golden Rule of String Pool

> **Only compile-time constants go to the String Pool automatically.** 
> **Runtime-created strings go to the Heap.**

```java
String s1 = "Ja" + "va"; // compile-time → pool  → s1 == s2 is true
String s1 = "ja";
String s2 = s1 + "va"; // runtime → Heap → s2 == "java" is false
```
> Use `.equals()` to compare content, not `==`.

## Internal Structure (Java 9+)

```java
public final class String {
    private final byte[] value; // character data (1 or 2 bytes per char)
    private final byte coder; // encoding indicator: 0 = Latin1, 1 = UTF-16
    private int hash; // cached hash code
}
```

~50% memory savings vs pre-Java 9 `char[]` for ASCII strings.

## String Methods

### Length / Emptiness
| Method | Description |
|--------|-------------|
| `length()` | Returns number of characters |
| `isEmpty()` | Returns `true` if length is 0 |
| `isBlank()` | Returns `true` if empty or only whitespace |

### Character Access
| Method | Description |
|--------|-------------|
| `charAt(int index)` | Returns char at given index |
| `toCharArray()` | Converts String to `char[]` |

### Comparison
| Method | Description |
|--------|-------------|
| `equals(Object o)` | Compares content (case-sensitive) |
| `equalsIgnoreCase(String s)` | Compares content (case-insensitive) |
| `compareTo(String s)` | Lexicographic comparison |

### Searching
| Method | Description |
|--------|-------------|
| `contains(CharSequence s)` | Checks if substring exists |
| `indexOf(String s)` | First occurrence index |
| `lastIndexOf(String s)` | Last occurrence index |
| `startsWith(String prefix)` | Checks prefix |
| `endsWith(String suffix)` | Checks suffix |

### Extraction / Transformation
| Method | Description |
|--------|-------------|
| `substring(int start)` | Extracts from index to end |
| `substring(int start, int end)` | Extracts range |
| `toUpperCase()` | Converts to uppercase |
| `toLowerCase()` | Converts to lowercase |
| `trim()` | Removes leading/trailing whitespace |
| `strip()` | Unicode-aware trim (Java 11+) |
| `repeat(int n)` | Repeats string n times |
| `replace(char old, char new)` | Replaces first occurrence |
| `replaceAll(String regex, String rep)` | Replaces all using regex |
| `split(String regex)` | Splits into array |
| `join(CharSequence delim, ...)` | Joins strings with delimiter |

### Conversion
| Method | Description |
|--------|-------------|
| `valueOf(...)` | Converts primitives/objects to String |
| `getBytes()` | Returns byte array of the String |

### Advanced
| Method | Description |
|--------|-------------|
| `intern()` | Moves string to pool (or returns existing pool reference) |
| `format(String fmt, ...)` | Creates formatted string |


## StringBuilder vs StringBuffer
Prefer `StringBuilder` in loops — `String` concatenation creates a new object each iteration.

```java
StringBuilder sb = new StringBuilder("java");
sb.append("!");   
sb.insert(2, "x");   
sb.delete(1,3);
sb.reverse();     
sb.replace(0,2,"GO");
```

| | `String` | `StringBuilder` | `StringBuffer` |
|--|----------|-----------------|----------------|
| Mutable | ❌ | ✅ | ✅ |
| Thread-safe | ✅ | ❌ | ✅ |
| Performance | Medium | Fast | Slower |

**Capacity growth:** `(old × 2) + 2`. Default initial capacity = 16.

> `StringBuilder.equals()` compares references — use `sb1.toString().equals(sb2.toString())`.