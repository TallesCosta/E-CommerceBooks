package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.ChangeStatus;
import br.com.talles.ecommercebooks.domain.Entity;

public class ChangeStatusNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		ChangeStatus changeStatus = (ChangeStatus) entity;
        
        if (changeStatus.getStatusCategory().getId() == 0L) {
            result.addMsg("A escolha de uma Categoria é obrigatória!\n");
		} if (changeStatus.getJustification() == null || changeStatus.getJustification().trim().isEmpty()) {
            result.addMsg("Justificativa é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}
