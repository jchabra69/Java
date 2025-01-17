package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;
import org.iesbelen.utils.HashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "usuariosServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        RequestDispatcher dispatcher;

        // Si pathInfo es null o está vacío, es la ruta principal de los usuarios
        if (pathInfo == null || pathInfo.isEmpty()) {
            // Mostrar lista de usuarios
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            List<Usuario> listaUsuarios = usuarioDAO.getAll();  // Recuperar la lista completa de usuarios
            request.setAttribute("listaUsuarios", listaUsuarios);  // Asignar la lista al request
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");

        } else {

            if ("/login".equals(pathInfo)) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");

            } else if ("/logout".equals(pathInfo)) {
                // Cierra la sesión y redirige al login
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/tienda/usuarios/login");
                return;

            } else {

                pathInfo = pathInfo.replaceAll("/$", "");
                String[] pathParts = pathInfo.split("/");

                if (pathParts.length == 1) {
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    List<Usuario> listaUsuarios = usuarioDAO.getAll();
                    request.setAttribute("listaUsuarios", listaUsuarios);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");

                } else if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");

                } else if (pathParts.length == 2) {
                    try {
                        int usuarioId = Integer.parseInt(pathParts[1]);
                        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                        Optional<Usuario> optUsuario = usuarioDAO.find(usuarioId);

                        if (optUsuario.isPresent()) {
                            request.setAttribute("usuario", optUsuario.get());
                            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/detalle-usuario.jsp");
                        } else {
                            response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                        return;
                    }
                } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    try {
                        int usuarioId = Integer.parseInt(pathParts[2]);
                        Usuario usuario = usuarioDAO.find(usuarioId).orElse(null);

                        if (usuario != null) {
                            request.setAttribute("usuario", usuario);
                            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");
                        } else {
                            response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
                        return;
                    }
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp");
                }
            }
        }

        dispatcher.forward(request, response);
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");
        RequestDispatcher dispatcher = null;

        if (__method__ == null) {

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            Usuario usuario = new Usuario();

            usuario.setNombre(nombre);
            usuario.setEmail(email);
            try {
                usuario.setPassword(HashUtil.hashPassword(password)); // Hash de la contraseña
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException("Error al hashear la contraseña", e);
            }
            usuario.setRol(rol);

            // Aquí se debe llamar al método create de UsuarioDAO
            try {
                usuarioDAO.create(usuario);  // Llamada al método create para insertar el usuario
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException("Error al crear el usuario", e);
            }

        } else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {

            doPut(request, response);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {

            doDelete(request, response);

        } else if (__method__ != null && "login".equalsIgnoreCase(__method__)) {

            String nombreUsuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            Optional<Usuario> usuarioOpt = usuarioDAO.validarUsuario(nombreUsuario, password);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();

                // Guarda al usuario en la sesión
                request.getSession().setAttribute("usuarioLogueado", usuario);

                response.sendRedirect(request.getContextPath() + "/index.jsp");

            } else {

                // Si las credenciales no son correctas...
                request.setAttribute("loginError", "Usuario o contraseña incorrectos");

                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
                dispatcher.forward(request, response);
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int usuarioId = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Contraseña nueva (si se proporciona)
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(rol);

        if (password != null && !password.trim().isEmpty()) {
            try {
                // Hasheamos la nueva contraseña
                usuario.setPassword(HashUtil.hashPassword(password));
            } catch (NoSuchAlgorithmException e) {
                throw new ServletException("Error al hashear la contraseña", e);
            }
        } else {

        }

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.update(usuario);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String codigo = request.getParameter("id");

        try {
            int id = Integer.parseInt(codigo);
            usuarioDAO.delete(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
