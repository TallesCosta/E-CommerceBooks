package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.SaleParameterization;
import br.com.talles.ecommercebooks.domain.Entity;

public class SaleParameterizationNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		SaleParameterization saleParameterization = (SaleParameterization) entity;
        
        if (saleParameterization.getMinSaleLimit() == 0) {
            result.addMsg("Limíte Mínimo de Venda é um campo obrigatório!\n");
		} if (saleParameterization.getMinSaleLimit() < 1) {
            result.addMsg("Limíte Mínimo de Venda deve ser, no mínimo, igual a 1!\n");
		} if (saleParameterization.getPeriodicity() == 0) {
            result.addMsg("Periodicidade é um campo obrigatório!\n");
		} if (saleParameterization.getPeriodicity() < 1) {
            result.addMsg("Periodicidade deve ser, no mínimo, igual a 1!\n");
		}
		
		return result;
	}
	
}
