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
		
		<a href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST")); %>">Listagem de Livros</a>
    </body>
</html>
