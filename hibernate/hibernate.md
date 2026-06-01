# Hibernate & ORM 

## What is ORM?

**ORM (Object-Relational Mapping)** is a technique that maps Java objects to relational database tables, eliminating the need to write raw SQL manually.

| Without ORM (JDBC) | With ORM (Hibernate) |
|---|---|
| Write SQL queries manually | Work with Java objects only |
| Handle `ResultSet`, `PreparedStatement` | Hibernate generates SQL for you |
| Tedious boilerplate code | Clean, concise code |
| DB-specific SQL | Database-agnostic |

---

## Why Hibernate?

- **Reduces boilerplate** — no manual SQL for basic CRUD
- **Database independence** — switch from PostgreSQL to MySQL with a config change
- **Automatic DDL** — can auto-create/update tables via `hbm2ddl.auto`
- **Caching support** — first-level (Session) and second-level caches
- **HQL (Hibernate Query Language)** — object-oriented query language
- **Lazy/Eager loading** — fine-grained control over data fetching

---

## Project Setup (Maven)

Add these dependencies to your `pom.xml`:

```xml
<properties>
    <maven.compiler.source>23</maven.compiler.source>
    <maven.compiler.target>23</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<dependencies>

    <!-- Hibernate Core (ORM engine) -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>7.0.0.Beta3</version>
    </dependency>

    <!-- PostgreSQL JDBC Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.4</version>
    </dependency>

</dependencies>
```

> For MySQL, replace the PostgreSQL dependency with `mysql-connector-j`.

---

## Hibernate Configuration (`hibernate.cfg.xml`)

Create this file at `src/main/resources/hibernate.cfg.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <!-- JDBC Connection Settings -->
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/telusko</property>
        <property name="hibernate.connection.username">postgres</property>
        <property name="hibernate.connection.password">9000</property>

        <!-- Auto DDL: create | update | validate | create-drop | none -->
        <property name="hibernate.hbm2ddl.auto">create</property>

        <!-- Show generated SQL in console -->
        <property name="hibernate.show_sql">true</property>

    </session-factory>
</hibernate-configuration>
```

### `hbm2ddl.auto` values explained

| Value | Behaviour |
|---|---|
| `create` | Drops and recreates tables on every run |
| `update` | Updates schema without dropping data |
| `validate` | Validates schema, throws error on mismatch |
| `create-drop` | Creates tables on start, drops on close |
| `none` | Does nothing to the schema |

> Use `create` while learning, `update` in development, `validate` or `none` in production.

---

## Entity Class

An **Entity** is a plain Java class annotated to tell Hibernate which table it maps to.

```java
package com.telusko;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Marks this class as a Hibernate entity
@Table(name = "alien_data") // Maps to the "alien_data" table (optional — defaults to class name)
public class Alien {

    @Id // Primary key
    @Column(name = "a_id") // Maps field to column "a_id" (optional — defaults to field name)
    private int aid;

    @Column(name = "a_name")
    private String aname;

    private String tech;

    // --- Getters & Setters ---

    public int getAid() { 
        return aid; 
    }
    public void setAid(int aid) { 
        this.aid = aid; 
    }

    public String getAname() { 
        return aname; 
    }
    public void setAname(String aname) { 
        this.aname = aname; 
    }

    public String getTech() { 
        return tech; 
    }
    public void setTech(String tech)  { 
        this.tech = tech; 
    }

    @Override
    public String toString() {
        return "Alien{aid=" + aid + ", aname='" + aname + "', tech='" + tech + "'}";
    }
}
```

---

## Core API: Session & SessionFactory

```
Configuration
     │
     ▼
SessionFactory   ← created once (expensive), thread-safe
     │
     ▼
Session          ← created per operation (lightweight), not thread-safe
     │
     ▼
Transaction      ← wraps write operations (persist, merge, remove)
```

### Boilerplate Setup (used in all operations)

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

SessionFactory factory = new Configuration()
        .addAnnotatedClass(com.telusko.Alien.class) // register entity
        .configure() // loads hibernate.cfg.xml
        .buildSessionFactory();

Session session = factory.openSession();
```

> From Hibernate 6+, `Configuration` is from `org.hibernate.cfg.Configuration` (not the old XML-only style).

---

## CRUD Operations

### CREATE — `session.persist()`

Saves a new object to the database.

```java
Alien a1 = new Alien();
a1.setAid(101);
a1.setAname("Navin");
a1.setTech("Java");

Transaction tx = session.beginTransaction();
session.persist(a1); // INSERT INTO alien_data ...
tx.commit();

session.close();
factory.close();
```

### READ — `session.get()` vs `session.load()`

```java
// get() — returns null if not found (eager: hits DB immediately)
Alien a1 = session.get(Alien.class, 103);
System.out.println(a1);

// load() — returns a proxy (lazy: hits DB only when data is accessed)
Alien a2 = session.load(Alien.class, 103);
System.out.println(a2);  // DB hit happens here
```

| Method | Behaviour on missing ID | When DB is hit |
|---|---|---|
| `get()` | Returns `null` | Immediately |
| `load()` | Throws `ObjectNotFoundException` | When object data is first accessed |

### UPDATE — `session.merge()`

Merges the state of a detached object back into the database.

```java
Alien a1 = new Alien();
a1.setAid(103); // must match existing primary key
a1.setAname("Harsh");
a1.setTech("Spring");

Transaction tx = session.beginTransaction();
session.merge(a1); // SELECT + UPDATE under the hood
tx.commit();
```

> Hibernate will first SELECT the record by ID, then issue an UPDATE.

### DELETE — `session.remove()`

```java
Transaction tx = session.beginTransaction();

Alien a1 = session.find(Alien.class, 104); // find() is JPA equivalent of get()
session.remove(a1); // DELETE FROM alien_data WHERE aid=?

tx.commit();
session.close();
factory.close();
```