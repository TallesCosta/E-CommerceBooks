<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Início</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1 id="index">Página Inicial</h1>
		
		<ul>
			<li>
				<a class="list-book" href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST")); %>">Listagem de Livros</a>
			</li>
			<li>
				<a class="list-disable-book" href="<% out.print(request.getContextPath().concat("/books/list-disable?operation=LIST-DISABLE")); %>">Listagem de Livros Inativos</a>
			</li>
			
			<br><br>
			<li>
				<a class="list-customer" href="<% out.print(request.getContextPath().concat("/customers/list?operation=LIST")); %>">Listagem de Clientes</a>
			</li>
			<li>
				<a class="list-disable-customer" href="<% out.print(request.getContextPath().concat("/customers/list-disable?operation=LIST-DISABLE")); %>">Listagem de Clientes Inativos</a>
			</li>
		</ul>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
