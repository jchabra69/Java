package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;

//Al inicio de la página, mostraré los productos sin tener que depender del ProductosServlet
//Tendré mi propio Servlet para el index.jsp!!
@WebServlet("") // Solo responde a la ruta raíz
public class TiendaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        // Solo leo la ruta raíz
        if (pathInfo == null || pathInfo.equals("/")) {

            ProductoDAO productoDAO = new ProductoDAOImpl();
            List<Producto> productos = productoDAO.getAll();

            if (productos != null && !productos.isEmpty()) {
                request.setAttribute("productosDisponibles", productos);
            }

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}

