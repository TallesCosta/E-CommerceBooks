<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.DeactivationCategory"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Book"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Category"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Author"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Livros</title>
		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");

			if (result != null) {
				if (result.hasMsg()) {
					String[] msgs = result.getMsg().split("\n");
					out.println("<p>");
					for(String msg : msgs)
						out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
					out.println("</p>");
				}
		%>
		
		<div id="app">
			<h1>Listagem de Livros</h1>
			
			<div>
				<form action="" method="POST">
					<!-- select authors -->
					<label for='author'>Autor: </label>
					<select name='author' id='author'>
			<%
				for(Entity entity : result.getEntities(Author.class.getSimpleName())){
					Author author = (Author) entity;
					out.println("<option value='" + author.getId() + "'>" + author.getName() + "</option>");
				}
			%>
					</select> <!-- end select authors -->

					<!-- select categoty -->
					<label for='categoty'>Categoria: </label>
					<select name='categoty' id='categoty'>
			<%
				for(Entity entity : result.getEntities(Category.class.getSimpleName())){
					Category categoty = (Category) entity;
					out.println("<option value='" + categoty.getId() + "'>" + categoty.getName() + "</option>");
				}
			%>
					</select> <!-- end select categoty -->

					<!-- select publishingCompany -->
					<label for='publishingCompany'>Editora: </label>
					<select name='publishingCompany' id='publishingCompany'>
			<%
				for(Entity entity : result.getEntities(PublishingCompany.class.getSimpleName())){
					PublishingCompany publishingCompany = (PublishingCompany) entity;
					out.println("<option value='" + publishingCompany.getId() + "'>" + publishingCompany.getName() + "</option>");
				}
			%>
					</select> <!-- end select publishingCompany -->

					<!-- select priceGroup -->
					<label for='priceGroup'>Grupo de Precificação: </label>
					<select name='priceGroup' id='priceGroup'>
			<%
				for(Entity entity : result.getEntities(PriceGroup.class.getSimpleName())){
					PriceGroup priceGroup = (PriceGroup) entity;
					out.println("<option value='" + priceGroup.getId() + "'>" + priceGroup.getMarkup() + "</option>");
				}
			%>
					</select> <!-- end select priceGroup -->

					<button name="" value="">Filtrar</button>
				</form>
			</div>
			
			<br><br>
			<div>
				<table class='u-full-width'>
					<thead>
						<tr>
							<td>Título</td>
							<td>Edição</td>
							<td>Ano de Publicação</td>
							<td>Nº de Páginas</td>
							<td>ISBN</td>
							<td>Código de Barras</td>
							<td>Categoria(s)</td>
							<td>Excluir</td>
							<td>Editar</td>
						</tr>
					</thead>
					
					<tbody>
			<%
				int i = 0;
				if(result.hasEntities() && result.getKeys().contains(Book.class.getSimpleName())){
					for(Entity entity : result.getEntities(Book.class.getSimpleName())){
						Book book = (Book) entity;

						String categories = "";
						for(Category category : book.getCategories()){
							categories += category.getName() + ", ";
						}

						out.println("<tr>");
						out.println("<td>" + book.getTitle() + "</td>");
						out.println("<td>" + book.getEdition() + "</td>");
						out.println("<td>" + book.getPublicationYear() + "</td>");
						out.println("<td>" + book.getNumberOfPages() + "</td>");
						out.println("<td>" + book.getIsbn() + "</td>");
						out.println("<td>" + book.getEan13()+ "</td>");
						out.println("<td>" + categories.substring(0, categories.length() - 2) + "</td>");
						out.println("<td>"
										+ "<a href='books/find?operation=FIND&id=" + book.getId() + "'>"
											+ "<i class='fa fa-pencil' aria-hidden='true'></i>"
										+ "</a>"
									+ "</td>");
						out.println("<td>"
										+ "<a onclick='setDisableId(" + book.getId() + ")' href='#'>"
											+ "<i class='fa fa-trash' aria-hidden='true'></i>"
										+ "</a>"
									+ "</td>");
						out.println("</tr>");
						i++;
					}
				} else {
					out.println("<tr>");
					
					for(int j = 0; j <= 8; j++){
						out.println("<td> - </td>");
					}
					
					out.println("</tr>");
				}
			%>
					</tbody>
					
					<tfooter>
						<tr>
							<td>Título</td>
							<td>Edição</td>
							<td>Ano de Publicação</td>
							<td>Nº de Páginas</td>
							<td>ISBN</td>
							<td>Código de Barras</td>
							<td>Categoria(s)</td>
							<td>Editar</td>
							<td>Excluir</td>
						</tr>
					</tfooter>
				</table>
					
				<p><% out.println(i); %> registros encontrados.</p>
			</div>
			
			<a href="<% out.print(request.getContextPath().concat("/books/create?operation=CREATE")); %>">Criar Livro</a>
			<a href="<% out.print(request.getContextPath().concat("/books/list-disable?operation=LIST-DISABLE")); %>">Listar Inativos</a>
			
			<br><br><br><br><br>
			<div id="light-box">
				<form action="disable" method="POST">
					<input name="id" id="id" type="hidden">
					
					<fieldset>
						<legend>Desativar Livro</legend>
						<div>
							<!-- select categoty -->
							<label for='deactivationCategory'>Categoria de Desativação*: </label>
							<select name='deactivationCategory' id='deactivationCategory'>
			<%
					for (Entity entity : result.getEntities(DeactivationCategory.class.getSimpleName())) {
						DeactivationCategory deactivationCategory = (DeactivationCategory) entity;
						out.println("<option value='" + deactivationCategory.getId() + "'>" + deactivationCategory.getName() + "</option>");
					}
				}
			%>
							</select> <!-- end select categoty -->
						</div>
						<div>
							<label for="justification">Justificativa*: </label>
							<input name="justification" id="justification" type="text">
						</div>
					</fieldset>
							
					<button name="operation" value="DISABLE" type="submit">Desativar</button>
					<small>Todos os campos marcados com * são obrigatórios.</small>
				</form>
			</div>
		</div>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
		
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
		</script>
		
		<script>
			function setDisableId(id) {
				alert(id);
				$("#id").val(id);
			}
		</script>
    </body>
</html>
