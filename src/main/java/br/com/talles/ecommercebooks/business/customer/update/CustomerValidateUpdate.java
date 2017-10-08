package br.com.talles.ecommercebooks.business.customer.update;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.customer.AddressNotBlank;
import br.com.talles.ecommercebooks.business.customer.CustomerNotBlank;
import br.com.talles.ecommercebooks.business.customer.PasswordValidate;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;

public class CustomerValidateUpdate implements IStrategy {
	
	@Override
	public Result process(Entity entity, Result result) {
		Customer customer = (Customer) entity;
		
		IStrategy customerNotBlank = new CustomerNotBlank();
		IStrategy addressNotBlank = new AddressNotBlank();
		IStrategy passwordValidate = new PasswordValidate();
		
		result = customerNotBlank.process(customer, result);
		result = addressNotBlank.process(customer.getChargeAddress(), result);
		result = passwordValidate.process(customer.getUser(), result);
		
		return result;
	}
	
}
