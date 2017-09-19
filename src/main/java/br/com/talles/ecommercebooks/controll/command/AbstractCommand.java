package br.com.talles.ecommercebooks.controll.command;

import br.com.talles.ecommercebooks.controll.facade.IFacade;
import br.com.talles.ecommercebooks.controll.facade.Facade;

public abstract class AbstractCommand implements ICommand{
     
    protected IFacade facade;
    
    public AbstractCommand() {
        facade = new Facade();
    }
    
}
