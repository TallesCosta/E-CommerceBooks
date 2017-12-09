package br.com.talles.ecommercebooks.business.customer.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.address.save.AddressNotBlank;
import br.com.talles.ecommercebooks.business.creditCard.save.CreditCardNotBlank;
import br.com.talles.ecommercebooks.business.customer.CustomerNotBlank;
import br.com.talles.ecommercebooks.business.customer.PasswordValidate;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;

public class CustomerValidateSave implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Customer customer = (Customer) entity;
		
		IStrategy customerNotBlank = new CustomerNotBlank();
		IStrategy addressNotBlank = new AddressNotBlank();
		IStrategy creditCardNotBlank = new CreditCardNotBlank();
		IStrategy passwordValidate = new PasswordValidate();
		
		result = customerNotBlank.process(customer, result);
		// At this time, chargeAddress and deliveryAddress are equals.
		result = addressNotBlank.process(customer.getChargeAddress(0), result);
		result = creditCardNotBlank.process(customer.getCreditCard(0), result);
		result = passwordValidate.process(customer.getUser(), result);
		
		return result;
	}
	
}
