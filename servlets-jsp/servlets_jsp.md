# Servlets & JSP 

## 1. Client–Server Architecture

A **web application** is a service delivered over the Internet (e.g. social media apps, e-commerce platforms). It relies on:

| Term | Definition |
|---|---|
| **Client** | A user or system that requests a resource/service from a server |
| **Internet** | A global communication system linking thousands of individual networks, allowing information exchange between computers |
| **Server** | Responsible for processing one or more requests at a time |
| **Web Server** | A software program or physical machine that offers World Wide Web access |
| **Web Browser** | The client-side tool (e.g. Chrome) used to send requests and receive responses |

Communication between the browser and web server happens via **HTTP (Hypertext Transfer Protocol)**.  
Each server is identified by an **IP address** and a **Port number**.

```
WebBrowser  ──── Request ────►  WebServer (IP:Port)
            ◄─── Response ───
```

---

## 2. Static vs Dynamic Response

### Static Response
- The output **does not change** for any user.
- The web server simply fetches a pre-written file (`.html`, `.css`) and returns it.


### Dynamic Response
- The output **changes based on the user's request**.
- Requires a **helper application (Java container)** such as **Apache Tomcat**.
- Technologies that generate dynamic responses:
  1. Servlet
  2. JSP (JavaServer Pages)
  3. ASP
  4. PHP
  5. CGI
  6. Perl
  7. Cold Fusion

```
Browser → WebServer (Apache Tomcat) → Servlet/JSP → Database → Response
```

---

## 3. Servlets

> **Servlets are used to generate dynamic responses to the client.**

A Servlet runs inside a **Servlet Container** (e.g. Apache Tomcat). The container manages the servlet's lifecycle.

### 3.1 Servlet Lifecycle

```
Client Request
     │
     ▼
Apache Tomcat
     │
     ├─► Load & Instantiate (class loaded, object created)
     ├─► init() (called once on first request)
     ├─► service() (called on every request — routes to doGet/doPost)
     └─► destroy() (called when server shuts down)
```

### 3.2 Lifecycle Code Example

```java
@WebServlet("/ServletLife")
public class ServletLife extends HttpServlet {

    // 1. Class loaded
    static {
        System.out.println("Servlet is loaded....");
    }

    // 2. Object created
    public ServletLife() {
        System.out.println("Servlet object is created");
    }

    // 3. Initialized
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Servlet initialized!");
    }

    // 4. Handles every HTTP request
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Service method to handle http request and response back");
    }

    // 5. Cleanup
    public void destroy() {
        System.out.println("Servlet destroyed");
    }
}
```

### 3.3 doGet vs doPost

| Method | When Used | Data Visibility |
|---|---|---|
| `doGet` | Form with `method="get"` | Parameters visible in URL |
| `doPost` | Form with `method="post"` | Parameters hidden in request body |

### 3.4 Basic Servlet — Static Response (First App)

**index.html**
```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>First App</title>
  </head>
  <body bgcolor='cyan'>
    <h1><marquee>Welcome to our First App with Static Response</marquee></h1>
  </body>
</html>
```

### 3.5 Servlet — Dynamic Response (Second App)

**index.html** — form that POSTs to a servlet
```html
<form method="post" action="./firstServlet">
  <table>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="uname"></td>
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

**FirstServletApp.java** — reads form data and writes dynamic HTML response
```java
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firstServlet")
public class FirstServletApp extends HttpServlet {

    public FirstServletApp() {
        System.out.println("Servlet obj is created internally by container");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name  = request.getParameter("uname");
        String ucity = request.getParameter("ucity");

        PrintWriter writer = response.getWriter();

        writer.println("<html><head><title>Second App</title></head>");
        writer.println("<body bgcolor='cyan'><h1><marquee>Welcome to our dynamic app</marquee></h1>");
        writer.println("<table>");
        writer.println("<tr><th>NAME</th><th>CITY</th></tr>");
        writer.println("<tr><td>" + name + "</td><td>" + ucity + "</td></tr>");
        writer.println("</table></body>");
        writer.println("</html>");

        writer.close();
    }
}
```

---

## 4. Request Dispatching

Used to **forward** or **include** a request from one servlet to another without the client knowing.

```
Client ──► FirstServlet ──forward──► SecondServlet ──► Response to Client

