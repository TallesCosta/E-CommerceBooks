package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.List;

public interface IDao {
    
    public List<Entity> select();
    
    public boolean save(Entity entity);
    
    public boolean delete(Entity entity);
    
    public Entity find(Entity entity);
    
    public boolean update(Entity entity);
	
	public Entity findLast();
    
	public boolean disable(Entity entity);
	
	public boolean enable(Entity entity);
	
    public List<Entity> selectDisabled();
	
}
