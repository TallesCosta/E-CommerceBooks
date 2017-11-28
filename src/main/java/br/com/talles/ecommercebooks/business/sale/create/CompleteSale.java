package br.com.talles.ecommercebooks.business.sale.create;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class CompleteSale implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        return result;
    }

}
