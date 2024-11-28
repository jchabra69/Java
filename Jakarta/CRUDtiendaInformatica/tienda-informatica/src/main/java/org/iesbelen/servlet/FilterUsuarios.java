package org.iesbelen.servlet;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.iesbelen.model.Usuario;

/**
 * Servlet Filter implementation class UsuariosFilter
 */
@WebFilter(
        urlPatterns = { "/tienda/usuarios/*" },
        initParams = {
                @WebInitParam(name = "acceso-concedido-a-rol", value = "Admin")

        })
public class FilterUsuarios extends HttpFilter implements Filter {

    private String rolAcceso;

    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterUsuarios() {
        super();

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        //Cast de ServletRequest a HttpServletRequest, el único tipo implementado
        //en el contenedor de Servlet: HttpServletRequest & HttpServletReponse
        HttpServletRequest httpRequest =(HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;


        //Accediendo al objeto de sesión
        HttpSession session = httpRequest.getSession();

        //Obteniendo la url
        String url = httpRequest.getRequestURL().toString();

        Usuario usuario = null;

        if (session != null  //Seteo inline de usuario
                && (usuario = (Usuario)session.getAttribute("usuarioLogueado") )!= null
                && "admin".equals(usuario.getRol())) {

            //Si eres administrador acceso a cualquier página del filtro
            chain.doFilter(request, response);
            return;

        } else if (url.endsWith("/usuarios/crear")
                || url.contains("/usuarios/editar")
                //|| url.contains("/usuarios/")
                || url.contains("/usuarios/borrar"))


        {



            // Si no tiene permisos el usuario, mostrar error

            request.setAttribute("permError", "Debes iniciar sesión con un rol de administrador para esa acción");

            // Usuario no administrador trata de acceder a páginas de crear y editar, y el filtro lo redirige a login
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/WEB-INF/jsp/usuarios/login.jsp");

            dispatcher.forward(request, response);

            return;

        } else {

            // Otras rutas /fabricantes y /fabricantes/{id} se dan paso a cualquier rol

            //RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/WEB-INF/jsp/fabricantes.jsp");
            //dispatcher.forward(httpRequest, httpResponse);
            chain.doFilter(request, response);
            return;

        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

        this.rolAcceso = fConfig.getInitParameter("acceso-concedido-a-rol");

    }
}