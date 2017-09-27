package br.com.talles.ecommercebooks.controll;

import br.com.talles.ecommercebooks.controll.command.CreateCmd;
import br.com.talles.ecommercebooks.controll.viewHelper.BookVh;
import br.com.talles.ecommercebooks.controll.viewHelper.IViewHelper;
import br.com.talles.ecommercebooks.controll.command.ICommand;
import br.com.talles.ecommercebooks.controll.command.ListCmd;
import br.com.talles.ecommercebooks.controll.command.SaveCmd;
import br.com.talles.ecommercebooks.controll.command.DeleteCmd;
import br.com.talles.ecommercebooks.controll.command.DisableCmd;
import br.com.talles.ecommercebooks.controll.command.EnableCmd;
import br.com.talles.ecommercebooks.controll.command.FindCmd;
import br.com.talles.ecommercebooks.controll.command.ListDisableCmd;
import br.com.talles.ecommercebooks.controll.command.UpdateCmd;
import br.com.talles.ecommercebooks.domain.Entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/books/*"})
public class Servlet extends HttpServlet {

	private Map<String, IViewHelper> viewHelpers;
	private Map<String, ICommand> commands;

	public Servlet() {
		viewHelpers = new HashMap();
		viewHelpers.put("/E-CommerceBooks/books/list", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/list-disable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/save", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/delete", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/find", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/update", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/disable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/enable", new BookVh());
		viewHelpers.put("/E-CommerceBooks/books/create", new BookVh());

		commands = new HashMap();
		commands.put("LIST", new ListCmd());
		commands.put("LIST-DISABLE", new ListDisableCmd());
		commands.put("SAVE", new SaveCmd());
		commands.put("DELETE", new DeleteCmd());
		commands.put("FIND", new FindCmd());
		commands.put("UPDATE", new UpdateCmd());
		commands.put("DISABLE", new DisableCmd());
		commands.put("ENABLE", new EnableCmd());
		commands.put("CREATE", new CreateCmd());
	}
	
	@Override
	protected void service (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try{
			String uri = request.getRequestURI();
        
			IViewHelper viewHelper = viewHelpers.get(uri);

			Entity entity = viewHelper.getEntity(request);

			String cmd = request.getParameter("operation");
			ICommand command = commands.get(cmd);

			Result result = command.execute(entity);

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
