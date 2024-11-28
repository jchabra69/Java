package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.*;
import org.iesbelen.model.Usuario;
import org.iesbelen.utils.HashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

        // LOGIN
        if ("/login".equals(pathInfo)) {
            // LLÉVAME AL FORMULARIO DEL LOGIN
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");

        } else if ("/logout".equals(pathInfo)) {

            // Si la ruta es /logout, cierra la sesión y redirige al login
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // Invalida la sesión
            }
            response.sendRedirect(request.getContextPath() + "/tienda/usuarios/login"); // Redirige al login

            return;

        } else {

            if (pathInfo != null) {
                pathInfo = pathInfo.replaceAll("/$", "");
            }
            String[] pathParts = pathInfo != null ? pathInfo.split("/") : new String[]{};

            if (pathParts.length == 0 || "/".equals(pathInfo)) {

                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                request.setAttribute("usuarios", usuarioDAO.getAll());
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");

            } else if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");

            } else if (pathParts.length == 2) {

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
            // Crea uno nuevo
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

            try {
                usrDao.create(usuario);
                response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        } else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            doPut(request, response);
            response.sendRedirect(request.getContextPath() + "/tienda/usuarios");

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            // Borrar uno existente
            doDelete(request, response);
            response.sendRedirect(request.getContextPath() + "/tienda/usuarios");

        } else if (__method__ != null && "login".equalsIgnoreCase(__method__)) {

            String nombreUsuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            Optional<Usuario> usuarioOpt = usuarioDAO.validarUsuario(nombreUsuario, password);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();

                // Guarda al usuario en la sesión
                request.getSession().setAttribute("usuarioLogueado", usuario);

                // Esta ruta termina redirigiendo a la ruta GET /(index home)
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {

                // Si las credenciales no son correctas, mostrar error
                request.setAttribute("loginError", "Usuario o contraseña incorrectos");

                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
                dispatcher.forward(request, response);
            }

        }
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
