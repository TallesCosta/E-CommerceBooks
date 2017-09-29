package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.PriceGroup;

public class PriceGroupNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		PriceGroup priceGroup = (PriceGroup) entity;
		
		if (priceGroup.getId() == 0L) {
            result.addMsg("A escolha de um Grupo de Precificação é obrigatória!\n");
		}
		
		return result;
	}
	
}
