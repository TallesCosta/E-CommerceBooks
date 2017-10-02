package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerVh implements IViewHelper {

	@Override
	public Entity getEntity(HttpServletRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Customer datas
		String idS = request.getParameter("id");
		long id = 0L;
		if (!(idS == null || idS.equals("")))
			id = Long.valueOf(idS);
		
        String registry = request.getParameter("registry");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
		
		String birthDateS = request.getParameter("birthDate");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birthDateS);
        } catch (ParseException | NullPointerException ex) {
            Logger.getLogger(CustomerVh.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		// Phone datas
		String ddd = request.getParameter("ddd");
        String phoneNumber = request.getParameter("phoneNumber");
        String phoneType = request.getParameter("phoneType");
		
		// User datas
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordVerify = request.getParameter("passwordVerify");
		
		// Delivery's Address datas
		String deliveryAlias = request.getParameter("deliveryAlias");
		String deliveryObservation = request.getParameter("deliveryObservation");
		String deliveryPublicPlaceType = request.getParameter("deliveryPublicPlaceType");
		String deliveryPublicPlace = request.getParameter("deliveryPublicPlace");
		String deliveryNumber = request.getParameter("deliveryNumber");
		String deliveryDistrict = request.getParameter("deliveryDistrict");
		String deliveryPostalCode = request.getParameter("deliveryPostalCode");
		String deliveryHomeType = request.getParameter("deliveryHomeType");
		String deliveryCity = request.getParameter("deliveryCity");
		String deliveryState = request.getParameter("deliveryState");
		String deliveryCountry = request.getParameter("deliveryCountry");
		
		// Charge's Address datas
		String chargeAlias = request.getParameter("chargeAlias");
		String chargeObservation = request.getParameter("chargeObservation");
		String chargePublicPlaceType = request.getParameter("chargePublicPlaceType");
		String chargePublicPlace = request.getParameter("chargePublicPlace");
		String chargeNumber = request.getParameter("chargeNumber");
		String chargeDistrict = request.getParameter("chargeDistrict");
		String chargePostalCode = request.getParameter("chargePostalCode");
		String chargeHomeType = request.getParameter("chargeHomeType");
		String chargeCity = request.getParameter("chargeCity");
		String chargeState = request.getParameter("chargeState");
		String chargeCountry = request.getParameter("chargeCountry");
		
		// Credit Carde datas
		String cardNumber = request.getParameter("cardNumber");
		String printedName = request.getParameter("printedName");
		String securityCode = request.getParameter("securityCode");
		
		String expirationDateS = request.getParameter("expirationDate");
		Date expirationDate = new Date();
		try {
            expirationDate = dateFormat.parse(expirationDateS);
        } catch (ParseException | NullPointerException ex) {
            Logger.getLogger(CustomerVh.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		// Card Company data
		String cardCompanyS = request.getParameter("cardCompany");
		long cardCompany = 0L;
		if (!(cardCompanyS == null || cardCompanyS.equals("")))
			cardCompany = Long.valueOf(cardCompanyS);
		
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
