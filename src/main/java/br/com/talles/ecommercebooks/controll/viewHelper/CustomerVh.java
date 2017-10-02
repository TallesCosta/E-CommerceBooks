package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		Customer customer = new Customer();
		
		switch(request.getParameter("operation")) {
			case "SAVE":
				break;

			case "LIST":
				break;

			case "LIST-DISABLE":
				break;
				
			case "DELETE":
				break;

			case "FIND":
				break;

			case "UPDATE":
				break;

			case "DISABLE":
				break;

			case "ENABLE":
				break;
				
			case "CREATE" :				
				break;
		}
		
		return customer;
	}

	@Override
	public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;
		request.setAttribute("result", result);
		
		try {
			switch(request.getParameter("operation")) {
				case "SAVE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/customers/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/customer/create.jsp");
						dispatcher.forward(request, response);
					}					
					break;

				case "LIST":
					dispatcher = request.getRequestDispatcher("/customer/list.jsp");
					dispatcher.forward(request, response);
					break;

				case "LIST-DISABLE":
					dispatcher = request.getRequestDispatcher("/customer/list-disable.jsp");
					dispatcher.forward(request, response);
					break;
					
				case "DELETE":
					break;

				case "FIND":					
					break;

				case "UPDATE":					
					break;

				case "DISABLE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/customers/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/customer/list.jsp");
						dispatcher.forward(request, response);
					}
					break;

				case "ENABLE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/customers/list-disable?operation=LIST-DISABLE");
					} else {
						dispatcher = request.getRequestDispatcher("/customer/list-disable.jsp");
						dispatcher.forward(request, response);
					}
					break;					
					
				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/customer/create.jsp");
					dispatcher.forward(request, response);
					break;
			}
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
