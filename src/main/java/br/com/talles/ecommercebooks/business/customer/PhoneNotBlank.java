package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Phone;

public class PhoneNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Phone phone = (Phone) entity;
		
		if (phone.getDdd() == null || phone.getDdd().trim().isEmpty()) {
            result.addMsg("DDD é um campo obrigatório!\n");
		} if (phone.getNumber() == null || phone.getNumber().trim().isEmpty()) {
            result.addMsg("Número de telefone é um campo obrigatório!\n");
		} if (phone.getPhoneType() == null || phone.getPhoneType().trim().isEmpty()) {
            result.addMsg("Tipo de telefone é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}
