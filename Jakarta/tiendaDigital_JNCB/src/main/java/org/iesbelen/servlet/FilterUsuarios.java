package org.iesbelen.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter(
        urlPatterns = { "/tienda/usuarios/*", "/tienda/categorias/*", "/tienda/productos/*" },
        initParams = {
                @WebInitParam(name = "acceso-concedido-a-rol", value = "Admin")
        })
public class FilterUsuarios extends HttpFilter implements Filter {

    private String rolAcceso;

    public FilterUsuarios() {
        super();
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        String url = httpRequest.getRequestURL().toString();

        Usuario usuario = null;

        if (session != null && (usuario = (Usuario) session.getAttribute("usuarioLogueado")) != null) {

            if ("admin".equals(usuario.getRol())) {
                chain.doFilter(request, response);
                return;
            }
        }
/*
        if (url.contains("/usuarios/") || url.contains("/categorias/") || url.contains("/productos/")) {
            request.setAttribute("permError", "Debes iniciar sesión con un rol de administrador para esa acción");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
            dispatcher.forward(request, response);
            return;
        } */

        //NO BLOQUEO LAS RUTAS DE USUARIOS PARA QUE TE CREES UNO COMO ADMIN, YA QUE EL QUE VIENE POR DEFECTO EN EL SCRIPT NO FUNCIONA AL ESTAR CREADO MANUALMENTE SIN HASH ETC
        if (url.contains("/categorias/") || url.contains("/productos/")) {
            request.setAttribute("permError", "Debes iniciar sesión con un rol de administrador para esa acción");
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.rolAcceso = fConfig.getInitParameter("acceso-concedido-a-rol");
    }
}
