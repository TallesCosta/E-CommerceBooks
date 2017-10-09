package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CardCompany;
import br.com.talles.ecommercebooks.domain.customer.Address;
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
		
		// Home's Address datas
		String homeAlias = request.getParameter("homeAlias");
		String homeObservation = request.getParameter("homeObservation");
		String homePublicPlaceType = request.getParameter("homePublicPlaceType");
		String homePublicPlace = request.getParameter("homePublicPlace");
		String homeNumber = request.getParameter("homeNumber");
		String homeDistrict = request.getParameter("homeDistrict");
		String homePostalCode = request.getParameter("homePostalCode");
		String homeHomeType = request.getParameter("homeHomeType");
		
		String idHomeCityS = request.getParameter("homeCity");
		long idHomeCity = 0L;
		if (!(idHomeCityS == null || idHomeCityS.equals("")))
			idHomeCity = Long.valueOf(idHomeCityS);
		
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
		
		// Charge's Address datas - Only update view
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
		
		String idChargeS = request.getParameter("idCharge");
		long idCharge = 0L;
		if (!(idChargeS == null || idChargeS.equals("")))
			idCharge = Long.valueOf(idChargeS);
		
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
		
		Customer customer = new Customer();
		
		switch(request.getParameter("operation")) {
			case "SAVE":
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType));
				customer.setUser(new User(email, password, passwordVerify));
				customer.setHomeAddress(new Address(homeAlias, homeObservation, homePublicPlaceType, 
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, 
						new City(idHomeCity, new State(idHomeState, new Country(idHomeCountry)))));
				customer.setChargeAddress(new Address(homeAlias, homeObservation, homePublicPlaceType, 
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, 
						new City(idHomeCity, new State(idHomeState, new Country(idHomeCountry)))));
				customer.setDeliveryAddress(Arrays.asList(new DeliveryAddress(true, homeAlias, homeObservation, 
						homePublicPlaceType, homePublicPlace, homeNumber, homeDistrict, homePostalCode, 
						homeHomeType, new City(idHomeCity, new State(idHomeState, new Country(idHomeCountry))))));
				customer.setCreditCard(Arrays.asList(new CreditCard(cardNumber, printedName, securityCode, 
						expirationDate, new CardCompany(idCardCompany))));
				break;

			case "LIST":
				break;

			case "LIST-DISABLE":
				break;
				
			case "DELETE":
				break;

			case "FIND":
				customer.setId(id);
				break;

			case "UPDATE":
				customer.setId(id);
				customer.setRegistry(registry);
				customer.setName(name);
				customer.setBirthDate(birthDate);
				customer.setGender(new Gender(gender));
				customer.setPhone(new Phone(ddd, phoneNumber, phoneType, idPhone));
				customer.setUser(new User(email, password, passwordVerify, idUser));
				customer.setHomeAddress(new Address(homeAlias, homeObservation, homePublicPlaceType, 
						homePublicPlace, homeNumber, homeDistrict, homePostalCode, homeHomeType, 
						new City(idHomeCity, new State(idHomeState, new Country(idHomeCountry))), idHome));
				customer.setChargeAddress(new Address(chargeAlias, chargeObservation, chargePublicPlaceType, 
						chargePublicPlace, chargeNumber, chargeDistrict, chargePostalCode, chargeHomeType, 
						new City(idChargeCity, new State(idChargeState, new Country(idChargeCountry))), idCharge));
				break;

			case "DISABLE":
				customer.setId(id);
				customer.setEnabled(false);
				break;

			case "ENABLE":
				customer.setId(id);
				customer.setEnabled(true);
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
				case "CREATE" :
					dispatcher = request.getRequestDispatcher("/customer/create.jsp");
					dispatcher.forward(request, response);
					break;
					
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
					
				case "FIND":
					dispatcher = request.getRequestDispatcher("/customer/create.jsp");
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
