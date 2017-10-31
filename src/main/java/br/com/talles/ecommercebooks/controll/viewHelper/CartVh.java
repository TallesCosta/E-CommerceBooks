package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Cart datas
		String priceS = request.getParameter("unitaryPrice");
		double price = Double.valueOf(priceS);
		String totalAmountS = request.getParameter("amount");
		int totalAmount = Integer.valueOf(totalAmountS);
		
		// Cart
		Cart cart = null;
		
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
				HttpSession session = request.getSession();
				cart = (Cart) session.getAttribute("cart");
				
				if (cart != null)
					cart = new Cart();
				
				cart.addSaleItem(new SaleItem(price, totalAmount));
				cart.calcTotalPrice();
				cart.calcTotalAmount();
				
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
