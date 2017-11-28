<%@page import="br.com.talles.ecommercebooks.domain.sale.SaleItem"%>
<%@page import="br.com.talles.ecommercebooks.domain.sale.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
		<div>
			<h1>Carrinho de Compras!</h1>
			<%
				Cart cart = (Cart) request.getSession().getAttribute("cart");
			%>
			<div>
				<table class='u-full-width'>
					<thead>
						<tr>
							<td>Nome</td>
							<td>Preço Unitário</td>
							<td>Quantidade</td>
							<td>Remover</td>
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

					<tfooter>
						<tr>
							<td>Nome</td>
							<td>Preço Unitário</td>
							<td>Quantidade</td>
							<td>Remover</td>
						</tr>
					</tfooter>
				</table>
			</div>

			<div>
				<table class='u-full-width'>
					<tbody>
			<%
				if(cart != null){
					out.println("<tr>");
					out.println("<td>");
					out.println("<p>Quantidade de Itens: " + cart.getTotalAmount() + "</p>");
					out.println("</td>");

					out.println("<td>");
					out.println("<p>Valor Parcial: R$ " + cart.getPrice() + "</p>");
					out.println("</td>");
					out.println("</tr>");
				} else {
					out.println("<tr>");
					out.println("<td>");
					out.println("<p>Quantidade de Itens: 0</p>");
					out.println("</td>");

					out.println("<td>");
					out.println("<p>Valor Parcial: R$ 0.00</p>");
					out.println("</td>");
					out.println("</tr>");
				}
			%>
					</tbody>
				</table>
			</div>

			<% out.print("<a href='" + request.getContextPath() + "/sales/create?operation=CREATE'>Finalizar Compras</a>"); %>
		</div>
				
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
