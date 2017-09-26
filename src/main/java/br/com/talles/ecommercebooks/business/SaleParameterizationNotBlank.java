package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.SaleParameterization;
import br.com.talles.ecommercebooks.domain.Entity;

public class SaleParameterizationNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		SaleParameterization saleParameterization = (SaleParameterization) entity;
        
        if (saleParameterization.getMinSaleLimit() == 0) {
            result.addMsg("Limíte Mínimo de Venda é um campo obrigatório!\n");
		} if (saleParameterization.getPeriodicity() == 0) {
            result.addMsg("Periodicidade é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}
