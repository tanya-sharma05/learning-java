# JVM, JRE & JDK

## Compilation Pipeline
```
Hello.java → javac → Hello.class (Bytecode) → JVM → Machine Code
```

## Nesting Relationship JDK ⊃ JRE ⊃ JVM

```
┌─────────────────────────────────────┐
│               JDK                   │
│  (Java Development Kit)             │
│  ┌───────────────────────────────┐  │
│  │            JRE                │  │
│  │  (Java Runtime Environment)   │  │
│  │  ┌─────────────────────────┐  │  │
│  │  │          JVM            │  │  │
│  │  │ (Java Virtual Machine)  │  │  │
│  │  └─────────────────────────┘  │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘
```

- **JVM** — executes bytecode (Interpreter + JIT + GC + Sandbox)
- **JRE** — JVM + standard class libraries (to *run* Java apps)
- **JDK** — JRE + `javac` + Debugger + JavaDocs (to *develop* Java apps)

## Compiler vs Interpreter vs JIT
| | Approach | Speed |
|--|----------|-------|
| Compiler (C++) | Whole program → machine code upfront | Fast |
| Interpreter (Python) | Line-by-line at runtime | Slow |
| Java (both) | Compile to bytecode, then JIT for hot paths | Fast + Portable |

**JIT (Just-In-Time):** Detects "hot" code (frequent loops) and compiles it to native machine code once, caching the result. This is why Java performance rivals C++ in many benchmarks.

## JVM's 3 Responsibilities
1. **Execute bytecode** — via Interpreter + JIT
2. **Security** — Sandbox model
3. **Garbage Collection** — automatic memory management

## Java Editions
| Edition | Use Case |
|---------|----------|
| **JSE** (Standard) | Desktop, core apps — also called *Core Java* |
| **JEE** (Enterprise) | Web backends, microservices — now *Jakarta EE* |
| **JME** (Micro) | Embedded, IoT, mobile |