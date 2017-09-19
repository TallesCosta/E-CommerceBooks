package br.com.talles.ecommercebooks.controll;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.List;
import java.util.ArrayList;

public class Result {
    
    private List<Entity> entities;
    private String msg;
    
	public Result() {
        entities = new ArrayList();
        msg = "";
    }
	
    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public Entity getEntity(int i){
        return entities.get(i);
    }
    
    public void setEntity(Entity entity) {
        this.entities.clear();
        this.entities.add(entity);
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
