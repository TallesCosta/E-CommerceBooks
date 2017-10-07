<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Address"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		Result result = new Result();
		result = (Result) request.getAttribute("result");
			
		if (result != null) {
	%>
    <head>
        <title>
			<% if (result.getOperation().equals("CREATE")) { out.print("Criação de Cliente"); }
			else if (result.getOperation().equals("FIND")) { out.print("Alteração de Cliente"); } %>
		</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<%
			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", ""), 
					new User("", "", ""), new Address("", "", "", "", "", "", "", ""));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
			<h1>
				<% if (result.getOperation().equals("CREATE")) { out.print("Criação de Cliente"); }
				else if (result.getOperation().equals("FIND")) { out.print("Alteração de Cliente"); } %>
			</h1>
			
			<form action="save" method="POST">
				<fieldset>
					<legend>Dados básicos</legend>
					<div>
						<label for="registry">CPF*: </label>
						<input name="registry" id="registry" value="<% out.print(customer.getRegistry()); %>" type="text">
					</div>
					<div>
						<label for="name">Nome*: </label>
						<input name="name" id="name" value="<% out.print(customer.getName()); %>" type="text">
					</div>
					<div>
						<label for="birthDate">Data Nasc.*: </label>
						<input name="birthDate" id="birthDate" 
							   value="<% out.print(dateFormat.format(customer.getBirthDate())); %>" type="date">
					</div>
					<div>
						<label for="gender">Gênero*: </label>
						<input <% if (customer.getGender().getName().equals("Feminino")) { out.print("checked"); } %> 
							name="gender" id="female" value="Feminino" type="radio">
						<label for="female">Feminino</label>
						<input <% if (customer.getGender().getName().equals("Masculino")) { out.print("checked"); } %> 
							name="gender" id="male" value="Masculino" type="radio">
						<label for="male">Masculino</label>
						<input <% if (customer.getGender().getName().equals("Outro")) { out.print("checked"); } %> 
							name="gender" id="other" value="Outro" type="radio">
						<label for="other">Outro</label>
					</div>
				</fieldset>

				<fieldset>
					<legend>Telefone</legend>
					<div>
						<label for="ddd">DDD*: </label>
						<input name="ddd" id="ddd" value="<% out.print(customer.getPhone().getDdd()); %>" type="text">
					</div>
					<div>
						<label for="phoneNumber">Número*: </label>
						<input name="phoneNumber" id="phoneNumber" 
							   value="<% out.print(customer.getPhone().getNumber()); %>" type="text">
					</div>
					<div>
						<label for="phoneType">Tipo*: </label>
						<input name="phoneType" id="phoneType" 
							   value="<% out.print(customer.getPhone().getPhoneType()); %>" type="text">
					</div>
				</fieldset>

				<fieldset>
					<legend>Usuário</legend>
					<div>
						<label for="email">E-mail*: </label>
						<input name="email" id="email" value="<% out.print(customer.getUser().getEmail()); %>" type="email">
					</div>
					<div>
						<label for="password">Senha*: </label>
						<input name="password" id="password" 
							   value="<% out.print(customer.getUser().getPassword()); %>" type="password">
					</div>
					<div>
						<label for="passwordVerify">Confirmaçãoo da Senha*: </label>
						<input name="passwordVerify" id="passwordVerify" 
							   value="<% out.print(customer.getUser().getPasswordVerify()); %>" type="password">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Endereço Residencial</legend>
					<div>
						<label for="homeAlias">Apelido*: </label>
						<input name="homeAlias" id="homeAlias" 
							   value="<% out.print(customer.getHomeAddress().getAlias()); %>" type="text">
					</div>
					<div>
						<label for="homeObservation">Observações: </label>
						<textarea name="homeObservation" id="homeObservation">
							<% out.print(customer.getHomeAddress().getObservation()); %></textarea>
					</div>
					<div>
						<label for="homePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="homePublicPlaceType" id="homePublicPlaceType" 
							   value="<% out.print(customer.getHomeAddress().getPublicPlaceType()); %>" type="text" 
							   placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="homePublicPlace">Logradouro*: </label>
						<input name="homePublicPlace" id="homePublicPlace" 
							   value="<% out.print(customer.getHomeAddress().getPublicPlace()); %>" type="text">
					</div>
					<div>
						<label for="homeNumber">Número*: </label>
						<input name="homeNumber" id="homeNumber" 
							   value="<% out.print(customer.getHomeAddress().getNumber()); %>" type="text">
					</div>
					<div>
						<label for="homeDistrict">Bairro*: </label>
						<input name="homeDistrict" id="homeDistrict" 
							   value="<% out.print(customer.getHomeAddress().getDistrict()); %>" type="text">
					</div>
					<div>
						<label for="homePostalCode">CEP*: </label>
						<input name="homePostalCode" id="homePostalCode" 
							   value="<% out.print(customer.getHomeAddress().getPostalCode()); %>" type="text">
					</div>
					<div>
						<label for="homeHomeType">Tipo de Residência*: </label>
						<input name="homeHomeType" id="homeHomeType" 
							   value="<% out.print(customer.getHomeAddress().getHomeType()); %>" type="text" 
							   placeholder="Casa, Apartamento, etc.">
					</div>
					<div>
						<label for="homeCity">Cidade*: </label>
						<select name="homeCity" id="homeCity">
		<%	
			for(Entity entity : result.getEntities(City.class.getSimpleName())){
				City city = (City) entity;
				out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
			}
		%>
						<select>
					</div>
					<div>
						<label for="homeState">Estado*: </label>
						<select name="homeState" id="homeState">
		<%	
			for(Entity entity : result.getEntities(State.class.getSimpleName())){
				State state = (State) entity;
				out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
			}
		%>
						<select>
					</div>
					<div>
						<label for="homeCountry">País*: </label>
						<select name="homeCountry" id="homeCountry">
		<%	
			for(Entity entity : result.getEntities(Country.class.getSimpleName())){
				Country country = (Country) entity;
				out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
			}
		%>
						<select>
						</div>
				</fieldset>
				
		<%
			if (result.getOperation().equals("FIND")) {
		%>
				<fieldset>
					<legend>Endereço de Cobrança</legend>
					<div>
						<label for="chargeAlias">Apelido*: </label>
						<input name="chargeAlias" id="chargeAlias" 
							   value="<% out.print(customer.getChargeAddress().getAlias()); %>" type="text">
					</div>
					<div>
						<label for="chargeObservation">Observações: </label>
						<textarea name="chargeObservation" id="chargeObservation">
							<% out.print(customer.getChargeAddress().getObservation()); %></textarea>
					</div>
					<div>
						<label for="chargePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="chargePublicPlaceType" id="chargePublicPlaceType" 
							   value="<% out.print(customer.getChargeAddress().getPublicPlaceType()); %>" type="text" 
							   placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="chargePublicPlace">Logradouro*: </label>
						<input name="chargePublicPlace" id="chargePublicPlace" 
							   value="<% out.print(customer.getChargeAddress().getPublicPlace()); %>" type="text">
					</div>
					<div>
						<label for="chargeNumber">Número*: </label>
						<input name="chargeNumber" id="chargeNumber" 
							   value="<% out.print(customer.getChargeAddress().getNumber()); %>" type="text">
					</div>
					<div>
						<label for="chargeDistrict">Bairro*: </label>
						<input name="chargeDistrict" id="chargeDistrict" 
							   value="<% out.print(customer.getChargeAddress().getDistrict()); %>" type="text">
					</div>
					<div>
						<label for="chargePostalCode">CEP*: </label>
						<input name="chargePostalCode" id="chargePostalCode" 
							   value="<% out.print(customer.getChargeAddress().getPostalCode()); %>" type="text">
					</div>
					<div>
						<label for="chargeHomeType">Tipo de Residência*: </label>
						<input name="chargeHomeType" id="chargeHomeType" 
							   value="<% out.print(customer.getChargeAddress().getHomeType()); %>" type="text" 
							   placeholder="Casa, Apartamento, etc.">
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
		<%
			}

			if (result.getOperation().equals("CREATE")) {
		%>				
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
						<input name="securityCode" id="securityCode" type="text">
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
		<%
			}
		%>

				<button name="operation" id="save-custumer" value="SAVE" type="submit">Salvar</button>
				<small>Todos os campos marcados com * são obrigatórios.</small>
			</form>
		</div>
	<%
		}
	%>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
