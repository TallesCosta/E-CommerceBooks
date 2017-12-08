package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;

public class AddressNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Address address = (Address) entity;
				
		if (address.getAlias() == null || address.getAlias().trim().isEmpty()) {
            result.addMsg("Apelido do endereço é um campo obrigatório!\n");
		} if (address.getPublicPlaceType() == null || address.getPublicPlaceType().trim().isEmpty()) {
            result.addMsg("Tipo de logradouro é um campo obrigatório!\n");
		} if (address.getPublicPlace() == null || address.getPublicPlace().trim().isEmpty()) {
            result.addMsg("Logradouro é um campo obrigatório!\n");
		} if (address.getNumber() == null || address.getPublicPlace().trim().isEmpty()) {
            result.addMsg("Número do endereço é um campo obrigatório!\n");
		} if (address.getDistrict() == null || address.getPublicPlace().trim().isEmpty()) {
            result.addMsg("Bairro é um campo obrigatório!\n");
		} if (address.getPostalCode() == null || address.getPublicPlace().trim().isEmpty()) {
            result.addMsg("CEP é um campo obrigatório!\n");
		} if (address.getCity() == null || address.getCity().trim().isEmpty()) {
			result.addMsg("Cidate é um campo obrigatório!\n");
		} if (address.getState().getId() == 0L) {
            result.addMsg("A escolha de um estado é obrigatório!\n");
		} if (address.getState().getCountry().getId() == 0L) {
			result.addMsg("A escolha de um país é obrigatório!\n");
		}
		
		return result;
	}
	
}
