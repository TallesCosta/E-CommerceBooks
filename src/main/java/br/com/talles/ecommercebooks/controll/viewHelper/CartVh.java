package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;
import br.com.talles.ecommercebooks.domain.sale.Stock;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Cart datas
		String idS = request.getParameter("id");
		long id = 0L;
		if (!(idS == null || idS.equals("")))
			id = Long.valueOf(idS);
		
		String unitaryPriceS = request.getParameter("unitaryPrice");
		double unitaryPrice = 0.0;
		if (!(unitaryPriceS == null || unitaryPriceS.equals("")))
			unitaryPrice = Double.valueOf(unitaryPriceS);
		
		String amountS = request.getParameter("amount");
		int amount = 0;
		if (!(amountS == null || amountS.equals("")))
			amount = Integer.valueOf(amountS);

		String idStockS = request.getParameter("id_stock");
		int idStock = 0;
		if (!(idStockS == null || idStockS.equals("")))
			idStock = Integer.valueOf(idStockS);

		String idBookS = request.getParameter("id_book");
		int idBook = 0;
		if (!(idBookS == null || idBookS.equals("")))
			idBook = Integer.valueOf(idBookS);

		// Cart
		Cart cart = new Cart();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :
				break;
			
			case "SAVE":
				cart.addSaleItem(new SaleItem(unitaryPrice, amount, new Book(idBook, new Stock(idStock))));
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
				cart.setId(id);
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
					dispatcher = request.getRequestDispatcher("/cart/list.jsp");
					dispatcher.forward(request, response);
					break;
			}
		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
