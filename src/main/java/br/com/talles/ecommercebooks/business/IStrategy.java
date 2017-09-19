package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.domain.Entity;

public interface IStrategy {
    
    public String process(Entity entity);
    
}
