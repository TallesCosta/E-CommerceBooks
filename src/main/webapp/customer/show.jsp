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
        <title>Histórico do Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<%
			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", "", 0L), 
					new User("", "", "", 0L), 
					new Address("", "", "", "", "", "", "", "", new City(0L, new State(0L, new Country(0L))), 0L), 
					new Address("", "", "", "", "", "", "", "", new City(0L, new State(0L, new Country(0L))), 0L), 
					Arrays.asList(new CreditCard("", "", "", new Date(0L), new CardCompany(0L), 0L)), 0L);
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
			<h1 id="show-customer">Histórico do Cliente</h1>
			
			<fieldset>
				<legend>Dados básicos</legend>
				<div>
					<label for="registry">CPF: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getRegistry()); %> </span>
				</div>
				<div>
					<label for="name">Nome: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getName()); %> </span>
				</div>
				<div>
					<label for="birthDate">Data Nasc.: </label>
					<span> <% out.print(dateFormat.format(((Customer) customer.getHistory().getEntity()).getBirthDate())); %> </span>
				</div>
				<div>
					<label for="gender">Gênero: </label>
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Feminino")) { out.print("checked"); } %> </span>
					<label for="female">Feminino</label>
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Masculino")) { out.print("checked"); } %> </span>
					<label for="male">Masculino</label>
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Outro")) { out.print("checked"); } %> </span>
					<label for="other">Outro</label>
				</div>
			</fieldset>

			<fieldset>
				<legend>Telefone</legend>
				<div>
					<label for="ddd">DDD: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getPhone().getDdd()); %> </span>
				</div>
				<div>
					<label for="phoneNumber">Número: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getPhone().getNumber()); %> </span>
				</div>
				<div>
					<label for="phoneType">Tipo: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getPhone().getPhoneType()); %> </span>
				</div>
			</fieldset>

			<fieldset>
				<legend>Usuário</legend>
				<div>
					<label for="email">E-mail: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getUser().getEmail()); %> </span>
				</div>
				<div>
					<label for="password">Senha: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getUser().getPassword()); %> </span>
				</div>
				<div>
					<label for="passwordVerify">Confirmaçãoo da Senha: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getUser().getPasswordVerify()); %> </span>
				</div>
			</fieldset>

			<fieldset>
				<legend>Endereço Residencial</legend>
				<div>
					<label for="homeAlias">Apelido: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getAlias()); %> </span>
				</div>
				<div>
					<label for="homeObservation">Observações: </label>
					<textarea name="homeObservation" id="homeObservation"><% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getObservation()); %></textarea>
				</div>
				<div>
					<label for="homePublicPlaceType">Tipo de Logradouro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getPublicPlaceType()); %> </span>
				</div>
				<div>
					<label for="homePublicPlace">Logradouro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getPublicPlace()); %> </span>
				</div>
				<div>
					<label for="homeNumber">Número: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getNumber()); %> </span>
				</div>
				<div>
					<label for="homeDistrict">Bairro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getDistrict()); %> </span>
				</div>
				<div>
					<label for="homePostalCode">CEP: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getPostalCode()); %> </span>
				</div>
				<div>
					<label for="homeHomeType">Tipo de Residência: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getHomeAddress().getHomeType()); %> </span>
				</div>
				<div>
					<input type="hidden" name="idHomeCity" id="idHomeCity" <% out.print("value='" + ((Customer) customer.getHistory().getEntity()).getHomeAddress().getCity().getId() + "'"); %> />
					<label for="homeCity">Cidade: </label>
					<select name="homeCity" id="homeCity">
		<%	
			for(Entity entity : result.getEntities(City.class.getSimpleName())){
				City city = (City) entity;
				out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
			}
		%>
					</select>
				</div>
				<div>
					<span> <% /*out.print("value='" + ((Customer) customer.getHistory().getEntity()).getHomeAddress().getCity().getState().getId() + "'");*/ %> </span>
					<label for="homeState">Estado: </label>
					<select name="homeState" id="homeState">
		<%	
			for(Entity entity : result.getEntities(State.class.getSimpleName())){
				State state = (State) entity;
				out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
			}
		%>
					</select>
				</div>
				<div>
					<span> <% /*out.print("value='" + ((Customer) customer.getHistory().getEntity()).getHomeAddress().getCity().getState().getCountry().getId() + "'");*/ %> </span>
					<label for="homeCountry">País: </label>
					<select name="homeCountry" id="homeCountry">
		<%	
			for(Entity entity : result.getEntities(Country.class.getSimpleName())){
				Country country = (Country) entity;
				out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
			}
		%>
					</select>
				</div>
			</fieldset>

		<%
			if (result.getOperation().equals("FIND")) {
		%>
			<fieldset>
				<legend>Endereço de Cobrança</legend>
				<div>
					<label for="chargeAlias">Apelido: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getAlias()); %> </span>
				</div>
				<div>
					<label for="chargeObservation">Observações: </label>
					<textarea name="chargeObservation" id="chargeObservation"><% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getObservation()); %></textarea>
				</div>
				<div>
					<label for="chargePublicPlaceType">Tipo de Logradouro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getPublicPlaceType()); %> </span>
				</div>
				<div>
					<label for="chargePublicPlace">Logradouro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getPublicPlace()); %> </span>
				</div>
				<div>
					<label for="chargeNumber">Número: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getNumber()); %> </span>
				</div>
				<div>
					<label for="chargeDistrict">Bairro: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getDistrict()); %> </span>
				</div>
				<div>
					<label for="chargePostalCode">CEP: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getPostalCode()); %> </span>
				</div>
				<div>
					<label for="chargeHomeType">Tipo de Residência: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getChargeAddress().getHomeType()); %> </span>
				</div>
				<div>
					<span> <% out.print("value='" + ((Customer) customer.getHistory().getEntity()).getChargeAddress().getCity().getId() + "'"); %> </span>
					<label for="chargeCity">Cidade: </label>
					<select name="chargeCity" id="chargeCity">
			<%	
				for(Entity entity : result.getEntities(City.class.getSimpleName())){
					City city = (City) entity;
					out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
				}
			%>
					</select>
				</div>
				<div>
					<span> <% /*out.print("value='" + ((Customer) customer.getHistory().getEntity()).getChargeAddress().getCity().getState().getId() + "'");*/ %> </span>
					<label for="chargeState">Estado: </label>
					<select name="chargeState" id="chargeState">
			<%	
				for(Entity entity : result.getEntities(State.class.getSimpleName())){
					State state = (State) entity;
					out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
				}
			%>
					</select>
				</div>
				<div>
					<span> <% /*out.print("value='" + ((Customer) customer.getHistory().getEntity()).getChargeAddress().getCity().getState().getCountry().getId() + "'");*/ %> </span>
					<label for="chargeCountry">País: </label>
					<select name="chargeCountry" id="chargeCountry">
			<%	
				for(Entity entity : result.getEntities(Country.class.getSimpleName())){
					Country country = (Country) entity;
					out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
				}
			%>
					</select>
				</div>
			</fieldset>
		<%
			}

			if (result.getOperation().equals("CREATE")) {
		%>				
			<fieldset>
				<legend>Cartão de Crédito</legend>
				<div>
					<label for="cardNumber">Número: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getCreditCard(0).getNumber()); %> </span>
				</div>
				<div>
					<label for="printedName">Nome Impresso: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getCreditCard(0).getPrintedName()); %> </span>
				</div>
				<div>
					<label for="securityCode">Código de Segurança: </label>
					<span> <% out.print(((Customer) customer.getHistory().getEntity()).getCreditCard(0).getSecurityCode()); %> </span>
				</div>
				<div>
					<label for="expirationDate">Data Exp.: </label>
					<span> <% out.print(dateFormat.format(((Customer) customer.getHistory().getEntity()).getCreditCard(0).getExpirationDate())); %> </span>
				</div>
				<div>
					<span> <% out.print("value='" + ((Customer) customer.getHistory().getEntity()).getCreditCard(0)
								   .getCardCompany().getId() + "'"); %> </span>
					<label for="cardCompany">Bandeira: </label>
					<select name="cardCompany" id="cardCompany">
			<%	
				for(Entity entity : result.getEntities(CardCompany.class.getSimpleName())){
					CardCompany cardCompany = (CardCompany) entity;
					out.print("<option value='" + cardCompany.getId() + "'>" + cardCompany.getName() + "</option>");
				}
			%>
					</select>
				</div>
			</fieldset>
		<%
			}
		%>

			<a href="javascript:window.history.go(-1)">Voltar</a>
		</div>
	<%
		}
	%>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
		</script>
		
		<script>
			// With the page ready, selects the options in combo-boxes
			$(function() {
				var idHomeCity = $("#idHomeCity").val();
				$("#homeCity").val(idHomeCity);
								
				var idHomeState = $("#idHomeState").val();
				$("#homeState").val(idHomeState);
				
				var idHomeCountry = $("#idHomeCountry").val();
				$("#homeCountry").val(idHomeCountry);
				
				var idChargeCity = $("#idChargeCity").val();
				$("#chargeCity").val(idChargeCity);
								
				var idChargeState = $("#idChargeState").val();
				$("#chargeState").val(idChargeState);
				
				var idChargeCountry = $("#idChargeCountry").val();
				$("#chargeCountry").val(idChargeCountry);
				
				var idCreditCard = $("#idCreditCard").val();
				$("#creditCard").val(idCreditCard);
			});
		</script>
		
    </body>
</html>
