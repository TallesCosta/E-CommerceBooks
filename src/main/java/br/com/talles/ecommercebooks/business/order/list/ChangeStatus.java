package br.com.talles.ecommercebooks.business.order.list;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class ChangeStatus implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        return result;
    }

}
