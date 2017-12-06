<%@page import="br.com.talles.ecommercebooks.domain.customer.DeliveryAddress"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Country"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.State"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.City"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Customer"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<title>Listagem de Clientes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

		<%@include file="../commons/admin/form-css.jsp"%>

		<%@include file="../commons/admin/menu-css.jsp"%>
    </head>
    <body>
		<%@include file="../commons/admin/menu-html.jsp"%>
		<%
			Result result = (Result) request.getAttribute("result");

			if (result != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				
				if (result.hasMsg()) {
					String[] msgs = result.getMsg().split("\n");
					out.println("<p>");
					for(String msg : msgs)
						out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
					out.println("</p>");
				}
		%>
		
		<div class="container">
			<h1 id="list-customer">Listagem de Clientes</h1>

			<form action="list" method="POST">
				<legend>Dados da filtragem</legend>
				<div class="row form-container">
					<fieldset class="column">
						<legend>Dados básicos</legend>
						<label for="registry">CPF: </label>
						<input name="registry" id="registry" type="text" >
						<label for="name">Nome: </label>
						<input name="name" id="name" type="text" >
						<label class="label-inline" for="birthDate">Data Nasc.: </label>
						<input name="birthDate" id="birthDate" type="date" >

						<fieldset>
							<legend>Gênero:</legend>

							<input name="gender" id="female" value="Feminino" type="radio">
							<label class="label-inline" for="female">Feminino</label>

							<input name="gender" id="male" value="Masculino" type="radio">
							<label class="label-inline" for="male">Masculino</label>

							<input name="gender" id="other" value="Outro" type="radio">
							<label class="label-inline" for="other">Outro</label>
						</fieldset>

						<fieldset>
							<legend>Usuário</legend>
							<input type="hidden" name="idUser" id="idUser" >
							<label for="email">E-mail: </label>
							<input name="email" id="email" type="email" >
							<label for="password">Senha: </label>
							<input name="password" id="password" type="password" >
						</fieldset>
					</fieldset>
					<fieldset class="column">
						<legend>Telefone</legend>
						<label for="ddd">DDD: </label>
						<input name="ddd" id="ddd" type="text" >
						<label for="phoneNumber">Número: </label>
						<input name="phoneNumber" id="phoneNumber" type="text" >
						<label for="phoneType">Tipo: </label>
						<input name="phoneType" id="phoneType" type="text" >
					</fieldset>
				</div>

				<!--
				<div class="row form-container">
					<div class="column">
						<legend>Endereço Residencial</legend>
						<label for="homeAlias">Apelido: </label>
						<input name="homeAlias" id="homeAlias" type="text" >

						<label for="homePublicPlaceType">Tipo de Logradouro: </label>
						<input name="homePublicPlaceType" id="homePublicPlaceType" type="text" placeholder="Rua, Av., Tr., etc.">

						<label for="homePublicPlace">Logradouro: </label>
						<input name="homePublicPlace" id="homePublicPlace" type="text" >

						<label for="homePostalCode">CEP: </label>
						<input name="homePostalCode" id="homePostalCode" type="text" >

						<label for="homeNumber">Número: </label>
						<input name="homeNumber" id="homeNumber" type="text" >

						<label for="homeHomeType">Tipo de Residência: </label>
						<input name="homeHomeType" id="homeHomeType" type="text" placeholder="Casa, Apartamento, etc.">
					</div>
					<div class="column">
						<label for="homeCity">Cidade: </label>
						<select name="homeCity" id="homeCity">
							<%/*
								for(Entity entity : result.getEntities(City.class.getSimpleName())){
									City city = (City) entity;
									out.print("<option value='" + city.getId() + "'>" + city.getName() + "</option>");
								}
							*/%>
						</select>

						<label for="homeState">Estado: </label>
						<select name="homeState" id="homeState">
							<%/*
								for(Entity entity : result.getEntities(State.class.getSimpleName())){
									State state = (State) entity;
									out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
								}
							*/%>
						</select>

						<label for="homeCountry">País: </label>
						<select name="homeCountry" id="homeCountry">
							<%/*
								for(Entity entity : result.getEntities(Country.class.getSimpleName())){
									Country country = (Country) entity;
									out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
								}
							*/%>
						</select>
						<label for="homeDistrict">Bairro: </label>
						<input name="homeDistrict" id="homeDistrict" type="text" >

						<label for="homeObservation">Observações: </label>
						<textarea name="homeObservation" id="homeObservation"></textarea>
					</div>
				</div>
				-->

				<button class="float-right" name="operation" value="LIST" type="submit">Filtrar</button>
			</form>

			<div class="row">
				<div class="column">
					<table class='u-full-width'>
						<thead>
						<tr>
							<td>CPF</td>
							<td>Nome</td>
							<td>Data de Nasc.</td>
							<td>Gênero</td>
							<td>Telefone</td>
							<td>E-mail</td>
							<td>End. Residencial</td>
							<td>End. Cobrança</td>
							<td>End(s). Entrega</td>
							<td>Editar</td>
							<td>Excluir</td>
							<td>Histórico</td>
						</tr>
						</thead>

						<tbody>
						<%
							int i = 0;
							if(result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())){
								for(Entity entity : result.getEntities(Customer.class.getSimpleName())){
									Customer customer = (Customer) entity;

									String deliveryAddresses = "";

									Set<DeliveryAddress> noRepeat = new HashSet<>(customer.getDeliveryAddresses());
									for(DeliveryAddress deliveryAddress : noRepeat){
										deliveryAddresses += deliveryAddress.getAlias()+ ", ";
									}

									out.println("<tr>");
									out.println("<td>" + customer.getRegistry() + "</td>");
									out.println("<td>" + customer.getName() + "</td>");
									out.println("<td>" + dateFormat.format(customer.getBirthDate()) + "</td>");
									out.println("<td>" + customer.getGender().getName() + "</td>");
									out.println("<td>" + customer.getPhone().toString() + "</td>");
									out.println("<td>" + customer.getUser().getEmail() + "</td>");
									out.println("<td>" + customer.getHomeAddress().getAlias() + "</td>");
									out.println("<td>" + customer.getChargeAddress().getAlias() + "</td>");
									out.println("<td>" + deliveryAddresses.substring(0, deliveryAddresses.length() - 2) + "</td>");
									out.println("<td>"
											+ "<a id='edit-" + customer.getId() + "' href='" + request.getContextPath() + "/customers/find?operation=FIND&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-pencil' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("<td>"
											+ "<a id='disable-" + customer.getId() + "' href='" + request.getContextPath() + "/customers/disable?operation=DISABLE&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-trash' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("<td>"
											+ "<a href='" + request.getContextPath() + "/customers/history?operation=HISTORY&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-history' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("</tr>");
									i++;
								}
							} else {
								out.println("<tr>");

								for(int j = 0; j <= 11; j++){
									out.println("<td> - </td>");
								}

								out.println("</tr>");
							}
						%>
						</tbody>

						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><b><% out.println(i); %></b> registros encontrados.</td>
							</tr>
						</tfoot>
					</table>
				</div>
		<%
			}
		%>
			</div>
			
			<a class="create-customer" href="<% out.print(request.getContextPath().concat("/customers/create?operation=CREATE")); %>">Criar Cliente</a>
			<a class="list-disable-customer" href="<% out.print(request.getContextPath().concat("/customers/list-disable?operation=LIST-DISABLE")); %>">Listar Inativos</a>
		</div>

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
			});
		</script>
    </body>
</html>
