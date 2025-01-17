package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.CategoriaDAO;
import org.iesbelen.dao.CategoriaDAOImpl;
import org.iesbelen.model.Categoria;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "categoriasServlet", value = "/tienda/categorias/*")
public class CategoriaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
            request.setAttribute("listaCategorias", categoriaDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/categorias.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/crear-categoria.jsp");

            } else if (pathParts.length == 2) {
                try {
                    int categoriaId = Integer.parseInt(pathParts[1]);
                    CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
                    Optional<Categoria> optCategoria = categoriaDAO.find(categoriaId);  // Obtención con Optional

                    if (optCategoria.isPresent()) {
                        request.setAttribute("categoria", optCategoria);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/detalle-categoria.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/categorias");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    response.sendRedirect(request.getContextPath() + "/tienda/categorias");
                    return;
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

                try {
                    int categoriaId = Integer.parseInt(pathParts[2]);
                    Categoria categoria = categoriaDAO.find(categoriaId).orElse(null);

                    if (categoria != null) {
                        request.setAttribute("categoria", categoria);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/editar-categoria.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/categorias");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    response.sendRedirect(request.getContextPath() + "/tienda/categorias");
                    return;
                }
            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorias/categorias.jsp");
            }
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {

            CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setNombre(nombre);
            nuevaCategoria.setDescripcion(descripcion);

            categoriaDAO.create(nuevaCategoria);

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

        int categoriaId = Integer.parseInt(request.getParameter("idCategoria"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaId);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        categoriaDAO.update(categoria);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        CategoriaDAO catDAO = new CategoriaDAOImpl();
        String codigo = request.getParameter("id");

        try {
            int id = Integer.parseInt(codigo);

            catDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

}
