
package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Stock datas
		// ...
				
		// Stock
		Stock stock = new Stock();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :
				break;
			
			case "SAVE":				
				break;

			case "LIST":
				
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
		
		return stock;
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
					dispatcher = request.getRequestDispatcher("/resume.jsp");
					dispatcher.forward(request, response);
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
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
