package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Cart datas
		String unitaryPriceS = request.getParameter("unitaryPrice");
		double unitaryPrice = Double.valueOf(unitaryPriceS);
		String amountS = request.getParameter("amount");
		int amount = Integer.valueOf(amountS);
		
		// Cart
		Cart cart = new Cart();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :
				break;
			
			case "SAVE":
				cart.addSaleItem(new SaleItem(unitaryPrice, amount));
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
		
		return cart;
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
					dispatcher = request.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
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
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
