<%@page import="br.com.talles.ecommercebooks.domain.customer.Customer"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
		<title>Listagem de Clientes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");

			if (result != null) {
				if (result.hasMsg()) {
					String[] msgs = result.getMsg().split("\n");
					out.println("<p>");
					for(String msg : msgs)
						out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
					out.println("</p>");
				}
		%>
		
		<div id="app">
			<h1>Listagem de Clientes</h1>
			
			<div>
				<form action="" method="POST">
					<button name="" value="">Filtrar</button>
				</form>
			</div>
				
			<br><br>
			<div>
				<table class='u-full-width'>
					<thead>
						<tr>
							<td>CPF</td>
							<td>Nome</td>
							<td>Data de Nasc.</td>
							<td>Gênero</td>
							<td>Editar</td>
							<td>Excluir</td>
						</tr>
					</thead>
					
					<tbody>
			<%
				int i = 0;
				if(result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())){
					for(Entity entity : result.getEntities(Customer.class.getSimpleName())){
						Customer customer = (Customer) entity;

						out.println("<tr>");
						out.println("<td>" + customer.getRegistry() + "</td>");
						out.println("<td>" + customer.getName() + "</td>");
						out.println("<td>" + customer.getBirthDate() + "</td>");
						out.println("<td>" + customer.getGender().getName() + "</td>");
						out.println("<td>"
										+ "<a href='customers/find?operation=FIND&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-pencil' aria-hidden='true'></i>"
										+ "</a>"
									+ "</td>");
						out.println("<td>"
										+ "<a href='customers/delete?operation=DELETE&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-trash' aria-hidden='true'></i>"
										+ "</a>"
									+ "</td>");
						out.println("</tr>");
						i++;
					}
				} else {
					out.println("<tr>");
					
					for(int j = 0; j <= 5; j++){
						out.println("<td> - </td>");
					}
					
					out.println("</tr>");
				}
			%>
					</tbody>
					
					<tfooter>
						<tr>
							<td>CPF</td>
							<td>Nome</td>
							<td>Data de Nasc.</td>
							<td>Gênero</td>
							<td>Editar</td>
							<td>Excluir</td>
						</tr>
					</tfooter>
				</table>
					
				<p><% out.println(i); %> registros encontrados.</p>
			</div>
			
			<a href="<% out.print(request.getContextPath().concat("/customers/create?operation=CREATE")); %>">Criar Cliente</a>
			<a href="<% out.print(request.getContextPath().concat("/customers/list-disable?operation=LIST-DISABLE")); %>">Listar Inativos</a>
		</div>
		<%
			}
		%>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
