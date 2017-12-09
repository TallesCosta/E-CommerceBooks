package br.com.talles.ecommercebooks.business.address.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;

public class RememberCustomerAddress implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Address address = (Address) entity;

        result.addEntity(address.getCustomer());
        return result;
    }

}
