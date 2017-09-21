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
	
    public Map<String, List<Entity>> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities.clear();
        this.entities.put(entities.getClass().getSimpleName(), entities);
    }

    public List<Entity> getEntity(String key){
        return entities.get(key);
    }
    
    public void setEntity(Entity entity) {
        this.entities.clear();
		this.entities.put(entities.getClass().getSimpleName(), Arrays.asList(entity));
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
