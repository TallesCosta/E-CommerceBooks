package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;

public class CreditCardNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		CreditCard creditCard = (CreditCard) entity;
		
		if (creditCard.getNumber() == null || creditCard.getNumber().trim().isEmpty()) {
            result.addMsg("Número do cartão de crédito é um campo obrigatório!\n");
		} if (creditCard.getPrintedName() == null || creditCard.getPrintedName().trim().isEmpty()) {
            result.addMsg("Nome impresso no cartão de crédito é um campo obrigatório!\n");
		} if (creditCard.getSecurityCode() == null || creditCard.getSecurityCode().trim().isEmpty()) {
            result.addMsg("Código de segurança do cartão de crédito é um campo obrigatório!\n");
		} if (creditCard.getExpirationDate() == null || creditCard.getExpirationDate().getTime() == 0L) {
            result.addMsg("Data de expiração do cartão de crédito é um campo obrigatório!\n");
		} if (creditCard.getCardCompany().getId() == 0L) {
            result.addMsg("A escolha de uma bandeira de cartão é obrigatória!\n");
		}
		
		return result;
	}
	
}
