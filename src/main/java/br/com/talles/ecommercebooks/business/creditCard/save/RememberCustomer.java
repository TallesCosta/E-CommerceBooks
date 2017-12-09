package br.com.talles.ecommercebooks.business.creditCard.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;

public class RememberCustomer implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        CreditCard creditCard = (CreditCard) entity;

        result.addEntity(creditCard.getCustomer());
        return result;
    }

}
