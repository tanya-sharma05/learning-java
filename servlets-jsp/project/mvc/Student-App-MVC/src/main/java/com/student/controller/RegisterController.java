package com.student.controller;

import com.student.model.Student;
import com.student.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    // Service is created once when servlet loads
    private final StudentService studentService = new StudentService();

    // GET request — show the empty registration form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    // POST request — handle form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Step 1: Read raw data from form
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String city = request.getParameter("ucity");

        // Step 2: Call service (business logic lives there, not here)
        Student student = studentService.registerStudent(name, email, city);

        // Step 3: Put result into request scope so JSP can read it
        if(student != null) {
            request.setAttribute("student", student);
            request.setAttribute("success", true);
        }
        else {
            request.setAttribute("success", false);
            // Send back what user typed so form can refill it
            request.setAttribute("enteredName",  name);
            request.setAttribute("enteredEmail", email);
            request.setAttribute("enteredCity",  city);
        }

        // Step 4: Forward to result view
        request.getRequestDispatcher("/WEB-INF/views/result.jsp")
                .forward(request, response);
    }
}