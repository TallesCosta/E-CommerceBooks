<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.domain.sale.Stock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Início</title>
		
		<style>
			.product{
				display: inline-block;
				width: 200px;
				border: 1px solid #ccc;
			}
		</style>
    </head>
    <body>
        <h1>Bora às compras!</h1>
		<% out.print("<a href='" + request.getContextPath() + "/cart/list.jsp'>Carrinho</a>"); %>
		
		<div>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");

			if (result != null) {
				if (result.hasMsg()) {
					String[] msgs = result.getMsg().split("\n");
					out.println("<p>");
					for (String msg : msgs)
						out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
					out.println("</p>");
				} else if (result.hasEntities() && result.getKeys().contains(Stock.class.getSimpleName())) {
					for (Entity entity : result.getEntities(Stock.class.getSimpleName())) {
						Stock stock = (Stock) entity;
		%>
			<div class="product">
				<div>
					<img src="http://www.hancock.k12.ky.us/userfiles/92/bookworm.jpg" width="100px" height="100px" />
					<% out.println("<p> " + stock.getBook().getTitle() + " </p>"); %>
					<% out.println("<p> " + stock.getBook().getAuthor().getName() + " </p>"); %>
					<% out.println("<p> " + stock.getSalePrice() + " </p>"); %>
				</div>

				<div>
				<form action="../carts/save" method="POST">
					<% out.println("<input type='hidden' name='id_stock' id='id_stock' value='" + stock.getId() + "' />"); %>
					<% out.println("<input type='hidden' name='id_book' id='id_book' value='" + stock.getBook().getId() + "' />"); %>
					<% out.println("<input type='hidden' name='unitaryPrice' id='unitaryPrice' value='" + stock.getSalePrice() + "' />"); %>
					<input type="number" name="amount" id="amount" value="1" min="1" />

					<input type="hidden" name="operation" id="operation-cart" value="SAVE" />
					<button type="submit">Add-Cart</button>
				</form>
				</div>
			</div>
		<%
					}
				}
			}
		%>
		</div>
    </body>
</html>
