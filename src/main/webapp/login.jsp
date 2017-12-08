<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
		<link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
		<link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">
		<style>
			.center {
				width: 450px;
				margin: 150px auto auto auto;
			}
		</style>
    </head>
    <body>
		<%
			Result result = (Result) request.getAttribute("result");

			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", "", 0L), new User("", "", "", 0L),
					Arrays.asList(new ChargeAddress("", "", "", "", "", "", "", "", "", new State(0L, new Country(0L)), 0L)),
					Arrays.asList(new DeliveryAddress("", "", "", "", "", "", "", "", "", new State(0L, new Country(0L)), 0L)),
					Arrays.asList(new CreditCard("", "", "", new Date(0L), new CardCompany(0L), 0L)), 0L);

			if (result != null) {
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
			}			
		%>
			<div class="center">
				<h1>Faça login</h1>
				<form action="<% out.print(request.getContextPath().concat("/log-in")); %>" method="POST" >
					<fieldset>
						<label for="email">E-mail: </label>
						<input name="email" id="email" type="email" >

						<label for="password">Senha: </label>
						<input name="password" id="password" type="password" >

						<input type="hidden" name="next" id="next" value="" />
						<input type="hidden" name="operation" id="operation-custumer" value="LIST" />
						<button type="submit">Login</button>
						<a class="create-customer"
						   href="<% out.print(request.getContextPath().concat("/customers/create?operation=CREATE")); %>">  Cadastre-se</a>
					</fieldset>
				</form>
			</div>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>

		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
				integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
				crossorigin="anonymous">
		</script>

		<script>
            // With the page ready, set the URL next
            $(function() {
                var url = window.location.pathname;
                if (url !== "/E-CommerceBooks/log-in") {
                	$("#next").val(url + window.location.search);
				} else {
                    url = document.referrer;
					url = url.replace("http://localhost:8080", "");
                    $("#next").val(url);
				}
            });
		</script>
    </body>
</html>
