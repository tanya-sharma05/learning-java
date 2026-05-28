# Operators in Java

## Arithmetic
`+`, `-`, `*`, `/`, `%`, `++`, `--`, `+=`, `-=`, `*=`, `/=`

```java
int i = 5;
int a = i++; // a=5, i=6  (post: use then increment)
int b = ++i; // b=7, i=7  (pre: increment then use)
```

## Relational (always return `boolean`)
`==`, `!=`, `<`, `>`, `<=`, `>=`

> `==` checks equality; `=` is assignment.

## Bitwise (operate on binary bits)
| Op | Meaning |
|----|---------|
| `&` | AND |
| `\|` | OR |
| `^` | XOR |
| `~` | NOT (complement) |
| `<<` | Left shift (×2 per shift) |
| `>>` | Right shift signed (÷2, preserves sign) |
| `>>>` | Right shift unsigned (fills with 0s) |

> `byte`/`short`/`char` are promoted to `int` before shifting. No `<<<` operator in Java.

## Logical (short-circuit)
| Op | Meaning |
|----|---------|
| `&&` | AND — stops at first `false` |
| `\|\|` | OR — stops at first `true` |
| `!` | NOT |

> `&` and `|` (bitwise) do NOT short-circuit.

## Operator Precedence (high → low)
`++`/`--` (postfix) → prefix/`~`/`!`/cast → `*`/`/`/`%` → `+`/`-` → shifts → relational → `==`/`!=` → `&` → `^` → `|` → `&&` → `||` → `?:` → `=`/compound

> Parentheses `( )` have the highest precedence.