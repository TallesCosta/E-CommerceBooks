package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.History;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.domain.sale.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaleVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		// Sale datas
		String idS = request.getParameter("id");
		long id = 0L;
		if (!(idS == null || idS.equals("")))
			id = Long.valueOf(idS);

		String idDeliveryAddressS = request.getParameter("idDeliveryAddress");
		long idDeliveryAddress = 0L;
		if (!(idDeliveryAddressS == null || idDeliveryAddressS.equals("")))
			idDeliveryAddress = Long.valueOf(idDeliveryAddressS);

		String shippingCostS = request.getParameter("shippingCost");
		double shippingCost = 0.0;
		if (!(shippingCostS == null || shippingCostS.equals("")))
			shippingCost = Double.valueOf(shippingCostS);

		String status = request.getParameter("status");

		String totalS = request.getParameter("total");
		double total = 0.0;
		if (!(totalS == null || totalS.equals("")))
			total = Math.abs( Double.valueOf(totalS) );

		String idPromotionalCouponS = request.getParameter("idPromotionalCoupon");
		long idPromotionalCoupon = 0L;
		if (!(idPromotionalCouponS == null || idPromotionalCouponS.equals("")))
			idPromotionalCoupon = Long.valueOf(idPromotionalCouponS);

		// CreditCards
		int k = 1;
		List<CreditCard> creditCards = new ArrayList<>();
		while (request.getParameter("creditCard" + k) != null) {
			double paymentValue = BigDecimal.valueOf(
					Double.valueOf(request.getParameter("creditCard" + k))).doubleValue();

			if (paymentValue > 0d) {
				long idCreditCard = Long.valueOf(request.getParameter("idCreditCard" + k));
				creditCards.add(new CreditCard(paymentValue, idCreditCard));
			}
			k++;
		}

		String idCreditCardS = request.getParameter("idCreditCard");
		long idCreditCard = 0L;
		if (!(idCreditCardS == null || idCreditCardS.equals("")))
			idCreditCard = Long.valueOf(idCreditCardS);

		// ExchangeCoupon data
		List<String> exchangeCouponS = new ArrayList<>();
		if(request.getParameterValues("exchangeCoupon") != null)
			exchangeCouponS = Arrays.asList(request.getParameterValues("exchangeCoupon"));

		List<ExchangeCoupon> exchangeCoupons = new ArrayList<>();
		long idExchangeCoupon = 0L;
		double exchangeCuopon = 0.0;
		for(String idExchangeCouponS : exchangeCouponS){
			if (!(idExchangeCouponS == null || idExchangeCouponS.equals(""))){
				String[] datas = idExchangeCouponS.split("-");
				idExchangeCoupon = Long.valueOf(datas[0]);
				exchangeCuopon = Double.valueOf(datas[1]);
			}
			exchangeCoupons.add(new ExchangeCoupon(idExchangeCoupon, exchangeCuopon));
		}

		// History
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Default Admin
		long userId = 1L;
		if (userSession != null && userSession.getId() != 0L) {
			userId = userSession.getId();
		}

		// Sale
		Sale sale = new Sale();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :
				break;
			
			case "SAVE":
				sale.setDelivery(new Delivery(new ShippingCost(shippingCost),
						new DeliveryAddress(idDeliveryAddress)));
				sale.setCreditCards(creditCards);
				sale.setExchangeCoupons(exchangeCoupons);
				sale.setPromotionalCoupon(new PromotionalCoupon(idPromotionalCoupon));
				sale.setPrice(total);
				sale.setHistory(new History(new Date(), new User(userId), sale));
				break;

			case "LIST":
				break;

			case "LIST-DISABLE":
				break;

			case "FIND":
				sale.setId(id);
				break;

			case "HISTORY":
				break;
				
			case "UPDATE":
				sale.setId(id);
				sale.setStatus(new Status(status));
				sale.setHistory(new History(new Date(), new User(userId), sale));
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
					if (result.hasMsg()) {
						dispatcher = request.getRequestDispatcher("/cart/list.jsp");
						dispatcher.forward(request, response);
					}
					dispatcher = request.getRequestDispatcher("/sale/create.jsp");
					dispatcher.forward(request, response);
					break;
					
				case "SAVE":
					if (result.hasMsg()) {
						dispatcher = request.getRequestDispatcher("/sale/create.jsp");
						dispatcher.forward(request, response);
					} else
						response.sendRedirect("/E-CommerceBooks/stocks/list?operation=LIST");
					break;

				case "LIST":
					dispatcher = request.getRequestDispatcher("/sale/list.jsp");
					dispatcher.forward(request, response);
					break;

				case "LIST-DISABLE":
					break;
					
				case "FIND":
					dispatcher = request.getRequestDispatcher("/sale/show.jsp");
					dispatcher.forward(request, response);
					break;

				case "HISTORY":					
					break;
					
				case "UPDATE":
					response.sendRedirect("/E-CommerceBooks/sales/list?operation=LIST");
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
