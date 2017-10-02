<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.CardCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Country"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.State"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.City"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.User"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Phone"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Gender"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Customer"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Criação de Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");
			
			if (result != null) {
				Customer customer = new Customer("", "", new Date(), new Gender(), new Phone("", "", ""), new User("", "", ""));
				
				if (result.getKeys().contains(Customer.class.getSimpleName())) {
					customer = (Customer) result.getEntities(Customer.class.getSimpleName()).get(0);
				}
				
				if (result.hasMsg()) {
					String[] msgs = result.getMsg().split("\n");
					out.print("<p>");
					for(String msg : msgs)
						out.print("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
					out.print("</p>");
				}
		%>
		
		<div id="app">
			<h1>Criação de Cliente</h1>
			
			<form action="save" method="POST">
				<fieldset>
					<legend>Dados básicos</legend>
					<div>
						<label for="registry">CPF*: </label>
						<input name="registry" id="registry" type="text">
					</div>
					<div>
						<label for="name">Nome*: </label>
						<input name="name" id="name" type="text">
					</div>
					<div>
						<label for="birthDate">Data Nasc.*: </label>
						<input name="birthDate" id="birthDate" type="date">
					</div>
					<div>
						<label for="gender">Gênero*: </label>
						<input name="gender" id="gender" value="Feminino" type="radio">
						<label for="gender">Feminino</label>
						<input name="gender" id="gender" value="Masculino" type="radio">
						<label for="gender">Masculino</label>
						<input name="gender" id="gender" value="Outro" type="radio">
						<label for="gender">Outro</label>
					</div>
				</fieldset>

				<fieldset>
					<legend>Telefone</legend>
					<div>
						<label for="ddd">DDD*: </label>
						<input name="ddd" id="ddd" type="number">
					</div>
					<div>
						<label for="phoneNumber">Número*: </label>
						<input name="phoneNumber" id="phoneNumber" type="text">
					</div>
					<div>
						<label for="phoneType">Tipo*: </label>
						<input name="phoneType" id="phoneType" type="text">
					</div>
				</fieldset>

				<fieldset>
					<legend>Usuário</legend>
					<div>
						<label for="email">E-mail*: </label>
						<input name="email" id="email" type="email">
					</div>
					<div>
						<label for="password">Senha*: </label>
						<input name="password" id="password" type="password">
					</div>
					<div>
						<label for="passwordVerify">Confirmaçãoo da Senha*: </label>
						<input name="passwordVerify" id="passwordVerify" type="password">
					</div>
				</fieldset>

				<fieldset>
					<legend>Endereço de Entrega</legend>
					<div>
						<label for="deliveryAlias">Apelido*: </label>
						<input name="deliveryAlias" id="deliveryAlias" type="text">
					</div>
					<div>
						<label for="deliveryObservation">Observações: </label>
						<textarea name="deliveryObservation" id="deliveryObservation"></textarea>
					</div>
					<div>
						<label for="deliveryPublicPlaceType">Tipo de Logradouro*: </label>
						<input name="deliveryPublicPlaceType" id="deliveryPublicPlaceType" type="text" placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="deliveryPublicPlace">Logradouro*: </label>
						<input name="deliveryPublicPlace" id="deliveryPublicPlace" type="text">
					</div>
					<div>
						<label for="deliveryNumber">Número*: </label>
						<input name="deliveryNumber" id="deliveryNumber" type="text">
					</div>
					<div>
						<label for="deliveryDistrict">Bairro*: </label>
						<input name="deliveryDistrict" id="deliveryDistrict" type="text">
					</div>
					<div>
						<label for="deliveryPostalCode">CEP*: </label>
						<input name="deliveryPostalCode" id="deliveryPostalCode" type="text">
					</div>
					<div>
						<label for="deliveryHomeType">Tipo de Residência*: </label>
						<input name="deliveryHomeType" id="deliveryHomeType" type="text" placeholder="Casa, Apartamento, etc.">
					</div>
					<div>
						<label for="deliveryCity">Cidade*: </label>
						<select name="deliveryCity" id="deliveryCity">
			<%	
				for(Entity entity : result.getEntities(City.class.getSimpleName())){
					City city = (City) entity;
					out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
				}
			%>
						<select>
					</div>
					<div>
						<label for="deliveryState">Estado*: </label>
						<select name="deliveryState" id="deliveryState">
			<%	
				for(Entity entity : result.getEntities(State.class.getSimpleName())){
					State state = (State) entity;
					out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
				}
			%>
						<select>
					</div>
					<div>
						<label for="deliveryCountry">País*: </label>
						<select name="deliveryCountry" id="deliveryCountry">
			<%	
				for(Entity entity : result.getEntities(Country.class.getSimpleName())){
					Country country = (Country) entity;
					out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
				}
			%>
						<select>
						</div>
				</fieldset>

				<fieldset>
					<legend>Endereço de Cobrança</legend>
					<div>
						<label for="chargeAlias">Apelido*: </label>
						<input name="chargeAlias" id="chargeAlias" type="text">
					</div>
					<div>
						<label for="chargeObservation">Observações: </label>
						<textarea name="chargeObservation" id="chargeObservation"></textarea>
					</div>
					<div>
						<label for="chargePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="chargePublicPlaceType" id="chargePublicPlaceType" type="text" placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="chargePublicPlace">Logradouro*: </label>
						<input name="chargePublicPlace" id="chargePublicPlace" type="text">
					</div>
					<div>
						<label for="chargeNumber">Número*: </label>
						<input name="chargeNumber" id="chargeNumber" type="text">
					</div>
					<div>
						<label for="chargeDistrict">Bairro*: </label>
						<input name="chargeDistrict" id="chargeDistrict" type="text">
					</div>
					<div>
						<label for="chargePostalCode">CEP*: </label>
						<input name="chargePostalCode" id="chargePostalCode" type="text">
					</div>
					<div>
						<label for="chargeHomeType">Tipo de Residência*: </label>
						<input name="chargeHomeType" id="chargeHomeType" type="text" placeholder="Casa, Apartamento, etc.">
					</div>
					<div>
						<label for="chargeCity">Cidade*: </label>
						<select name="chargeCity" id="chargeCity">
			<%	
				for(Entity entity : result.getEntities(City.class.getSimpleName())){
					City city = (City) entity;
					out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
				}
			%>
						<select>
					</div>
					<div>
						<label for="chargeState">Estado*: </label>
						<select name="chargeState" id="chargeState">
			<%	
				for(Entity entity : result.getEntities(State.class.getSimpleName())){
					State state = (State) entity;
					out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
				}
			%>
						<select>
					</div>
					<div>
						<label for="chargeCountry">País*: </label>
						<select name="chargeCountry" id="chargeCountry">
			<%	
				for(Entity entity : result.getEntities(Country.class.getSimpleName())){
					Country country = (Country) entity;
					out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
				}
			%>
						<select>
					</div>
				</fieldset>

				<fieldset>
					<legend>Cartão de Crédito</legend>
					<div>
						<label for="cardNumber">Número*: </label>
						<input name="cardNumber" id="cardNumber" type="text">
					</div>
					<div>
						<label for="printedName">Nome Impresso*: </label>
						<input name="printedName" id="printedName" type="text">
					</div>
					<div>
						<label for="securityCode">Código de Segurança*: </label>
						<input name="securityCode" id="passwordVerify" type="securityCode">
					</div>
					<div>
						<label for="expirationDate">Data Exp.*: </label>
						<input name="expirationDate" id="expirationDate" type="date">
					</div>
					<div>
						<label for="cardCompany">Bandeira*: </label>
						<select name="cardCompany" id="cardCompany">
			<%	
				for(Entity entity : result.getEntities(CardCompany.class.getSimpleName())){
					CardCompany cardCompany = (CardCompany) entity;
					out.print("<option value='" + cardCompany.getId() + "'>" + cardCompany.getName() + "</option>");
				}
			%>
						<select>
					</div>
				</fieldset>

				<button name="operation" value="SAVE" type="submit">Salvar</button>
				<small>Todos os campos marcados com * são obrigatórios.</small>
			</form>
		</div>
		<%
			}
		%>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
