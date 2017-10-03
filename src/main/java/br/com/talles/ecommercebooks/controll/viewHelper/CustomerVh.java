package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CardCompany;
import br.com.talles.ecommercebooks.domain.customer.ChargeAddress;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.City;
import br.com.talles.ecommercebooks.domain.customer.State;
import br.com.talles.ecommercebooks.domain.customer.Country;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.customer.Gender;
import br.com.talles.ecommercebooks.domain.customer.Phone;
import br.com.talles.ecommercebooks.domain.customer.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
		
		String idDeliveryCityS = request.getParameter("deliveryCity");
		long idDeliveryCity = 0L;
		if (!(idDeliveryCityS == null || idDeliveryCityS.equals("")))
			idDeliveryCity = Long.valueOf(idDeliveryCityS);
		
		String idDeliveryStateS = request.getParameter("deliveryState");
		long idDeliveryState = 0L;
		if (!(idDeliveryStateS == null || idDeliveryStateS.equals("")))
			idDeliveryState = Long.valueOf(idDeliveryStateS);
		
		String idDeliveryCountryS = request.getParameter("deliveryCountry");
		long idDeliveryCountry = 0L;
		if (!(idDeliveryCountryS == null || idDeliveryCountryS.equals("")))
			idDeliveryCountry = Long.valueOf(idDeliveryCountryS);
		
		// Charge's Address datas
		String chargeAlias = request.getParameter("chargeAlias");
		String chargeObservation = request.getParameter("chargeObservation");
		String chargePublicPlaceType = request.getParameter("chargePublicPlaceType");
		String chargePublicPlace = request.getParameter("chargePublicPlace");
		String chargeNumber = request.getParameter("chargeNumber");
		String chargeDistrict = request.getParameter("chargeDistrict");
		String chargePostalCode = request.getParameter("chargePostalCode");
		String chargeHomeType = request.getParameter("chargeHomeType");
		
		String idChargeCityS = request.getParameter("chargeCity");
		long idChargeCity = 0L;
		if (!(idChargeCityS == null || idChargeCityS.equals("")))
			idChargeCity = Long.valueOf(idChargeCityS);
		
		String idChargeStateS = request.getParameter("chargeState");
		long idChargeState = 0L;
		if (!(idChargeStateS == null || idChargeStateS.equals("")))
			idChargeState = Long.valueOf(idChargeStateS);
		
		String idChargeCountryS = request.getParameter("chargeCountry");
		long idChargeCountry = 0L;
		if (!(idChargeCountryS == null || idChargeCountryS.equals("")))
			idChargeCountry = Long.valueOf(idChargeCountryS);
		
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
		long idCardCompany = 0L;
		if (!(cardCompanyS == null || cardCompanyS.equals("")))
			idCardCompany = Long.valueOf(cardCompanyS);
		
		Customer customer = new Customer();
		
		switch(request.getParameter("operation")) {
			case "SAVE":
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				customer.setPhone(new Phone(ddd, name, phoneType));
				customer.setUser(new User(email, password, passwordVerify));
				customer.setDeliveryAddress(Arrays.asList(new DeliveryAddress(true, chargeAlias, chargeObservation, chargePublicPlaceType, chargePublicPlace, chargeNumber, chargeDistrict, chargePostalCode, chargeHomeType, new City(idDeliveryCity, new State(idDeliveryState, new Country(idDeliveryCountry))))));
				customer.setChargeAddress(Arrays.asList(new ChargeAddress(deliveryAlias, deliveryObservation, deliveryPublicPlaceType, deliveryPublicPlace, deliveryNumber, deliveryDistrict, deliveryPostalCode, deliveryHomeType, new City(idChargeCity, new State(idChargeState, new Country(idChargeCountry))))));
				customer.setCreditCard(Arrays.asList(new CreditCard(cardNumber, printedName, securityCode, expirationDate, new CardCompany(idCardCompany))));
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
