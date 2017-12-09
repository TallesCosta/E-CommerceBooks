<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		Result result = (Result) request.getAttribute("result");
			
		if (result != null) {
	%>
    <head>
        <title>
			<% if (result.getTransaction().getOperation().equals("CREATE")) { out.print("Criação de Cliente"); }
			else if (result.getTransaction().getOperation().equals("FIND")) { out.print("Alteração de Cliente"); } %>
		</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<%@include file="../commons/admin/menu-css.jsp"%>
    </head>
    <body>
		<%
			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", "", 0L), new User("", "", "", 0L),
					Arrays.asList(new ChargeAddress("", "", "", "", "", "", "", "", "", new State(0L, new Country(0L)), 0L)),
					Arrays.asList(new DeliveryAddress("", "", "", "", "", "", "", "", "", new State(0L, new Country(0L)), 0L)),
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
		<%@include file="../commons/admin/menu-html.jsp"%>
		
		<div class="container">
			<h1 id="create-customer">
				<% if (result.getTransaction().getOperation().equals("CREATE")) { out.print("Criação de Cliente"); }
				else if (result.getTransaction().getOperation().equals("FIND")) { out.print("Alteração de Cliente"); } %>
			</h1>
			
			<form 
				<% if (result.getTransaction().getOperation().equals("CREATE")) { out.print("action='save'"); }
				else if (result.getTransaction().getOperation().equals("FIND")) { out.print("action='update'"); } %>
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
						<label class="label-inline" for="gender">Gênero*: </label>

						<input <% if (customer.getGender().getName().equals("Feminino")) { out.print("checked"); } %> 
							name="gender" id="female" value="Feminino" type="radio">
						<label class="label-inline" for="female">Feminino</label>

						<input <% if (customer.getGender().getName().equals("Masculino")) { out.print("checked"); } %>
							name="gender" id="male" value="Masculino" type="radio">
						<label class="label-inline" for="male">Masculino</label>

						<input <% if (customer.getGender().getName().equals("Outro")) { out.print("checked"); } %>
							name="gender" id="other" value="Outro" type="radio">
						<label class="label-inline" for="other">Outro</label>
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

			<%
				if (!result.getTransaction().getOperation().equals("FIND")) {
			%>
				<fieldset>
					<legend>Endereço</legend>
					<input type="hidden" name="idHome" id="idHome" >
					<div>
						<label for="homeAlias">Apelido*: </label>
						<input name="homeAlias" id="homeAlias" type="text">
					</div>
					<div>
						<label for="homeObservation">Observações: </label>
						<textarea name="homeObservation" id="homeObservation"></textarea>
					</div>
					<div>
						<label for="homePublicPlaceType">Tipo de Logradouro*: </label>
						<input name="homePublicPlaceType" id="homePublicPlaceType" type="text"
							   placeholder="Rua, Av., Tr., etc.">
					</div>
					<div>
						<label for="homePublicPlace">Logradouro*: </label>
						<input name="homePublicPlace" id="homePublicPlace" type="text" >
					</div>
					<div>
						<label for="homeNumber">Número*: </label>
						<input name="homeNumber" id="homeNumber" type="text" >
					</div>
					<div>
						<label for="homeDistrict">Bairro*: </label>
						<input name="homeDistrict" id="homeDistrict" type="text" >
					</div>
					<div>
						<label for="homePostalCode">CEP*: </label>
						<input name="homePostalCode" id="homePostalCode" type="text" >
					</div>
					<div>
						<label for="homeHomeType">Tipo de Residência*: </label>
						<input name="homeHomeType" id="homeHomeType" type="text"
							   placeholder="Casa, Apartamento, etc.">
					</div>
					<div>
						<label for="homeCity">Cidade*: </label>
						<input name="homeCity" id="homeCity" type="text" >
					</div>
					<div>
						<input type="hidden" name="idHomeState" id="idHomeState" >
						<label for="homeState">Estado*: </label>
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
						<input type="hidden" name="idHomeCountry" id="idHomeCountry" >
						<label for="homeCountry">País*: </label>
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
			}

			if (result.getTransaction().getOperation().equals("CREATE")) {
		%>				
				<fieldset>
					<legend>Cartão de Crédito</legend>
					<div>
						<label for="cardNumber">Número*: </label>
						<input name="cardNumber" id="cardNumber" type="text" >
					</div>
					<div>
						<label for="printedName">Nome Impresso*: </label>
						<input name="printedName" id="printedName" type="text" >
					</div>
					<div>
						<label for="securityCode">Código de Segurança*: </label>
						<input name="securityCode" id="securityCode" type="text" >
					</div>
					<div>
						<label for="expirationDate">Data Exp.*: </label>
						<input name="expirationDate" id="expirationDate" type="date" />
					</div>
					<div>
						<input type="hidden" name="idCardCompany" id="idCardCompany" >
						<label for="cardCompany">Bandeira*: </label>
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

				<input type="hidden" name="operation" id="operation-custumer" 
					<% if (result.getTransaction().getOperation().equals("CREATE")) { out.print("value='SAVE'"); }
					else if (result.getTransaction().getOperation().equals("FIND")) { out.print("value='UPDATE'"); } %> />
		
				<button type="submit">Salvar</button>
				<small>Todos os campos marcados com * são obrigatórios.</small>
			</form>
		</div>
	<%
		}
	%>

		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
		</script>
		
		<script>
			// With the page ready, selects the options in combo-boxes
			$(function() {
				var idHomeState = $("#idHomeState").val();
				$("#homeState").val(idHomeState);
				
				var idHomeCountry = $("#idHomeCountry").val();
				$("#homeCountry").val(idHomeCountry);
				
				var idCreditCard = $("#idCreditCard").val();
				$("#creditCard").val(idCreditCard);
			});
		</script>
		
    </body>
</html>
