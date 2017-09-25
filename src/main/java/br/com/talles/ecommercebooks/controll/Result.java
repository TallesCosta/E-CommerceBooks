package br.com.talles.ecommercebooks.controll;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Result {
    
    private Map<String, List<Entity>> entities;
    private String msg;
    
	public Result() {
        entities = new HashMap<>();
        msg = "";
    }
	
    public List<Entity> getEntities(String key){
        return entities.get(key);
    }

	public List<Object> getKeys(){
		return Arrays.asList(entities.keySet().toArray());
	}
	
    public void setEntity(Entity entity) {
        this.entities.clear();
		this.entities.put(entity.getClass().getSimpleName(), Arrays.asList(entity));
    }
	
    public void setEntities(List<Entity> entities) {
        this.entities.clear();
		
        if (!entities.isEmpty())
			this.entities.put(entities.get(0).getClass().getSimpleName(), entities);
    }
    
	public void addEntity(Entity entity) {
		this.entities.put(entity.getClass().getSimpleName(), Arrays.asList(entity));
    }
	
    public void addEntities(List<Entity> entities) {
		if (!entities.isEmpty())
			this.entities.put(entities.get(0).getClass().getSimpleName(), entities);
    }
	
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void addMsg(String msg){
        this.msg += msg;
    }
    
    public boolean hasEntities() {
        return !(entities == null) && !(entities.isEmpty());
    }
    
    public boolean hasMsg() {
        return !(msg == null) && !(msg.isEmpty());
    }
    
}
