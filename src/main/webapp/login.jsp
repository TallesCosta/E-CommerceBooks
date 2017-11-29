<%@page import="br.com.talles.ecommercebooks.domain.customer.CardCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.CreditCard"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Country"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.State"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.City"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Phone"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Gender"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Arrays"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Address"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.User"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Customer"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log-in</title>
		
		<style>
			fieldset{
				display: inline;
			}
		</style>
    </head>
    <body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");
			
			Customer customer = new Customer("", "", new Date(0L), new Gender(""), new Phone("", "", "", 0L), 
			   new User("", "", "", 0L), 
			   new Address("", "", "", "", "", "", "", "", new City(0L, new State(0L, new Country(0L))), 0L), 
			   new Address("", "", "", "", "", "", "", "", new City(0L, new State(0L, new Country(0L))), 0L), 
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
        <h1></h1>
		<form action="<% out.print(request.getContextPath().concat("/log-in")); %>" method="POST" >
			<fieldset>
				<legend>Usuário</legend>
				<div>
					<label for="email">E-mail: </label>
					<input name="email" id="email" type="email" >
				</div>
				<div>
					<label for="password">Senha: </label>
					<input name="password" id="password" type="password" >
				</div>

				<input type="hidden" name="next" id="next" value="" />
				<input type="hidden" name="operation" id="operation-custumer" value="LIST" />
				<button type="submit">Log-in</button>
				<a class="create-customer" 
				   href="<% out.print(request.getContextPath().concat("/customers/create?operation=CREATE")); %>">Cadastra</a>
			</fieldset>
		</form>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>

		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
				integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
				crossorigin="anonymous">
		</script>

		<script>
            // With the page ready, set the URL next
            $(function() {
                url = window.location.pathname;
                if (url != "/E-CommerceBooks/log-in") {
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
