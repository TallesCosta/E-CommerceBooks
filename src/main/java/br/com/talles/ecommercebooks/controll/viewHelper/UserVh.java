package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// User datas
		String email = request.getParameter("email");
        String password = request.getParameter("password");

		// User
		User user = new User();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :				
				break;

			case "SAVE":
				break;

			case "LIST":
				user.setEmail(email);
				user.setPassword(password);
				break;

			case "LIST-DISABLE":
				break;

			case "FIND":
				break;

			case "HISTORY":
				break;
				
			case "UPDATE":
				break;

			case "DISABLE":
				break;

			case "ENABLE":
				break;
				
			case "DELETE":
				break;
		}
		
		return user;
	}

	@Override
	public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;
		request.setAttribute("result", result);
		
		try {
			switch(request.getParameter("operation")) {
				case "CREATE" :
					break;

				case "SAVE":
					break;

				case "LIST":
					if (result.hasMsg()) {
						dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);
					} else {
						if (result.getEntities(User.class.getSimpleName()).get(0).getId() == 1L)
							response.sendRedirect("/E-CommerceBooks/dashboard-admin/list?operation=LIST");
						else {
							String next = request.getParameter("next");
							if (next.equals("/E-CommerceBooks/login.jsp") ||
									next.equals("/E-CommerceBooks/log-in") ||
									next.equals("/E-CommerceBooks/customers/list?operation=LIST"))
								response.sendRedirect("/E-CommerceBooks/orders/list?operation=LIST");
							else
								response.sendRedirect(next);
						}
					}
					break;

				case "LIST-DISABLE":
					break;
					
				case "FIND":
					break;

				case "HISTORY":					
					break;
					
				case "UPDATE":
					break;

				case "DISABLE":
					break;

				case "ENABLE":
					break;		
					
				case "DELETE":
					response.sendRedirect("/E-CommerceBooks/stocks/list?operation=LIST");
					break;
			}
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
