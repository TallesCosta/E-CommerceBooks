package br.com.talles.ecommercebooks.controll;

import br.com.talles.ecommercebooks.domain.customer.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "FilterAuthentication",
        urlPatterns = {
        "/customers/list", "/customers/list-disable", "/customers/history", "/customers/find",
        "/customers/update", "/customers/disable", "/customers/enable", "/customers/delete",
        "/books/*", "/sales/*", "/orders/*", "/exchanges/*" })
public class FilterAuthentication implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");

        if (userSession == null) {
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
