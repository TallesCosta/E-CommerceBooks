<%@page import="br.com.talles.ecommercebooks.domain.sale.SaleItem"%>
<%@page import="br.com.talles.ecommercebooks.domain.sale.Cart"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho</title>

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
		<link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
		<link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<style>
			.finish {
				float: right;
			}
		</style>
    </head>
    <body>
		<div class="container">
			<h1>Carrinho de Compras!</h1>
			<%
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
			%>
			<div class="row">
				<div class="column">
					<%
						if (cart == null) {
						    out.println("<p><i class='fa fa-exclamation-triangle' aria-hidden='true' style='color: #FFFF00;'></i> Carrinho ainda vazio ou removido por inatividade!</p>");
						}

						Result result = new Result();
						result = (Result) request.getAttribute("result");
						if (result != null) {
							if (result.hasMsg()) {
								String[] msgs = result.getMsg().split("\n");
								out.print("<p>");
								for(String msg : msgs)
									out.print("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
								out.print("</p>");
							}
						}
					%>
					<table>
						<thead>
						<tr>
							<th>Nome</th>
							<th>Preço Unitário</th>
							<th>Quantidade</th>
							<th>Remover</th>
						</tr>
						</thead>

						<tbody>
						<%
							if (cart == null || !cart.hasSaleItem()) {
								out.println("<tr>");

								for(int j = 0; j <= 3; j++){
									out.println("<td> - </td>");
								}

								out.println("</tr>");
							} else {
								int i = 0;

								for(SaleItem saleItem : cart.getSaleItems()) {
									out.println("<tr>");
									out.println("<td>" + saleItem.getBook().getTitle() + "</td>");
									out.println("<td>" + formatter.format(saleItem.getUnitaryPrice()) + "</td>");
									out.println("<td>" + saleItem.getAmount()+ "</td>");
									out.println("<td>"
											+ "<a href='" + request.getContextPath() + "/carts/delete?operation=DELETE&id=" + i + "'>"
											+ "<i class='fa fa-times' aria-hidden='true'></i>"
											+ "</a>"
											+ "</td>");
									out.println("</tr>");

									i++;
								}
							}
						%>
						</tbody>

						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td>
									<%
										String itemQuantity = "0";
										if(cart != null)
										    itemQuantity = "" + cart.getTotalAmount();

										out.println("Quantidade de Itens: <b>" + itemQuantity + "</b>");
									%>
								</td>
								<td>
									<%
										String price = formatter.format(0L);
										if(cart != null)
											price = formatter.format(cart.getPrice());

										out.println("Valor Parcial: <b>" + price + "</b>");
									%>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>

			<% out.print("<a class='finish button' href='" + request.getContextPath() + "/sales/create?operation=CREATE'>Finalizar Compras</a>"); %>
		</div>
				
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
