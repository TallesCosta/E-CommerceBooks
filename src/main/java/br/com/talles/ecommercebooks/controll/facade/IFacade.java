package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public interface IFacade {
    
    public Result list(Entity entity, String operation);
    
    public Result save(Entity entity, String operation);
    
	public Result delete(Entity entity, String operation);
	
    public Result find(Entity entity, String operation);
	
    public Result update(Entity entity, String operation);
	
    public Result create(Entity entity, String operation);
    
}
