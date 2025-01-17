package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Producto;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CarritoServlet", value = "/tienda/carrito/*")
public class CarritoServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/tienda/usuarios/login");
            return;
        }

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        double total = calcularTotal(carrito);

        request.setAttribute("carrito", carrito);
        request.setAttribute("total", total);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrito/carrito.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        if ("add".equals(action)) {
            int productoId = Integer.parseInt(request.getParameter("productoId"));
            Optional<Producto> productoOptional = obtenerProductoPorId(productoId);

            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                carrito.add(producto);
                session.setAttribute("carrito", carrito);
            } else {
                request.setAttribute("error", "Producto no encontrado.");
            }
        } else if ("confirmar".equals(action)) {
            session.setAttribute("carrito", new ArrayList<>());
            request.setAttribute("mensaje", "Compra confirmada con éxito.");
        }

        request.setAttribute("carrito", carrito);
        request.setAttribute("total", calcularTotal(carrito));

        response.sendRedirect(request.getContextPath() + "/tienda/carrito");
    }

    private double calcularTotal(List<Producto> carrito) {
        double total = 0;
        for (Producto producto : carrito) {
            total += producto.getPrecio();
        }
        return total;
    }

    private Optional<Producto> obtenerProductoPorId(int productoId) {
        return productoDAO.find(productoId);
    }
}
