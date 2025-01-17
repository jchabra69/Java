package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.dao.EmpleadoDAOImpl;
import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.model.Empleado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "empleadosServlet", value = "/empresa/empleado/*")
public class EmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * /empleado/
     * /empleado/{id}
     * /empleado/editar{id}
     * /empleado/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            EmpleadoDAOImpl empleadoDAO = new EmpleadoDAOImpl();

            //GET
            //	/empleado/
            //	/empleado

            request.setAttribute("listaEmpleado", empleadoDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/empleado.jsp");

        } else {
            // GET
            // 		/empleado/{id}
            // 		/empleado/{id}/
            // 		/empleado/edit/{id}
            // 		/empleado/edit/{id}/
            // 		/empleado/crear
            // 		/empleado/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /empleado/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/crear-empleado.jsp");


            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empleado/empleado.jsp");

            }
        }

        dispatcher.forward(request, response);
    }

}
