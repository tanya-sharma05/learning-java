<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
</head>
<body>
    <div class="card">
        <h2>Student Registration</h2>

        <form method="post" action="register">
            <label>Full Name</label>
            <input type="text" name="uname" placeholder="Enter your name" required>
            <br>
            <br>
            <label>Email</label>
            <input type="email" name="email" placeholder="Enter your email" required>
            <br>
            <br>
            <label>City</label>
            <input type="text" name="ucity" placeholder="Enter your city" required>
            <br>
            <br>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>