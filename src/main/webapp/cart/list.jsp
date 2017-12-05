<%@page import="br.com.talles.ecommercebooks.domain.sale.SaleItem"%>
<%@page import="br.com.talles.ecommercebooks.domain.sale.Cart"%>
<%@page import="java.text.NumberFormat"%>
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
				float
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
							if(cart == null || !cart.hasSaleItem()){
								out.println("<tr>");

								for(int j = 0; j <= 3; j++){
									out.println("<td> - </td>");
								}

								out.println("</tr>");
							} else {
								int i = 0;

								for(SaleItem saleItem : cart.getSaleItems()) {
									out.println("<tr>");
									out.println("<td>xxx</td>");
									out.println("<td>" + saleItem.getUnitaryPrice() + "</td>");
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
											price = formatter.format(cart.getTotalAmount());

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
