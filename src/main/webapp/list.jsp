<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.talles.ecommercebooks.domain.Author"%>
<%@page import="br.com.talles.ecommercebooks.domain.Category"%>
<%@page import="br.com.talles.ecommercebooks.domain.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.Book"%>
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
		<div id="app">
			<h1>Listagem de Livros</h1>
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
					} else if (result.hasEntities()) {
			%>
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
			
			<%
					}
					
					out.println("<br><br>");
					out.println("<div>");
					out.println("<table class='u-full-width'>");
					out.println("<thead>");
					out.println("<tr>");
					out.println("<td>Título</td>");
					out.println("<td>Edição</td>");
					out.println("<td>Ano de Publicação</td>");
					out.println("<td>Nº de Páginas</td>");
					out.println("<td>ISBN</td>");
					out.println("<td>Código de Barras</td>");
					out.println("<td>Categoria(s)</td>");
					out.println("<td>Excluir</td>");
					out.println("<td>Editar</td>");
					out.println("</tr>");
					out.println("</thead>");
					out.println("<tbody>");

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
											+ "<a href='books/delete?operation=DELETE&id=" + book.getId() + "'>"
												+ "<i class='fa fa-trash' aria-hidden='true'></i>"
											+ "</a>"
										+ "</td>");
							out.println("</tr>");
							i++;
						}
					}

					out.println("</tbody>");
					out.println("<tfooter>");
					out.println("<tr>");
					out.println("<td>Título</td>");
					out.println("<td>Edição</td>");
					out.println("<td>Ano de Publicação</td>");
					out.println("<td>Nº de Páginas</td>");
					out.println("<td>ISBN</td>");
					out.println("<td>Código de Barras</td>");
					out.println("<td>Categoria(s)</td>");
					out.println("<td>Editar</td>");
					out.println("<td>Excluir</td>");
					out.println("</tr>");
					out.println("</tfooter>");
					out.println("</table>");
					out.println("<p>" + i + " registros encontrados.</p>");
					out.println("</div>");
				}
			%>

			<a href="<% out.print(request.getContextPath().concat("/books/create?operation=CREATE")); %>">Criar Livro</a>
		</div>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
