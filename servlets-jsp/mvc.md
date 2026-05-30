# Introduction to MVC — Servlet, JSP & JDBC

## What is MVC?

MVC stands for **Model-View-Controller** — a software design pattern that separates an application into three interconnected components, making code more organized and maintainable.

| Component | Role |
|---|---|
| **Model** | Handles data logic — fetch, store, update, delete data (via JDBC) |
| **View** | What the user sees — displays data and accepts input (JSP, HTML) |
| **Controller** | Processes user input, updates the Model, and manages data flow between Model and View (Servlet) |

---

## MVC Architecture Flow


1. The **Client** sends a request to the **Controller**
2. The **Controller** calls the **Model** to interact with the database
3. The **Model** performs DB operations and returns the result
4. The **Controller** forwards the response to the appropriate **View**
5. The **View** renders the result back to the **Client**

---

## Project: Registration App (MVC — Servlet + JSP)

### 1. View — `index.html` (Registration Form)

```html
<form action="Register" method="post">
  <table>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="uname"></td>
    </tr>
    <tr>
      <td>User Email id</td>
      <td><input type="text" name="email"></td>
    </tr>
    <tr>
      <td>User Password</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <td>User City</td>
      <td><input type="text" name="ucity"></td>
    </tr>
    <tr>
      <td><input type="submit" value="SignUp"></td>
    </tr>
  </table>
</form>
```

---

### 2. View — `success.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Success</title>
  </head>
  <body>
    <h1><marquee>Registration Successfull!</marquee></h1>
    <% String name=(String)session.getAttribute("name"); %>
    <h2>Hey <%= name %>, you have registered to this web app</h2>
  </body>
</html>
```

---

### 3. View — `failure.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Failure</title>
  </head>
  <body>
    <h1><marquee>Registration failed</marquee></h1>
    <% String name=(String)session.getAttribute("name"); %>
    <h2>Hey <%= name %>, you failed to registered to this web app</h2>
  </body>
</html>
```

---

### 4. Model — `Model.java`

Holds user data fields, getters/setters, and a `register()` method to insert the user into the database.

```java
public class Model {
    private String uname;
    private String upassword;
    private String email;
    private String ucity;

    // Getters and Setters
    public String getUname() { 
        return uname; 
    }
    public void setUname(String uname) { 
        this.uname = uname; 
    }

    public String getUpassword() { 
        return upassword; 
    }
    public void setUpassword(String upassword) { 
        this.upassword = upassword; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getUcity() { 
        return ucity; 
    }
    public void setUcity(String ucity) { 
        this.ucity = ucity; 
    }

    // Register method — inserts user into DB
    public int register() {
        int row = 0;
        try {
            Connection connect = JdbcUtil.getDBConnection();
            String sql = "INSERT INTO personalinfo (uname, email, upassword, ucity) VALUES(?,?,?,?)";
            PreparedStatement pstmnt = connect.prepareStatement(sql);
            pstmnt.setString(1, uname);
            pstmnt.setString(2, email);
            pstmnt.setString(3, upassword);
            pstmnt.setString(4, ucity);
            row = pstmnt.executeUpdate();
        } 
        catch(SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
```

---

### 5. DB Utility — `JdbcUtil.java`

A helper class to load the MySQL driver and return a database connection.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Driver is registered");
    }

    public static Connection getDBConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/telusko_db";
        String userName = "root";
        String password = "root123";
        return DriverManager.getConnection(url, userName, password);
    }
}
```

---

### 6. Controller — `Register.java` (Servlet)

Reads form data, populates the Model, calls `register()`, stores the username in session, and redirects to success or failure page.

```java
// Inside doPost() method
String uname = request.getParameter("uname");
String emailId = request.getParameter("email");
String upassword = request.getParameter("password");
String ucity = request.getParameter("ucity");

Model model = new Model();
model.setUname(uname);
model.setEmail(emailId);
model.setUpassword(upassword);
model.setUcity(ucity);

int row = model.register();

HttpSession session = request.getSession();
session.setAttribute("name", uname);

if (row == 0) {
 response.sendRedirect("/RegistrationAppMVC/failure.jsp");
} 
else {        response.sendRedirect("/RegistrationAppMVC/success.jsp");
}
```