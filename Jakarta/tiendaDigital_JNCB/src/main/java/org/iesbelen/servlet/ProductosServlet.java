package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaDAOImpl;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Categoria;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "productosServlet", value = "/tienda/productos/*")
public class ProductosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            ProductoDAO productoDAO = new ProductoDAOImpl();
            request.setAttribute("listaProductos", productoDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/crear-producto.jsp");

                // Cargar categorías para el formulario de crear producto
                CategoriaDAOImpl catDAO = new CategoriaDAOImpl();
                List<Categoria> listaCategorias = catDAO.getAll();
                request.setAttribute("listaCategorias", listaCategorias);

            } else if (pathParts.length == 2) {
                try {
                    int productoId = Integer.parseInt(pathParts[1]);
                    ProductoDAO productoDAO = new ProductoDAOImpl();
                    Optional<Producto> optProducto = productoDAO.find(productoId);  // Obtención con Optional

                    if (optProducto.isPresent()) {
                        request.setAttribute("producto", optProducto);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/detalle-producto.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/productos");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    response.sendRedirect(request.getContextPath() + "/tienda/productos");
                    return;
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                ProductoDAO productoDAO = new ProductoDAOImpl();

                try {
                    int productoId = Integer.parseInt(pathParts[2]);
                    Producto producto = productoDAO.find(productoId).orElse(null);

                    if (producto != null) {
                        request.setAttribute("producto", producto);

                        // Cargar categorías para el formulario de edición
                        CategoriaDAOImpl catDAO = new CategoriaDAOImpl();
                        List<Categoria> listaCategorias = catDAO.getAll();
                        request.setAttribute("listaCategorias", listaCategorias);

                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/editar-producto.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/productos");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    response.sendRedirect(request.getContextPath() + "/tienda/productos");
                    return;
                }
            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/productos.jsp");
            }
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {

            ProductoDAO productoDAO = new ProductoDAOImpl();

            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String imagen = request.getParameter("imagen");
            int categoriaID = Integer.parseInt(request.getParameter("categoria"));

            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setImagen(imagen);
            nuevoProducto.setCategoriaID(categoriaID);

            productoDAO.create(nuevoProducto);

        } else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            // Borrar uno existente
            //Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/tienda/categorias");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productoId = Integer.parseInt(request.getParameter("idProducto"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String imagen = request.getParameter("imagen");

        Producto producto = new Producto();
        producto.setIdProducto(productoId);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setImagen(imagen);


        ProductoDAO productoDAO = new ProductoDAOImpl();
        productoDAO.update(producto);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        ProductoDAO proDAO = new ProductoDAOImpl();
        String codigo = request.getParameter("id");

        try {
            int id = Integer.parseInt(codigo);

            proDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
