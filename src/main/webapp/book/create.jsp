<%@page import="br.com.talles.ecommercebooks.domain.book.StatusCategory"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.ChangeStatus"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Dimension"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Author"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Book"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.SaleParameterization"%>
<%@page import="br.com.talles.ecommercebooks.domain.book.Category"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		Result result = new Result();
		result = (Result) request.getAttribute("result");

		if (result != null) {
	%>
	<head>
		<title>
			<% if (result.getOperation().equals("CREATE")) { out.print("Criação de Livro"); }
			else if (result.getOperation().equals("FIND")) { out.print("Alteração de Livro"); } %>
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			Book book = new Book("", "", 0, 0, "", "", "", 
					new Dimension(0.0, 0.0, 0.0, 0.0, 0L), new SaleParameterization(0, 0, 0L),
					new ChangeStatus(new StatusCategory(0L), 0L), 0L);
			
			if (result.getKeys().contains(Book.class.getSimpleName())) {
				book = (Book) result.getEntities(Book.class.getSimpleName()).get(0);
			}

			if (result.hasMsg()) {
				String[] msgs = result.getMsg().split("\n");
				out.print("<p>");
				for(String msg : msgs)
					out.print("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
				out.print("</p>");
			}
		%>
		
		<div id="app">
			<h1>
				<% if (result.getOperation().equals("CREATE")) { out.print("Criação de Livro"); }
				else if (result.getOperation().equals("FIND")) { out.print("Alteração de Livro"); } %>
			</h1>
			
			<form 
				<% if (result.getOperation().equals("CREATE")) { out.print("action='save'"); }
				else if (result.getOperation().equals("FIND")) { out.print("action='update'"); } %>
				method="POST">
				
				<fieldset>
					<legend>Dados básicos</legend>
					<input type="hidden" name="id" id="id" 
						   <% out.print("value='" + book.getId() + "'"); %> >
					<div>
						<label for="title">Titulo*: </label>
						<input name="title" id="title" value="<% out.print(book.getTitle()); %>" type="text">
					</div>
					<div>
						<label for="synopsis">Sinópse*: </label>
						<textarea name="synopsis" id="synopsis" maxlength="255"><% out.print(book.getSynopsis()); %></textarea>
					</div>
					<div>
						<input type="hidden" name="idAuthor" id="idAuthor" value="2" >
						<label for="author">Autor*: </label>
						<select name="author" id="author">
		<%	
			for(Entity entity : result.getEntities(Author.class.getSimpleName())){
				Author author = (Author) entity;
				out.print("<option value='" + author.getId() + "'>" + author.getName() + "</option>");
			}
		%>
						</select>
					</div>
					<div>
						<input type="hidden" name="idCategory" id="idCategory" value="1-2" >
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
					</div>
					<div>
						<input type="hidden" name="idPublishingCompany" id="idPublishingCompany" value="1" >
						<label for="publishingCompany">Editora*: </label>
						<select name="publishingCompany" id="publishingCompany">
		<%
			for(Entity entity : result.getEntities(PublishingCompany.class.getSimpleName())){
				PublishingCompany publishingCompany = (PublishingCompany) entity;
				out.print("<option value='" + publishingCompany.getId() + "'>" + publishingCompany.getName() + "</option>");
			}
		%>
						</select>
					</div>
					<div>
						<label for="edition">Edição*: </label>
						<input name="edition" id="edition" value="<% out.print(book.getEdition()); %>" type="text">
					</div>
					<div>
						<label for="publicationYear">Ano*: </label>
						<input name="publicationYear" id="publicationYear" value="<% out.print(book.getPublicationYear()); %>" type="number" min="1889" max="2017">
					</div>
					<div>
						<label for="numberOfPages">Número de páginas*: </label>
						<input name="numberOfPages" id="numberOfPages" value="<% out.print(book.getNumberOfPages()); %>" type="number" min="1">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Dimensões</legend>
					<input type="hidden" name="idDimension" id="idDimension" 
						   <% out.print("value='" + book.getDimension().getId() + "'"); %> >
					<div>
						<label for="height">Altura*: </label>
						<input name="height" id="height" value="<% out.print(book.getDimension().getHeight()); %>" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
					<div>
						<label for="widht">Largura*: </label>
						<input name="widht" id="widht" value="<% out.print(book.getDimension().getWidht()); %>" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
					<div>
						<label for="weight">Peso*: </label>
						<input name="weight" id="weight" value="<% out.print(book.getDimension().getWeight()); %>" type="number" step="0.001" min="0.001" max="10"> kg
					</div>
					<div>
						<label for="depth">Profundidade*: </label>
						<input name="depth" id="depth" value="<% out.print(book.getDimension().getDepth()); %>" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Identificação</legend>
					<div>
						<label for="isbn">ISBN*: </label>
						<input name="isbn" for="isbn" value="<% out.print(book.getIsbn()); %>" type="text">
					</div>
					<div>
						<label for="ean13">Código de barras*: </label>
						<input name="ean13" id="ean13" value="<% out.print(book.getEan13()); %>" type="text">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Grupo de Precificação</legend>
					<div>
						<input type="hidden" name="idPriceGroup" id="idPriceGroup" value="2" >
						<label for="priceGroup">Porcentagem*: </label>
						<select name="priceGroup" id="priceGroup">
		<%
			for(Entity entity : result.getEntities(PriceGroup.class.getSimpleName())){
				PriceGroup priceGroup = (PriceGroup) entity;
				out.print("<option value='" + priceGroup.getId() + "'>" + priceGroup.getMarkup() + " %</option>");
			}
		%>
						</select>
					</div>      
				</fieldset>
				
				<fieldset>
					<legend>Parâmetro de venda</legend>
					<input type="hidden" name="idSaleParameterization" id="idSaleParameterization" 
						   <% out.print("value='" + book.getSaleParameterization().getId() + "'"); %> >
					<div>
						<label for="minSaleLimit">Limite mínimo de vendas*: </label>
						<input name="minSaleLimit" id="minSaleLimit" value="<% out.print(book.getSaleParameterization().getMinSaleLimit()); %>" type="number" min="1">
					</div>
					<div>
						<label for="periodicity">Periodicidade*: </label>
						<input name="periodicity" id="periodicity" value="<% out.print(book.getSaleParameterization().getPeriodicity()); %>" type="number" min="1">
						<select name="classifierPeriod">
							<option value="m">Minuto(s)</option>
							<option value="H">Hora(s)</option>
							<option value="D">Dia(s)</option>
							<option value="M">Mês(es)</option>
							<option value="Y">Ano(s)</option>
						  </select>
					</div>
				</fieldset>
				
				<input type="hidden" name="operation" id="operation-book" 
					<% if (result.getOperation().equals("CREATE")) { out.print("value='SAVE'"); }
					else if (result.getOperation().equals("FIND")) { out.print("value='UPDATE'"); } %> />
						
				<input type="hidden" name="idChangeStatus" id="idChangeStatus" 
					<% out.print("value='" + book.getChangeStatus().getId() + "'"); %> >
				<input type="hidden" name="justificationChangeStatus" id="justificationChangeStatus" 
					<% out.print("value='" + book.getChangeStatus().getJustification() + "'"); %> >
				<input type="hidden" name="statusCategoryChangeStatus" id="idStatusCategoryChangeStatus" 
					<% out.print("value='" + book.getChangeStatus().getStatusCategory().getId() + "'"); %> >
				
				<button type="submit">Salvar</button>
				<small>Todos os campos marcados com * são obrigatórios.</small>
			</form>
		</div>
	<%
		}
	%>
						
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
		</script>
		
		<script>
			// TODO: Use this code to all selects sz
			$(function() {
				var idAuthor = $("#idAuthor").val();
				$("#author").prop("selectedIndex", idAuthor);
				
				// Don't work :(
				var idCategory = $("#idCategory").val();
				idCategory = idCategory.split("-");
				for (var i = idCategory.length; i > 0 ; i--) {
					$("#category").prop("selectedIndex", idCategory[i]);
				}
				
				var idPublishingCompany = $("#idPublishingCompany").val();
				$("#publishingCompany").prop("selectedIndex", idPublishingCompany);
				
				var idPriceGroup = $("#idPriceGroup").val();
				$("#priceGroup").prop("selectedIndex", idPriceGroup);
			});
		</script>
		
	</body>
</html>
