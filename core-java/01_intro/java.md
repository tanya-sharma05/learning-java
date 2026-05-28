# Introduction to Java

## Why Java?
C/C++ compiled to **platform-specific machine code** — same source required recompilation for every OS/processor combo. Java solved this with a two-step model.

## Java's Solution: Bytecode + JVM
```
Hello.java → Compiler → Hello.class (Bytecode) → JVM(platform dependent) → Machine Code
```
- **Bytecode** — platform-independent intermediate code (`.class` file)
- **JVM** — translates bytecode to native machine code on each platform
- **WORA** — Write Once, Run Anywhere

## Key Features
| Feature | How |
|---------|-----|
| Portable | JVM abstracts the platform |
| Simple | No pointers, no manual memory management |
| Secure | Sandbox model — no direct hardware access; Garbage Collector handles memory |

## Java vs C/C++
| | C/C++ | Java |
|--|-------|------|
| Output | Platform-specific binary | Platform-independent bytecode |
| Memory | Manual (`malloc`/`free`) | Automatic (Garbage Collector) |
| Pointers | ✅ | ❌ |
| Speed | Faster | Slight JVM overhead |

## Related Languages
- **C#** — Microsoft's Java alternative; compiles to CLR bytecode
- **Kotlin / Scala** — modern JVM languages, fully interoperable with Java