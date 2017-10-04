package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;

public class CustomerNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Customer customer = (Customer) entity;
		
		IStrategy phoneNotBlank = new PhoneNotBlank();
		IStrategy userNotBlank = new UserNotBlank();
		IStrategy addressNotBlank = new AddressNotBlank();
		IStrategy creditCardNotBlank = new CreditCardNotBlank();
		
        if (customer.getRegistry() == null || customer.getRegistry().trim().isEmpty()) {
            result.addMsg("CPF é um campo obrigatório!\n");
		} if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            result.addMsg("Nome é um campo obrigatório!\n");
		} if (customer.getBirthDate() == null || customer.getBirthDate().getTime() == 0L) {
            result.addMsg("Data de Nasc. é um campo obrigatório!\n");
		} if (customer.getGender().getName() == null || customer.getGender().getName().trim().isEmpty()) {
            result.addMsg("Gênero é um campo obrigatório!\n");
		}
		
		result = phoneNotBlank.process(customer.getPhone(), result);
		result = userNotBlank.process(customer.getUser(), result);
		result = addressNotBlank.process(customer.getHomeAddress(), result);
		
		if (result.getOperation().equals("UPDATE"))
			result = addressNotBlank.process(customer.getChargeAddress(), result);
		
		if (result.getOperation().equals("SAVE"))
			result = creditCardNotBlank.process(customer.getCreditCard(0), result);
		
		return result;
	}
	
}
