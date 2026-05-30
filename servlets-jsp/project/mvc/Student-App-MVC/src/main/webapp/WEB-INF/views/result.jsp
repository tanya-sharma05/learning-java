<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registration Result</title>
</head>
<body>
  <div class="card">

    <c:choose>

      <c:when test="${success == true}">
        <h2 class="success">Registration Successful!</h2>
        <table>
          <tr><th>Field</th><th>Value</th></tr>
          <tr><td>Name</td>  <td>${student.name}</td></tr>
          <tr><td>Email</td> <td>${student.email}</td></tr>
          <tr><td>City</td>  <td>${student.city}</td></tr>
        </table>
      </c:when>

      <c:otherwise>
        <h2 class="error">Registration Failed!</h2>
        <p>Please check your inputs and try again.</p>
      </c:otherwise>

    </c:choose>

    <a class="btn" href="register">← Back to Form</a>
  </div>
</body>
</html>