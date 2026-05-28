# Threads

## What is a Thread?

A **thread** is a lightweight unit of execution. Java supports **multithreading** — running multiple threads simultaneously.

## Creating a Thread

### Way 1: Extend Thread class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running: " + Thread.currentThread().getName());
    }
}

MyThread t = new MyThread();
t.start(); // don't call run() directly — use start()
```

### Way 2: Implement Runnable (Preferred)

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Runnable running");
    }
}

new Thread(new MyTask()).start();

// With lambda
new Thread(() -> System.out.println("Lambda thread")).start();
```

**Why prefer Runnable?**
- Java has single inheritance — using Runnable lets your class extend another class too
- Better separation of task logic from threading logic
- Works cleanly with lambda expressions

## Multiple Threads

```java
class Task extends Thread {
    String name;
    Task(String name) { 
        this.name = name; 
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " - " + i);
        }
    }
}

Task t1 = new Task("Thread-A");
Task t2 = new Task("Thread-B");
t1.start();
t2.start();
// Output order is NOT guaranteed!
```

## Thread Priority & Sleep

```java
// Priority: 1 (min) to 10 (max), default is 5
Thread t = new Thread(() -> System.out.println("Hi"));
t.setPriority(Thread.MAX_PRIORITY); // 10
t.start();

// Sleep — pause current thread (value in milliseconds)
try {
    Thread.sleep(1000); // pause 1 second
} 
catch (InterruptedException e) {
    e.printStackTrace();
}
```

> Priority is a **hint** to the OS scheduler — not a guarantee.

## Thread States

```
NEW → RUNNABLE → (BLOCKED / WAITING / TIMED_WAITING) → TERMINATED
```

| State | Description |
|---|---|
| `NEW` | Created, `start()` not called yet |
| `RUNNABLE` | Running or ready to run |
| `BLOCKED` | Waiting for a lock |
| `WAITING` | Waiting indefinitely (`wait()`) |
| `TIMED_WAITING` | Waiting for set time (`sleep()`, `join(timeout)`) |
| `TERMINATED` | Finished execution |

```java
Thread t = new Thread(() -> {});
System.out.println(t.getState()); // NEW

t.start();
System.out.println(t.getState()); // RUNNABLE or TERMINATED
```

## Race Condition

Occurs when multiple threads access and modify **shared data** simultaneously — leads to unpredictable results.

```java
// NOT thread-safe
class Counter {
    int count = 0;
    void increment() { 
        count++; 
    }
}

// Thread-safe with synchronized
class Counter {
    int count = 0;
    synchronized void increment() { 
        count++; 
    }
}

// Or synchronized block
synchronized (this) {
    count++;
}
```

Also consider `AtomicInteger` from `java.util.concurrent` for simple counters.