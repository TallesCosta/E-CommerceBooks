<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
				
				if(result != null){
					if(result.hasMsg()){
						String[] msgs = result.getMsg().split("\n");
						out.println("<p>");
						for(String msg : msgs)
							out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
						out.println("</p>");
					}else{
						out.println("<table class='u-full-width'>");
						out.println("<thead>");
						out.println("<tr>");
						out.println("<td>Título</td>");
						out.println("<td>Edição</td>");
						out.println("<td>Ano de Publicação</td>");
						out.println("<td>Nº de Páginas</td>");
						out.println("<td>ISBN</td>");
						out.println("<td>Código de Barras</td>");
						out.println("<td>Excluir</td>");
						out.println("</tr>");
						out.println("</thead>");
						out.println("<tbody>");
						
						int i = 0;
						if(result.hasEntities()){
							for(Entity entity : result.getEntities(Book.class.getSimpleName())){
								Book book = (Book) entity;
								out.println("<tr>");
								out.println("<td>" + book.getTitle() + "</td>");
								out.println("<td>" + book.getEdition() + "</td>");
								out.println("<td>" + book.getPublicationYear() + "</td>");
								out.println("<td>" + book.getNumberOfPages() + "</td>");
								out.println("<td>" + book.getIsbn() + "</td>");
								out.println("<td>" + book.getEan13()+ "</td>");
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
						out.println("<td>Excluir</td>");
						out.println("</tr>");
						out.println("</tfooter>");
						out.println("</table>");
						out.println("<p>" + i + " registros encontrados.</p>");
					}
				}
			%>
			
			<a href="<% out.print(request.getContextPath().concat("/books/create?operation=CREATE")); %>">Criar Livro</a>
		</div>
    </body>
</html>
