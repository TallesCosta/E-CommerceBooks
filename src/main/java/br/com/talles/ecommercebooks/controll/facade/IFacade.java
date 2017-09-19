package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public interface IFacade {
    
    public Result select(Entity entity);
    
    public Result save(Entity entity);
    
    public Result delete(Entity entity);
    
    public Result find(Entity entity);
    
    public Result update(Entity entity);
    
}
