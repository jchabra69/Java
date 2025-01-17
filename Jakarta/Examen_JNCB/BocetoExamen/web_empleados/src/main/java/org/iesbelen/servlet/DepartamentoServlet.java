package org.iesbelen.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.iesbelen.dao.DepartamentoDAO;
import org.iesbelen.dao.DepartamentoDAOImpl;
import org.iesbelen.dto.DepartamentoDTO;
import org.iesbelen.model.Departamento;

@WebServlet(name = "departamentosServlet", value = "/empresa/departamento/*")
public class DepartamentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/departamento/
     * 		/departamento/{id}
     * 		/departamento/editar{id}
     * 		/departamento/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            DepartamentoDAO depDAO = new DepartamentoDAOImpl();

            List<DepartamentoDTO> misDepartamentos = new ArrayList<>();

            for (Departamento dep : depDAO.getAll()) {

                misDepartamentos.add(new DepartamentoDTO(dep));

            }


            //GET
            //	/departamento/
            //	/departamento

            request.setAttribute("listaDepartamento", depDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/departamento.jsp");

        } else {
            // GET
            // 		/departamento/{id}
            // 		/departamento/{id}/
            // 		/departamento/edit/{id}
            // 		/departamento/edit/{id}/
            // 		/departamento/crear
            // 		/departamento/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /departamento/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/crear-departamento.jsp");


            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/departamento/departamento.jsp");

            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            // Crear uno nuevo
            DepartamentoDAO depDAO = new DepartamentoDAOImpl();

            //LO COJO TODO COMO STRING
            String nombre = request.getParameter("nombre");
            String presupuesto = request.getParameter("presupuesto");
            String gastos = request.getParameter("gastos");

            Departamento nuevoDep = new Departamento();
            nuevoDep.setNombre(nombre);
            nuevoDep.setPresupuesto(Double.parseDouble(presupuesto));
            nuevoDep.setGastos(Double.parseDouble(gastos));

            depDAO.create(nuevoDep);

        } else {
            System.out.println("Opción POST no soportada.");
        }

        //response.sendRedirect("../../../empresa/departamento");
        response.sendRedirect(request.getContextPath() + "/empresa/departamento");
    }


    //NO HAY DELETE, NI EDITAR (DELETE, PUT) EN DEPARTAMENTO
}
