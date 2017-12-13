<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.*" %>
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
		
		<div class="container">
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
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Feminino")) { %> <label for="female">Feminino</label> <% } %> </span>
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Masculino")) { %> <label for="male">Masculino</label> <% } %> </span>
					<span><% if (((Customer) customer.getHistory().getEntity()).getGender().getName().equals("Outro")){ %> <label for="other">Outro</label> <% } %> </span>
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
			</fieldset>

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
