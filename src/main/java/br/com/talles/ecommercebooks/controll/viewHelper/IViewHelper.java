package br.com.talles.ecommercebooks.controll.viewHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public interface IViewHelper {
    
    public Entity getEntity(HttpServletRequest request);
    
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response);
    
}
