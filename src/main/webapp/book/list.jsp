<%@page import="br.com.talles.ecommercebooks.domain.book.Dimension"%>
<%@page import="java.util.Arrays"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.ChangeStatus"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.SaleParameterization"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.StatusCategory"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Book"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Category"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Author"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Livros</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<style>
			fieldset {
				display: inline-block;
			}
		</style>	
    </head>
    <body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");

			if (result != null) {
				
				Book filterBook = new Book("", "", 0, 0, "", "", "", 
					new Dimension(0.0, 0.0, 0.0, 0.0, 0L), new PriceGroup(0.0, 0L), 
					new PublishingCompany(0L), new SaleParameterization(0, 0, 0L),
					new ChangeStatus(new StatusCategory(0L), 0L), new Author(0L), 
					Arrays.asList(new Category(0L)), 0L);
				
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
				<form action="list" method="POST">
					<fieldset>
						<legend>Dados da filtragem</legend>
						<fieldset>
						<legend>Dados básicos</legend>
						
						<label for="title">Titulo*: </label>
						<input name="title" id="title" type="text">
						<label for="synopsis">Sinópse*: </label>
						<textarea name="synopsis" id="synopsis" maxlength="255"></textarea>
						
						<input type="hidden" name="idAuthor" id="idAuthor" 
							   <% out.print("value='" + filterBook.getAuthor().getId() + "'"); %> >
						<label for="author">Autor*: </label>
						<select name="author" id="author">
			<%	
				for(Entity entity : result.getEntities(Author.class.getSimpleName())){
					Author author = (Author) entity;
					out.print("<option value='" + author.getId() + "'>" + author.getName() + "</option>");
				}
			%>
						</select>
						<input type="hidden" name="idCategory" id="idCategory" 
							   <% 
								   String value = "";
								   for (Category c : filterBook.getCategories()) {
									   value += c.getId() + "-";
								   }

								   out.print("value='" + value.substring(0, value.length() - 1) + "'"); %> >
						<label for="category">Categoria*: </label>
						<select name="category" id="category" multiple>
			<%
				for(Entity entity : result.getEntities(Category.class.getSimpleName())){
					Category category = (Category) entity;
					if(category.getId() == 1){
						out.print("<option selected value='" + category.getId() + "'>" + category.getName() + "</option>");
					}else{
						out.print("<option value='" + category.getId() + "'>" + category.getName() + "</option>");
					}
				}
			%>
						</select>
						<input type="hidden" name="idPublishingCompany" id="idPublishingCompany" 
							   <% out.print("value='" + filterBook.getPublishingCompany().getId() + "'"); %> >
						<label for="publishingCompany">Editora*: </label>
						<select name="publishingCompany" id="publishingCompany">
			<%
				for(Entity entity : result.getEntities(PublishingCompany.class.getSimpleName())){
					PublishingCompany publishingCompany = (PublishingCompany) entity;
					out.print("<option value='" + publishingCompany.getId() + "'>" + publishingCompany.getName() + "</option>");
				}
			%>
						</select>
						<label for="edition">Edição*: </label>
						<input name="edition" id="edition" type="text">
						<label for="publicationYear">Ano*: </label>
						<input name="publicationYear" id="publicationYear" type="number" >
						<label for="numberOfPages">Número de páginas*: </label>
						<input name="numberOfPages" id="numberOfPages" type="number" >
					</fieldset>

					<fieldset>
						<legend>Dimensões</legend>
						 <label for="height">Altura*: </label>
						 <input name="height" id="height" type="number" step="0.01" > cm
						 <label for="widht">Largura*: </label>
						 <input name="widht" id="widht" type="number" step="0.01" > cm
						 <label for="weight">Peso*: </label>
						 <input name="weight" id="weight" type="number" step="0.001" > kg
						 <label for="depth">Profundidade*: </label>
						 <input name="depth" id="depth" type="number" step="0.01" > cm
					</fieldset>

					<fieldset>
						<legend>Identificação</legend>
						<label for="isbn">ISBN*: </label>
						<input name="isbn" for="isbn" type="text">
						<label for="ean13">Código de barras*: </label>
						<input name="ean13" id="ean13" type="text">
					</fieldset>

					<fieldset>
						<legend>Grupo de Precificação</legend>
						<input type="hidden" name="idPriceGroup" id="idPriceGroup" 
							   <% out.print("value='" + filterBook.getPriceGroup().getId() + "'"); %> >
						<label for="priceGroup">Porcentagem*: </label>
						<select name="priceGroup" id="priceGroup">
			<%
				for(Entity entity : result.getEntities(PriceGroup.class.getSimpleName())){
					PriceGroup priceGroup = (PriceGroup) entity;
					out.print("<option value='" + priceGroup.getId() + "'>" + priceGroup.getMarkup() + " %</option>");
				}
			%>
						</select>
					</fieldset>

					<fieldset>
						<legend>Parâmetro de venda</legend>
						 <label for="minSaleLimit">Limite mínimo de vendas*: </label>
						 <input name="minSaleLimit" id="minSaleLimit" type="number" >
						 <label for="periodicity">Periodicidade*: </label>
						 <input name="periodicity" id="periodicity" value="<% out.print(filterBook.getSaleParameterization().getPeriodicity()); %>" type="number" >
						 <select name="classifierPeriod">
							 <option value="m">Minuto(s)</option>
							 <option value="H">Hora(s)</option>
							 <option value="D">Dia(s)</option>
							 <option value="M">Mês(es)</option>
							 <option value="Y">Ano(s)</option>
						   </select>
					</fieldset>
						 
					<button name="operation" value="LIST" type="submit">Filtrar</button>
				</fieldset>
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
							<td>Dimensions</td>
							<td>Categoria(s)</td>
							<td>Editar</td>
							<td>Excluir</td>
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
						out.println("<td>" + book.getDimension().getHeight() + "x" 
								+ book.getDimension().getWidht() + "x" 
								+ book.getDimension().getDepth() + " (" 
								+ book.getDimension().getWeight() + " kg)</td>");
						out.println("<td>" + categories.substring(0, categories.length() - 2) + "</td>");
						out.println("<td>"
										+ "<a href='" + request.getContextPath() + "/books/find?operation=FIND&id=" + book.getId() + "'>"
											+ "<i class='fa fa-pencil' aria-hidden='true'></i>"
										+ "</a>"
									+ "</td>");
						out.println("<td>"
										+ "<a href='" + request.getContextPath() + "/books/disable?operation=DISABLE&id=" + book.getId() + "'>"
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
							<td>Dimensions</td>
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
					for (Entity entity : result.getEntities(StatusCategory.class.getSimpleName())) {
						StatusCategory deactivationCategory = (StatusCategory) entity;
						
						if (!deactivationCategory.isActivationCategory())
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
			// With the page ready, selects the options in combo-boxes
			$(function() {
				var idAuthor = $("#idAuthor").val();
				$("#author").val(idAuthor);
				
				var idCategory = $("#idCategory").val();
				idCategory = idCategory.split("-");
				$("#category").val(idCategory);
				
				var idPublishingCompany = $("#idPublishingCompany").val();
				$("#publishingCompany").val(idPublishingCompany);
				
				var idPriceGroup = $("#idPriceGroup").val();
				$("#priceGroup").val(idPriceGroup);
			});
			
			function setDisableId(id) {
				alert(id);
				$("#id").val(id);
			}
		</script>
    </body>
</html>
