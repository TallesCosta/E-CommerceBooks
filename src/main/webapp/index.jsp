<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Início</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css" />
    </head>
    <body>
		<div class="container" id="app">
			<div class="row">
				<div class="u-full-width">
			        <h1>Página Inicial</h1>
					
					<ul>
						<li>
							<a href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST&enabled=true")); %>">Listagem de Livros</a>
						</li>
						<li>
							<a href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST&enabled=false")); %>">Listagem de Livros inativos</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
    </body>
	<script src="https://use.fontawesome.com/51922b6b29.js"></script>
</html>
