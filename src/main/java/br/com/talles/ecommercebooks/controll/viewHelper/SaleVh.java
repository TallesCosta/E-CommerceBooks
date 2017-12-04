package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.sale.Delivery;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.domain.sale.ShippingCost;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaleVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Sale datas
		String idDeliveryAddressS = request.getParameter("idDeliveryAddress");
		long idDeliveryAddress = 0L;
		if (!(idDeliveryAddressS == null || idDeliveryAddressS.equals("")))
			idDeliveryAddress = Long.valueOf(idDeliveryAddressS);

		String idCreditCardS = request.getParameter("idCreditCard");
		long idCreditCard = 0L;
		if (!(idCreditCardS == null || idCreditCardS.equals("")))
			idCreditCard = Long.valueOf(idCreditCardS);

		String shippingCostS = request.getParameter("shippingCost");
		double shippingCost = 0.0;
		if (!(shippingCostS == null || shippingCostS.equals("")))
			shippingCost = Double.valueOf(shippingCostS);

		String totalS = request.getParameter("total");
		double total = 0.0;
		if (!(totalS == null || totalS.equals("")))
			total = Double.valueOf(totalS);

		// Sale
		Sale sale = new Sale();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :
				break;
			
			case "SAVE":
				sale.setDelivery(new Delivery(new ShippingCost(shippingCost),
						new DeliveryAddress(idDeliveryAddress)));
				sale.setCreditCard(new CreditCard(idCreditCard));
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
		
		return sale;
	}

	@Override
	public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;
		request.setAttribute("result", result);
		
		try {
			switch(request.getParameter("operation")) {
				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/sale/create.jsp");
					dispatcher.forward(request, response);
					break;
					
				case "SAVE":
					response.sendRedirect("/E-CommerceBooks/stocks/list?operation=LIST");
					break;

				case "LIST":
					dispatcher = request.getRequestDispatcher("/order/list.jsp");
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
