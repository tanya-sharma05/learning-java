# JDBC – Java Database Connectivity

JDBC is a Java API that enables Java applications to interact with relational databases. It acts as a bridge between a Java program and a database (MySQL, Oracle, PostgreSQL, etc.) using database-specific **JDBC Drivers**.

---

## How it Works

```
Java App  ──→  JDBC  ──→  JDBC Driver  ──→  Database (MySQL / Oracle / PostgreSQL)
```

- Java can store data either on the **file system** (via `java.io.*` file handling) or in a **database** via JDBC.
- Each database requires its own JDBC Driver (a `.jar` file).

---

## Steps to Develop a JDBC Application

1. Import the required packages
2. Load and Register the Driver
3. Establish the Connection
4. Create the Statement
5. Execute the Query
6. Process the Result
7. Close the Resources

---

## Step-by-Step Implementation

### 1. Import Package

```java
import java.sql.*;
```

Also add the **MySQL JDBC Driver JAR** to your project (see Maven section below for the easier alternative).

---

### 2. Load and Register the Driver

```java
// Modern MySQL driver (MySQL Connector/J 8+)
Class.forName("com.mysql.cj.jdbc.Driver");

// Alternatively (older style, explicit registration)
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
```

> In JDBC 4.0+, `Class.forName()` is optional — the driver registers automatically if the JAR is on the classpath.

---

### 3. Establish the Connection

```java
String url = "jdbc:mysql://localhost:3306/jdbclearning";
String user = "root";
String password = "root123";

Connection connect = DriverManager.getConnection(url, user, password);
```

---

### 4. Create the Statement

```java
Statement statement = connect.createStatement();
```

---

### 5. Execute the Query

#### INSERT

```java
String sql = "INSERT INTO studentinfo(id, sname, sage, scity) VALUES(1, 'Rohan', 17, 'Bengaluru')";
int rowsAffected = statement.executeUpdate(sql);

if(rowsAffected == 0) {
    System.out.println("Unable to insert the data");
} 
else {
    System.out.println("Data Inserted Successfully!");
}
```

#### UPDATE

```java
String sql = "UPDATE studentinfo SET sage=24 WHERE id=2";
int rowsAffected = statement.executeUpdate(sql);

if(rowsAffected == 0)
    System.out.println("Updation failed");
else
    System.out.println("Update successful!");
```

#### DELETE

```java
String sql = "DELETE FROM studentinfo WHERE id=2";
int rowsAffected = statement.executeUpdate(sql);

if(rowsAffected == 0)
    System.out.println("Failed to delete the record");
else
    System.out.println("Record deleted Successfully!");
```

#### SELECT

```java
String sql = "SELECT * FROM studentinfo";
ResultSet rs = statement.executeQuery(sql);

while (rs.next()) {
    System.out.println(
        rs.getInt("id")     + " " +
        rs.getString("sname") + " " +
        rs.getInt("sage")   + " " +
        rs.getString("scity")
    );
}
```

---

### 6. Using `execute()` — Universal Method

`execute()` works for any SQL. It returns `true` for SELECT queries and `false` for INSERT/UPDATE/DELETE.

```java
boolean status = statement.execute(sql);

if(status) {
    // SELECT — retrieve results
    ResultSet rs = statement.getResultSet();
    while (rs.next()) {
        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
    }
} 
else {
    // INSERT / UPDATE / DELETE
    int rows = statement.getUpdateCount();
    if(rows == 0)
        System.out.println("Operation failed!");
    else
        System.out.println("Operation successful!");
}
```

---

### 7. Close the Resources

Always close resources in a `finally` block to avoid connection leaks:

```java
finally {
    try {
        if(statement != null) {
            statement.close();
        }
        if(connect  != null) {
            connect.close();
        }
    } 
    catch(SQLException e) {
        e.printStackTrace();
    }
}
```

---

## Complete Boilerplate (with Exception Handling)

```java
package com.learning.jdbclearning;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        Connection connect = null;
        Statement  statement = null;

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish Connection
            String url = "jdbc:mysql://localhost:3306/jdbclearning";
            String user = "root";
            String password = "root123";
            connect = DriverManager.getConnection(url, user, password);

            // 3. Create Statement
            statement = connect.createStatement();

            // 4. Execute Query
            String sql = "SELECT * FROM studentinfo";
            ResultSet rs = statement.executeQuery(sql);

            // 5. Process Result
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id")       + " " +
                    rs.getString("sname") + " " +
                    rs.getInt("sage")     + " " +
                    rs.getString("scity")
                );
            }
        } 
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        } 
        catch(Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                if(connect != null) {
                    connect.close();
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

---

## Statement vs PreparedStatement

| Feature | `Statement` | `PreparedStatement` |
|---|---|---|
| SQL passed at | execution time | creation (run) time |
| Parameters | hardcoded in SQL string | placeholders `?` |
| Compiled by DB | every execution | once (cached) |
| SQL Injection safe | ❌ No | ✅ Yes |
| Best for | simple/static queries | dynamic/user-input queries |

### PreparedStatement Example (INSERT with user input)

```java
String query = "INSERT INTO studentinfo(id, sname, sage, scity) VALUES(?,?,?,?)";
PreparedStatement pstmt = connect.prepareStatement(query);

Scanner scan = new Scanner(System.in);

System.out.println("Enter your id:");    
int id = scan.nextInt();

System.out.println("Enter your name:");  
String name = scan.next();

System.out.println("Enter your age:");   
int age = scan.nextInt();

System.out.println("Enter your city:");  
String city = scan.next();

pstmt.setInt(1, id);
pstmt.setString(2, name);
pstmt.setInt(3, age);
pstmt.setString(4, city);

int rowAffected = pstmt.executeUpdate();

if(rowAffected == 0)
    System.out.println("Unable to insert the data");
else
    System.out.println("Data Inserted Successfully!");
```

---

## Database Setup (MySQL)

```sql
-- Create the database
CREATE DATABASE jdbclearning;

-- Use it
USE jdbclearning;

-- Create the table
CREATE TABLE studentinfo (
    id INT NOT NULL,
    sname VARCHAR(40) NOT NULL,
    sage INT NOT NULL,
    scity VARCHAR(40) NOT NULL,
    PRIMARY KEY(id)
);

-- Verify
SELECT * FROM jdbclearning.studentinfo;
```

---

## Using JDBC with Maven (Recommended)

When working **without Maven**, you must manually:
1. Download the MySQL Connector/J `.jar` from [dev.mysql.com](https://dev.mysql.com/downloads/connector/j/)
2. Add it to your project's build path (e.g., as a library in Eclipse)

With **Maven**, just add the dependency to `pom.xml` and Maven downloads and manages the JAR automatically — no manual download needed.

### Maven Dependency

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>
```

> Check [Maven Central](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j) for the latest version.

### Minimal Maven `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>jdbc</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.3.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

All JDBC code remains exactly the same — Maven simply handles the JAR so you can skip the manual download and classpath setup entirely.