<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.domain.sale.Stock"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Início</title>

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
		<link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
		<link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">

		<style>
			.product {
				border: 1px solid #ccc;
				text-align: center;
				margin-right: 25px;
				border-radius: .4rem;
			}

			.product form {
				margin: 0;
			}

			.product p {
				margin-bottom: 1.2rem;
			}

			.product .title {
				font-weight: 600;
			}
		</style>
    </head>
    <body>
		<div class="container">
			<div class="row">
				<div class="column">
					<h1>Bora às compras!</h1>
					<% out.print("<a href='" + request.getContextPath() + "/cart/list.jsp'>Carrinho</a>"); %>
					<% out.print("<a href='" + request.getContextPath() + "/login.jsp'>Login!</a>"); %>
				</div>
			</div>
			<div class="row">
				<%
					Result result = (Result) request.getAttribute("result");
					NumberFormat formatter = NumberFormat.getCurrencyInstance();

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
				<div class="product column column-20">
					<img src="http://www.hancock.k12.ky.us/userfiles/92/bookworm.jpg" width="100px" height="100px" />
					<% out.println("<p class='title'> " + stock.getBook().getTitle() + " </p>"); %>
					<% out.println("<p class='author'> De " + stock.getBook().getAuthor().getName() + " </p>"); %>
					<% out.println("<p class='value'> " + formatter.format(stock.getSalePrice()) + " </p>"); %>

					<form action="../carts/save" method="POST">
						<% out.println("<input type='hidden' name='id_stock' id='id_stock' value='" + stock.getId() + "' />"); %>
						<% out.println("<input type='hidden' name='id_book' id='id_book' value='" + stock.getBook().getId() + "' />"); %>
						<% out.println("<input type='hidden' name='unitaryPrice' id='unitaryPrice' value='" + stock.getSalePrice() + "' />"); %>
						<input type="number" name="amount" id="amount" value="1" min="1" />

						<input type="hidden" name="operation" id="operation-cart" value="SAVE" />
						<button class="button button-clear" type="submit">Add-Cart</button>
					</form>
				</div>
		<%
					}
				}
			}
		%>
			</div>
		</div>
    </body>
</html>
