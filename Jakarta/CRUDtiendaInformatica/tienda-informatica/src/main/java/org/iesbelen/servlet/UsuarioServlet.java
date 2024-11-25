package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Usuario;
import org.iesbelen.utils.HashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "usuariosServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/usuarios/
     * 		/usuarios/{id}
     * 		/usuarios/editar{id}
     * 		/usuarios/crear
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {

            // GET para la lista de usuarios: /usuarios/ o /usuarios
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();


            request.setAttribute("usuarios", usuarioDAO.getAll());


            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");

        } else {
            // GET para detalles de usuario o formularios de edición
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET para la página de creación de usuario
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");

            } else if (pathParts.length == 2) {
                // GET para ver detalles de un usuario: /usuarios/{id}
                try {
                    int idUsuario = Integer.parseInt(pathParts[1]);
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    Usuario usuario = usuarioDAO.find(idUsuario).orElse(null);

                    if (usuario != null) {
                        request.setAttribute("usuario", usuario);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/detalle-usuario.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                        return;
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }

            } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                // GET para editar usuario: /usuarios/editar/{id}
                try {
                    int idUsuario = Integer.parseInt(pathParts[2]);
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    Usuario usuario = usuarioDAO.find(idUsuario).orElse(null);

                    if (usuario != null) {
                        request.setAttribute("usuario", usuario);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }

            } else {
                // Si la ruta no es reconocida, redirigimos a la lista de usuarios
                response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                return;
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
            UsuarioDAO usrDao = new UsuarioDAOImpl();

            String nombre = request.getParameter("nombre");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            Usuario usuario = new Usuario();


            usuario.setNombreUsuario(nombre);
            try {
                usuario.setPassword(HashUtil.hashPassword(password));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            usuario.setRol(rol);


            //Lo de cifrar la contraseña no se hace en DAO, sino aquí

            try {
                usrDao.create(usuario);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

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

        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usrDAO = new UsuarioDAOImpl();
        String idUsuario = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        try {
            int id = Integer.parseInt(idUsuario);
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(id);
            usuario.setNombreUsuario(nombre);
            usuario.setRol(rol);

            if (password != null && !password.isEmpty()) {
                // Si hay nueva contraseña, la ciframos tmb
                try {
                    usuario.setPassword(HashUtil.hashPassword(password));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }

            //Y actualizamos en la base de datos
            usrDAO.update(usuario);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usrDAO = new UsuarioDAOImpl();
        String idUsuario = request.getParameter("idUsuario");

        try {
            int id = Integer.parseInt(idUsuario);
            usrDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
