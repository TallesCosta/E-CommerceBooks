package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;

public class DimensionNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Dimension dimension = (Dimension) entity;
        
        if (dimension.getHeight() == 0.0) {
            result.addMsg("Altura é um campo obrigatório!\n");
		} if (dimension.getWidht() == 0.0) {
            result.addMsg("Largura é um campo obrigatório!\n");
		} if (dimension.getWeight() == 0.0) {
            result.addMsg("Peso é um campo obrigatório!\n");
		} if (dimension.getDepth() == 0.0) {
            result.addMsg("Profundidade é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}
