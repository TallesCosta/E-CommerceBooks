<%@page import="br.com.talles.ecommercebooks.domain.customer.CreditCard"%>
<%@page import="java.util.Arrays"%>
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
			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", "", 0L), 
					new User("", "", "", 0L), new Address("", "", "", "", "", "", "", "", 0L), 
					Arrays.asList(new CreditCard("", "", "", new Date(0L), new CardCompany(), 0L)), 0L);
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
			
			<form 
				<% if (result.getOperation().equals("CREATE")) { out.print("action='save'"); }
				else if (result.getOperation().equals("FIND")) { out.print("action='update'"); } %>
				method="POST">
				
				<fieldset>
					<legend>Dados básicos</legend>
					<input type="hidden" name="id" id="id" 
						   <% out.print("value='" + customer.getId() + "'"); %> >
					<div>
						<label for="registry">CPF*: </label>
						<input name="registry" id="registry" type="text"
							   value="<% out.print(customer.getRegistry()); %>">
					</div>
					<div>
						<label for="name">Nome*: </label>
						<input name="name" id="name" type="text" 
							   value="<% out.print(customer.getName()); %>">
					</div>
					<div>
						<label for="birthDate">Data Nasc.*: </label>
						<input name="birthDate" id="birthDate" type="date"
							   value="<% out.print(dateFormat.format(customer.getBirthDate())); %>">
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
					<input type="hidden" name="idPhone" id="idPhone" 
						   <% out.print("value='" + customer.getPhone().getId() + "'"); %> >
					<div>
						<label for="ddd">DDD*: </label>
						<input name="ddd" id="ddd" type="text" 
							   value="<% out.print(customer.getPhone().getDdd()); %>">
					</div>
					<div>
						<label for="phoneNumber">Número*: </label>
						<input name="phoneNumber" id="phoneNumber" type="text"
							   value="<% out.print(customer.getPhone().getNumber()); %>">
					</div>
					<div>
						<label for="phoneType">Tipo*: </label>
						<input name="phoneType" id="phoneType" type="text"
							   value="<% out.print(customer.getPhone().getPhoneType()); %>">
					</div>
				</fieldset>

				<fieldset>
					<legend>Usuário</legend>
					<input type="hidden" name="idUser" id="idUser" 
						   <% out.print("value='" + customer.getUser().getId() + "'"); %> >
					<div>
						<label for="email">E-mail*: </label>
						<input name="email" id="email" type="email" 
							   value="<% out.print(customer.getUser().getEmail()); %>">
					</div>
					<div>
						<label for="password">Senha*: </label>
						<input name="password" id="password" type="password"
							   value="<% out.print(customer.getUser().getPassword()); %>">
					</div>
					<div>
						<label for="passwordVerify">Confirmaçãoo da Senha*: </label>
						<input name="passwordVerify" id="passwordVerify" type="password"
							   value="<% out.print(customer.getUser().getPasswordVerify()); %>">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Endereço Residencial</legend>
					<input type="hidden" name="idHome" id="idHome" 
						   <% out.print("value='" + customer.getHomeAddress().getId() + "'"); %> >
					<div>
						<label for="homeAlias">Apelido*: </label>
						<input name="homeAlias" id="homeAlias" type="text"
							   value="<% out.print(customer.getHomeAddress().getAlias()); %>">
					</div>
					<div>
						<label for="homeObservation">Observações: </label>
						<textarea name="homeObservation" id="homeObservation">
							<% out.print(customer.getHomeAddress().getObservation()); %></textarea>
					</div>
					<div>
						<label for="homePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="homePublicPlaceType" id="homePublicPlaceType" type="text"
							   value="<% out.print(customer.getHomeAddress().getPublicPlaceType()); %>"
							   placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="homePublicPlace">Logradouro*: </label>
						<input name="homePublicPlace" id="homePublicPlace" type="text"
							   value="<% out.print(customer.getHomeAddress().getPublicPlace()); %>">
					</div>
					<div>
						<label for="homeNumber">Número*: </label>
						<input name="homeNumber" id="homeNumber" type="text"
							   value="<% out.print(customer.getHomeAddress().getNumber()); %>">
					</div>
					<div>
						<label for="homeDistrict">Bairro*: </label>
						<input name="homeDistrict" id="homeDistrict" type="text"
							   value="<% out.print(customer.getHomeAddress().getDistrict()); %>">
					</div>
					<div>
						<label for="homePostalCode">CEP*: </label>
						<input name="homePostalCode" id="homePostalCode" type="text"
							   value="<% out.print(customer.getHomeAddress().getPostalCode()); %>">
					</div>
					<div>
						<label for="homeHomeType">Tipo de Residência*: </label>
						<input name="homeHomeType" id="homeHomeType" type="text"
							   value="<% out.print(customer.getHomeAddress().getHomeType()); %>"
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
					<input type="hidden" name="idCharge" id="idCharge" 
						   <% out.print("value='" + customer.getChargeAddress().getId() + "'"); %> >
					<div>
						<label for="chargeAlias">Apelido*: </label>
						<input name="chargeAlias" id="chargeAlias" type="text"
							   value="<% out.print(customer.getChargeAddress().getAlias()); %>">
					</div>
					<div>
						<label for="chargeObservation">Observações: </label>
						<textarea name="chargeObservation" id="chargeObservation">
							<% out.print(customer.getChargeAddress().getObservation()); %></textarea>
					</div>
					<div>
						<label for="chargePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="chargePublicPlaceType" id="chargePublicPlaceType" type="text"
							   value="<% out.print(customer.getChargeAddress().getPublicPlaceType()); %>"
							   placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="chargePublicPlace">Logradouro*: </label>
						<input name="chargePublicPlace" id="chargePublicPlace" type="text"
							   value="<% out.print(customer.getChargeAddress().getPublicPlace()); %>">
					</div>
					<div>
						<label for="chargeNumber">Número*: </label>
						<input name="chargeNumber" id="chargeNumber" type="text"
							   value="<% out.print(customer.getChargeAddress().getNumber()); %>">
					</div>
					<div>
						<label for="chargeDistrict">Bairro*: </label>
						<input name="chargeDistrict" id="chargeDistrict" type="text"
							   value="<% out.print(customer.getChargeAddress().getDistrict()); %>">
					</div>
					<div>
						<label for="chargePostalCode">CEP*: </label>
						<input name="chargePostalCode" id="chargePostalCode" type="text"
							   value="<% out.print(customer.getChargeAddress().getPostalCode()); %>">
					</div>
					<div>
						<label for="chargeHomeType">Tipo de Residência*: </label>
						<input name="chargeHomeType" id="chargeHomeType" type="text"
							   value="<% out.print(customer.getChargeAddress().getHomeType()); %>" 
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
						<input name="cardNumber" id="cardNumber" type="text"
							   value="<% out.print(customer.getCreditCard().get(0).getNumber()); %>">
					</div>
					<div>
						<label for="printedName">Nome Impresso*: </label>
						<input name="printedName" id="printedName" type="text"
							   value="<% out.print(customer.getCreditCard().get(0).getPrintedName()); %>">
					</div>
					<div>
						<label for="securityCode">Código de Segurança*: </label>
						<input name="securityCode" id="passwordVerify" type="securityCode"
							   value="<% out.print(customer.getCreditCard().get(0).getSecurityCode()); %>">
					</div>
					<div>
						<label for="expirationDate">Data Exp.*: </label>
						<input name="expirationDate" id="expirationDate" type="date"
							   value="<% out.print(dateFormat.format(customer.getCreditCard().get(0).getExpirationDate())); %>"
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

				<button name="operation"
						<% if (result.getOperation().equals("CREATE")) { out.print("value='SAVE'"); }
							else if (result.getOperation().equals("FIND")) { out.print("value='UPDATE'"); } %>
						type="submit">Salvar</button>
				<small>Todos os campos marcados com * são obrigatórios.</small>
			</form>
		</div>
	<%
		}
	%>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