Client ──► FirstServlet ──include──► SecondServlet
                 │                         │
                 └────── Response ◄────────┘  (both responses combined)
```

### 4.1 Forward

```java
// In FirstServlet
RequestDispatcher reqDispatch = request.getRequestDispatcher("/SecondServlet");
reqDispatch.forward(request, response); // Control moves to SecondServlet
```

### 4.2 Include

```java
// In FirstServlet
RequestDispatcher reqDispatch = request.getRequestDispatcher("/SecondServlet");
reqDispatch.include(request, response); // SecondServlet output included, then FirstServlet continues

PrintWriter writer = response.getWriter();
writer.println("<h1>Response from Servlet One</h1>");
writer.close();
```

### 4.3 SecondServlet

```java
@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Response from Second Servlet</h1>");
        writer.close();
    }
}
```

---

## 5. HttpSession (Session Management)

HTTP is stateless — use `HttpSession` to maintain user data across requests.

```java
// In FirstServlet — store data in session
HttpSession session = request.getSession();
session.setAttribute("name", name);

// In SecondServlet — retrieve data from session
HttpSession session = request.getSession(false); // false = don't create new
String name = (String) session.getAttribute("name");
```

---

## 6. JSP (JavaServer Pages)

> JSP lets you embed Java directly inside HTML — the server converts it into a Servlet automatically.

### 6.1 JSP Tags

| Tag | Purpose | Example |
|---|---|---|
| `<%@ ... %>` | Directive (imports, settings) | `<%@ page import="java.util.Date" %>` |
| `<%! ... %>` | Declaration (fields, methods) | `<%! int age = 18; %>` |
| `<% ... %>` | Scriptlet (Java logic) | `<% String name = ...; %>` |
| `<%= ... %>` | Expression (print value) | `<%= date %>` |

### 6.2 JSP Example

**JspApp.jsp**
```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP WEB APP</title>
</head>
<body>
  <h1>JSP Web App to generate Dynamic Response</h1>

  <%!
    int age = 18; // Declaration tag — class-level variable
  %>

  <%
    // Scriptlet — request-scoped logic
    String name  = request.getParameter("uname");
    String ucity = request.getParameter("ucity");
    Date date    = new Date();

    out.println("Hello " + name);
    out.println(" I know you're from " + ucity);
  %>

  <h1><%= date %></h1>   <!-- Expression tag — prints date -->

</body>
</html>
```

**index.html** — form pointing directly to a JSP file
```html
<form method="post" action="JspApp.jsp">
  <table>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="uname"></td>
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

## 7. Database Integration (JDBC in Servlet)

```java
// Load driver
Class.forName("com.mysql.cj.jdbc.Driver");

// Connect
String url = "jdbc:mysql://localhost:3306/telusko_db";
String user = "root";
String password = "root123";
Connection connect = DriverManager.getConnection(url, user, password);

// Prepared statement — insert user data
PreparedStatement pstmt = connect.prepareStatement(
    "INSERT INTO personalinfo (uname, email, upassword, ucity) VALUES (?,?,?,?)"
);

pstmt.setString(1, uname);
pstmt.setString(2, emailId);
pstmt.setString(3, upassword);
pstmt.setString(4, ucity);

int rowAffected = pstmt.executeUpdate();

PrintWriter writer = response.getWriter();
if(rowAffected != 0) {
    writer.println("<h1>Registration Success!</h1>");
} 
else {
    writer.println("<h1>Registration fail!</h1>");
}

pstmt.close();
connect.close();
```

---

## 8. Send Redirect vs Request Dispatcher

| Feature | `sendRedirect` | `forward` (RequestDispatcher) |
|---|---|---|
| Who redirects | Client (browser) | Server |
| URL changes | Yes | No |
| Request object shared | No (new request) | Yes |
| Usage | External URLs, after DB ops | Internal server-side routing |

```java
// Redirect to a JSP after DB operation
response.sendRedirect("/FourthServletApp/success.jsp");
```

---

**Key annotations:**
```java
@WebServlet("/urlPattern")   // Maps servlet to a URL
```