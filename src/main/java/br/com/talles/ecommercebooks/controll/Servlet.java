package br.com.talles.ecommercebooks.controll;

import br.com.talles.ecommercebooks.controll.command.CreateCmd;
import br.com.talles.ecommercebooks.controll.viewHelper.BookVh;
import br.com.talles.ecommercebooks.controll.viewHelper.CustomerVh;
import br.com.talles.ecommercebooks.controll.viewHelper.IViewHelper;
import br.com.talles.ecommercebooks.controll.command.ICommand;
import br.com.talles.ecommercebooks.controll.command.ListCmd;
import br.com.talles.ecommercebooks.controll.command.SaveCmd;
import br.com.talles.ecommercebooks.controll.command.DeleteCmd;
import br.com.talles.ecommercebooks.controll.command.FindCmd;
import br.com.talles.ecommercebooks.controll.command.UpdateCmd;
import br.com.talles.ecommercebooks.controll.viewHelper.CartVh;
import br.com.talles.ecommercebooks.controll.viewHelper.SaleVh;
import br.com.talles.ecommercebooks.controll.viewHelper.StockVh;
import br.com.talles.ecommercebooks.controll.viewHelper.UserVh;
import br.com.talles.ecommercebooks.domain.Entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/log-in", "/books/*", "/customers/*", "/stocks/*", "/carts/*", "/orders/*"})
public class Servlet extends HttpServlet {

	private Map<String, IViewHelper> viewHelpers;
	private Map<String, ICommand> commands;

	public Servlet() {
		viewHelpers = new HashMap();
		// Books Requests
		viewHelpers.put("/E-CommerceBooks/books/create", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/save", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/list", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/list-disable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/find", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/history", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/update", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/disable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/enable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/delete", new BookVh());
		// Customers Request
		viewHelpers.put("/E-CommerceBooks/customers/create", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/save", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/list", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/list-disable", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/find", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/history", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/update", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/disable", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/enable", new CustomerVh());
		viewHelpers.put("/E-CommerceBooks/customers/delete", new CustomerVh());
		// Stocks Request
		viewHelpers.put("/E-CommerceBooks/stocks/list", new StockVh());
		// Users Request
		viewHelpers.put("/E-CommerceBooks/log-in", new UserVh());
		// Carts Request
		viewHelpers.put("/E-CommerceBooks/carts/save", new CartVh());
		viewHelpers.put("/E-CommerceBooks/carts/delete", new CartVh());
		// Orders Request
		viewHelpers.put("/E-CommerceBooks/orders/list", new SaleVh());

		commands = new HashMap();
		commands.put("CREATE", new CreateCmd());
		commands.put("SAVE", new SaveCmd());
		commands.put("LIST", new ListCmd());
		commands.put("LIST-DISABLE", new ListCmd());
		commands.put("FIND", new FindCmd());
		commands.put("HISTORY", new FindCmd());
		commands.put("UPDATE", new UpdateCmd());
		commands.put("DISABLE", new UpdateCmd());
		commands.put("ENABLE", new UpdateCmd());
		commands.put("DELETE", new DeleteCmd());
	}
	
	@Override
	protected void service (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try{			
			String uri = request.getRequestURI();
        
			IViewHelper viewHelper = viewHelpers.get(uri);
			Entity entity = viewHelper.getEntity(request);

			String operation = request.getParameter("operation");
			ICommand command = commands.get(operation);
			
			Transaction transaction = new Transaction(request, operation);
			Result result = command.execute(entity, transaction);

			viewHelper.setView(result, request, response);
			
		}catch(Exception ex){
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Erro 500</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Infelizmente, ocorreu um erro!</h1>");
			out.println("<p>Tente novamente mais tarde, contataremos o administrador.</p>");
			out.println("</body>");
			out.println("</html>");
		}		
	}
	
}
