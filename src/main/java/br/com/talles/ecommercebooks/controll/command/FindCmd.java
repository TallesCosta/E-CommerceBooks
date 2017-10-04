package br.com.talles.ecommercebooks.controll.command;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class FindCmd extends AbstractCommand{

    @Override
    public Result execute(Entity entity, String operation) {
        return facade.find(entity, operation);
    }
    
}
