<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewprot" content="width=device-width, initial-scale=1.0">
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
		<% out.print("<a href='" + request.getContextPath() + "/carts/list?operation=LIST'>Carrinho</a>"); %>
		
		<div>
			<div class="product">
				<div>
					<img src="http://www.hancock.k12.ky.us/userfiles/92/bookworm.jpg" width="100px" height="100px" />
					<p>Como passar em LES em 583 passos!</p>
					<span>Davisson Medeiros</span>
					<p>49,99</p>
				</div>

				<div>
				<form action="carts/save" method="POST">
					<input type="hidden" name="book_id" id="book_id" value="1" />
					<input type="hidden" name="unitaryPrice" id="unitaryPrice" value="49" />
					<input type="number" name="amount" id="amount" value="1" />

					<input type="hidden" name="operation" id="operation-cart" value="SAVE" />
					<button type="submit">Add-Cart</button>
				</form>
				</div>
			</div>

			<div class="product">
				<div>
					<img src="http://www.hancock.k12.ky.us/userfiles/92/bookworm.jpg" width="100px" height="100px" />
					<p>Como superar uma DP!</p>
					<span>Davisson Medeiros</span>
					<p>34,99</p>
				</div>

				<form action="carts/save" method="POST">
					<input type="hidden" name="book_id" id="book_id" value="1" />
					<input type="hidden" name="unitaryPrice" id="unitaryPrice" value="34" />
					<input type="number" name="amount" id="amount" value="1" />

					<input type="hidden" name="operation" id="operation-cart" value="SAVE" />
					<button type="submit">Add-Cart</button>
				</form>
			</div>
		</div>
    </body>
</html>
