package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.List;

public interface IDao {
    
    public List<Entity> select(boolean enabled, Entity entity);
    
    public boolean save(Entity entity);
    
    public boolean delete(Entity entity);
    
    public Entity find(Entity entity);
    
    public boolean update(Entity entity, String operation);
	
	public Entity findLast();
	
}
