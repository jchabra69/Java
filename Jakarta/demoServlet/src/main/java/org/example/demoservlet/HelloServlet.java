package org.example.demoservlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String email = "<h1> " + message + request.getParameter("email") + "</h1>";

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        //out.println("<h1>" + message + "</h1>");
        out.println(email);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}