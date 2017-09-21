package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public interface IStrategy {
    
    public Result process(Entity entity, Result result);
    
}
