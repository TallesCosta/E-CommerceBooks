package br.com.talles.ecommercebooks.controll.command;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class SelectCmd extends AbstractCommand{

    @Override
    public Result execute(Entity entity) {
        return facade.select(entity);
    }
    
}
