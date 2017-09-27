<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Início</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Página Inicial</h1>
		
		<ul>
			<li>
				<a href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST")); %>">Listagem de Livros</a>
			</li>
			<li>
				<a href="<% out.print(request.getContextPath().concat("/books/list-disable?operation=LIST-DISABLE")); %>">Listagem de Livros Inativos</a>
			</li>
		</ul>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
