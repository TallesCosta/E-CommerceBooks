<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.talles.ecommercebooks.domain.customer.Customer"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<title>Listagem de Clientes Inativos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@include file="../commons/admin/menu-css.jsp"%>tu
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
			<h1 id="list-disable-customer">Listagem de Clientes Inativos</h1>

			<div>
				<form action="" method="POST">
					<button class="button button-outline" name="" value="">Filtrar</button>
				</form>
			</div>

			<div class="row">
				<div class="column">
					<table>
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
									out.println("<td>" + dateFormat.format(customer.getBirthDate()) + "</td>");
									out.println("<td>" + customer.getGender().getName() + "</td>");
									out.println("<td>" + customer.getPhone().toString() + "</td>");
									out.println("<td>" + customer.getUser().getEmail() + "</td>");
									out.println("<td>" + customer.getHomeAddress().getAlias() + "</td>");
									out.println("<td>" + customer.getChargeAddress().getAlias() + "</td>");
									out.println("<td>"
											+ "<a id='edit-" + customer.getId() + "' href='" + request.getContextPath() + "/customers/find?operation=FIND&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-pencil' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("<td>"
											+ "<a id='enable-" + customer.getId() + "' href='" + request.getContextPath() + "/customers/enable?operation=ENABLE&id=" + customer.getId() + "'>"
											+ "<i class='fa fa-plus' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("</tr>");
									i++;
								}
							} else {
								out.println("<tr>");

								for(int j = 0; j <= 9; j++){
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
								<td><b><% out.println(i); %></b> registros encontrados.</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<a class="create-customer" href="<% out.print(request.getContextPath().concat("/customers/create?operation=CREATE")); %>">Criar Cliente</a>
			<a class="list-customer" href="<% out.print(request.getContextPath().concat("/customers/list?operation=LIST")); %>">Listar Ativos</a>
		</div>
		<%
			}
		%>
    </body>
</html>
