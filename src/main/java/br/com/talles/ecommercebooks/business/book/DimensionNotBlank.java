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
		} if (dimension.getHeight() < 0.01 && dimension.getHeight() > 100.0) {
            result.addMsg("Altura deve estar entre 0,01 cm e 100,0 cm!\n");
		} if (dimension.getWidht() == 0.0) {
            result.addMsg("Largura é um campo obrigatório!\n");
		} if (dimension.getWidht() < 0.01 && dimension.getWidht() > 100.0) {
            result.addMsg("Largura deve estar entre 0,01 cm e 100,0 cm!\n");
		} if (dimension.getWeight() == 0.0) {
            result.addMsg("Peso é um campo obrigatório!\n");
		} if (dimension.getWeight() < 0.001 && dimension.getWeight() > 10.0) {
            result.addMsg("Peso deve estar entre 0,001 cm e 10,0 cm!\n");
		} if (dimension.getDepth() == 0.0) {
            result.addMsg("Profundidade é um campo obrigatório!\n");
		} if (dimension.getDepth() < 0.01 && dimension.getDepth() > 100.0) {
            result.addMsg("Profundidade deve estar entre 0,01 cm e 100,0 cm!\n");
		}
		
		return result;
	}
	
}
