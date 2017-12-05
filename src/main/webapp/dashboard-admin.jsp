<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Início</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
		<link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
		<link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<style>
			nav {
				width: 100%;
				height: 50px;
				background-color: #9B4DCA;
			}

			nav .item {
				display: inline-block;
				color: white;
				height: 50px;
				line-height: 50px;
				margin: 0 8px;
				padding: 0 8px;
			}

			nav .item:hover {
				color: #9B4DCA;
				background-color: white;
			}

			nav a, nav a:visited, nav a:active {
				color: white;
				display: block;
				text-decoration: none;
			}

			nav .item:hover a {
				color: #9B4DCA;
			}
		</style>
    </head>
    <body>
		<nav>
			<div class="container">
				<div class="item">
					<a class="list-book" href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST&USER=1")); %>">
						Livros
					</a>
				</div>
				<div class="item">
					<a class="list-disable-book" href="<% out.print(request.getContextPath().concat("/books/list-disable?operation=LIST-DISABLE")); %>">
						Livros Inativos
					</a>
				</div>
				<div class="item">
					<a class="list-customer" href="<% out.print(request.getContextPath().concat("/customers/list?operation=LIST")); %>">
						Clientes
					</a>
				</div>
				<div class="item">
					<a class="list-disable-customer" href="<% out.print(request.getContextPath().concat("/customers/list-disable?operation=LIST-DISABLE")); %>">
						Clientes Inativos
					</a>
				</div>
				<div class="item">
					<a class="list-sale" href="<% out.print(request.getContextPath().concat("/sales/list?operation=LIST")); %>">
						Vendas
					</a>
				</div>
			</div>
		</nav>
		<div class="container">
			<h1 id="index">Página Inicial</h1>
			<h3>Seja bem-vindo, administrador!</h3>
		</div>
    </body>
</html>
