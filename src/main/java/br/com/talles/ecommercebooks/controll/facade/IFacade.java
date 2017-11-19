package br.com.talles.ecommercebooks.controll.facade;

import br.com.talles.ecommercebooks.controll.Transaction;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public interface IFacade {
    
    public Result list(Entity entity, Transaction operation);
    
    public Result save(Entity entity, Transaction operation);
    
	public Result delete(Entity entity, Transaction operation);
	
    public Result find(Entity entity, Transaction operation);
	
    public Result update(Entity entity, Transaction operation);
	
    public Result create(Entity entity, Transaction operation);
    
}
