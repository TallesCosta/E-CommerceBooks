package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.History;
import br.com.talles.ecommercebooks.domain.customer.*;

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
import javax.servlet.http.HttpSession;

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
		if (gender == null)
			gender = "";
		
		String birthDateS = request.getParameter("birthDate");
        Date birthDate = new Date(0L);
        try {
            birthDate = dateFormat.parse(birthDateS);
        } catch (ParseException | NullPointerException ex) {
            Logger.getLogger(CustomerVh.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		// Phone datas
		String ddd = request.getParameter("ddd");
        String phoneNumber = request.getParameter("phoneNumber");
        String phoneType = request.getParameter("phoneType");
		
		String idPhoneS = request.getParameter("idPhone");
		long idPhone = 0L;
		if (!(idPhoneS == null || idPhoneS.equals("")))
			idPhone = Long.valueOf(idPhoneS);
		
		// User datas
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordVerify = request.getParameter("passwordVerify");
		
		String idUserS = request.getParameter("idUser");
		long idUser = 0L;
		if (!(idUserS == null || idUserS.equals("")))
			idUser = Long.valueOf(idUserS);
		
		// Charge/Delivery's Address datas
		String homeAlias = request.getParameter("homeAlias");
		String homeObservation = request.getParameter("homeObservation");
		String homePublicPlaceType = request.getParameter("homePublicPlaceType");
		String homePublicPlace = request.getParameter("homePublicPlace");
		String homeNumber = request.getParameter("homeNumber");
		String homeDistrict = request.getParameter("homeDistrict");
		String homePostalCode = request.getParameter("homePostalCode");
		String homeHomeType = request.getParameter("homeHomeType");
		String homeCity = request.getParameter("homeCity");
		
		String idHomeStateS = request.getParameter("homeState");
		long idHomeState = 0L;
		if (!(idHomeStateS == null || idHomeStateS.equals("")))
			idHomeState = Long.valueOf(idHomeStateS);
		
		String idHomeCountryS = request.getParameter("homeCountry");
		long idHomeCountry = 0L;
		if (!(idHomeCountryS == null || idHomeCountryS.equals("")))
			idHomeCountry = Long.valueOf(idHomeCountryS);
		
		String idHomeS = request.getParameter("idHome");
		long idHome = 0L;
		if (!(idHomeS == null || idHomeS.equals("")))
			idHome = Long.valueOf(idUserS);
		
		// Credit Carde datas
		String cardNumber = request.getParameter("cardNumber");
		String printedName = request.getParameter("printedName");
		String securityCode = request.getParameter("securityCode");
		
		String expirationDateS = request.getParameter("expirationDate");
		Date expirationDate = new Date(0L);
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
		
		// History
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Default Admin
		long userId = 1L;
		if (userSession != null && userSession.getId() != 0L) {
			userId = userSession.getId();
		}
		
		// Customer
		Customer customer = new Customer();
		
		switch(request.getParameter("operation")) {
			case "CREATE" :				
				break;
			
			case "SAVE":
				// Customer
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				customer.setHistory(new History(new Date(), new User(userId), customer));
				// Phone
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType));
				// User
				customer.setUser(new User(email, password, passwordVerify));
				// Charge Address
				customer.setChargeAddresses(Arrays.asList(new ChargeAddress(homeAlias, homeObservation, homePublicPlaceType,
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, homeCity,
						new State(idHomeState, new Country(idHomeCountry)))));
				// Delivery Address
				customer.setDeliveryAddresses(Arrays.asList(new DeliveryAddress(homeAlias, homeObservation, homePublicPlaceType,
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, homeCity,
						new State(idHomeState, new Country(idHomeCountry)))));
				// Credit Card
				customer.setCreditCards(Arrays.asList(new CreditCard(cardNumber, printedName, securityCode, 
						expirationDate, new CardCompany(idCardCompany))));
				break;

			case "LIST":
				// Customer
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				// Phone
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType));
				// User
				customer.setUser(new User(email, password));
				// Charge Address
				customer.setChargeAddresses(Arrays.asList(new ChargeAddress(homeAlias, homeObservation,
						homePublicPlaceType, homePublicPlace, homeNumber, homeDistrict, homePostalCode,
						homeHomeType, homeCity, new State(idHomeState, new Country(idHomeCountry)))));
				// Delivery Address
				customer.setDeliveryAddresses(Arrays.asList(new DeliveryAddress(homeAlias, homeObservation, homePublicPlaceType,
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, homeCity,
						new State(idHomeState, new Country(idHomeCountry)))));
				// Credit Card
				customer.setCreditCards(Arrays.asList(new CreditCard(cardNumber, printedName, securityCode, 
						expirationDate, new CardCompany(idCardCompany))));
				break;

			case "LIST-DISABLE":
				// Customer
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				// Phone
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType));
				// User
				customer.setUser(new User(email, password));
				// Charge Address
				customer.setChargeAddresses(Arrays.asList(new ChargeAddress(homeAlias, homeObservation,
						homePublicPlaceType, homePublicPlace, homeNumber, homeDistrict, homePostalCode,
						homeHomeType, homeCity, new State(idHomeState, new Country(idHomeCountry)))));
				// Delivery Address
				customer.setDeliveryAddresses(Arrays.asList(new DeliveryAddress(homeAlias, homeObservation,
						homePublicPlaceType, homePublicPlace, homeNumber, homeDistrict, homePostalCode, 
						homeHomeType, homeCity, new State(idHomeState, new Country(idHomeCountry)))));
				// Credit Card
				customer.setCreditCards(Arrays.asList(new CreditCard(cardNumber, printedName, securityCode, 
						expirationDate, new CardCompany(idCardCompany))));
				break;

			case "FIND":
				customer.setId(id);
				break;

			case "HISTORY":
				customer.setId(id);
				break;
				
			case "UPDATE":
				customer.setId(id);
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				customer.setHistory(new History(new Date(), new User(userId)));
				// Phone
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType, idPhone));
				// User
				customer.setUser(new User(email, password, passwordVerify, idUser));
				break;

			case "DISABLE":
				customer.setId(id);
				customer.setEnabled(false);
				break;

			case "ENABLE":
				customer.setId(id);
				customer.setEnabled(true);
				break;
				
			case "DELETE":
				break;
				
		}
		
		return customer;
	}

	@Override
	public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;
		request.setAttribute("result", result);
		
		try {
			HttpSession session = request.getSession();
			User userSession = (User) session.getAttribute("user");

			switch(request.getParameter("operation")) {
				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/customer/create.jsp");
					dispatcher.forward(request, response);
					break;

				case "SAVE":
					if (!result.hasMsg())
						response.sendRedirect("/E-CommerceBooks/customers/list?operation=LIST");
					else{
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

				case "FIND":
					dispatcher = request.getRequestDispatcher("/customer/create.jsp");
					dispatcher.forward(request, response);
					break;

				case "HISTORY":
					dispatcher = request.getRequestDispatcher("/customer/show.jsp");
					dispatcher.forward(request, response);
					break;

				case "UPDATE":
					if (!result.hasMsg()) {
						response.sendRedirect("/E-CommerceBooks/customers/list?operation=LIST");
					} else {
						dispatcher = request.getRequestDispatcher("/customer/create.jsp");
						dispatcher.forward(request, response);
					}
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

				case "DELETE":
					break;
			}

		} catch (ServletException | IOException ex) {
			Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
