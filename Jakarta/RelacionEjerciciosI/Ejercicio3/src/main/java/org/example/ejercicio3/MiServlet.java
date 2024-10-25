package org.example.ejercicio3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "miServlet", value = "/MiServlet")
public class MiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String edad = request.getParameter("edad");
        String sexo = request.getParameter("sexo");
        String peso = request.getParameter("peso");
        String estado = request.getParameter("estadoCivil");
       // String aficiones = request.getParameter("aficiones");

        String[] checkBoxes = request.getParameterValues("aficiones");
        String[] sexos = request.getParameterValues("sexo");
        //Los radios tienen que tener nombres diferentes

        String message = "Nombre: " + nombre + "<br> Apellidos: " + apellidos + "<br> Edad: " + edad + "<br> Sexo: " + leerArray(sexos) + "<br> Peso: " + peso + "<br> Estado: " + estado + " <br> Aficiones: " + leerArray(checkBoxes);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(message);
        out.println("</body></html>");



    }

    public String leerArray(String[] array) {

        String cadena = "";

        for (String s : array) {

            cadena += s +", ";

        }

        return cadena;

    }


}