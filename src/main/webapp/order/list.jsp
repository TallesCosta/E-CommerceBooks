<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listagem de Pedidos</title>
    </head>
    <body>
        <h1>Listagem de Pedidos</h1>
		
		<a class="create-order" href="<% out.print(request.getContextPath().concat("/orders/create?operation=CREATE")); %>">Criar Cliente</a>
    </body>
</html>
