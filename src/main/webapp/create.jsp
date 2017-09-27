<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.talles.ecommercebooks.domain.SaleParameterization"%>
<%@page import="br.com.talles.ecommercebooks.domain.Dimension"%>
<%@page import="br.com.talles.ecommercebooks.domain.Book"%>
<%@page import="br.com.talles.ecommercebooks.domain.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.Author"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.domain.Entity"%>
<%@page import="br.com.talles.ecommercebooks.domain.Category"%>
<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Criação de Livro</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css" />
	</head>
	<body>
		<%
			Result result = new Result();
			result = (Result) request.getAttribute("result");
			
			if (result != null) {
				Book book = new Book("", "", 0, 0, "", "", "", new Dimension(0.0, 0.0, 0.0, 0.0), new SaleParameterization(0, 0));
				
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
		
		<div class="container" id="app">
			<div class="row">
				<div class="u-full-width">
					<h1>Criação de Livro</h1>
					
					<form action="save" method="POST">
						<fieldset>
							<legend>Dados básicos</legend>
							<div>
								<label for="title">Titulo*: </label>
								<input name="title" id="title" value="<% out.print(book.getTitle()); %>" type="text">
							</div>
							<div>
								<label for="synopsis">Sinópse*: </label>
								<textarea name="synopsis" id="synopsis" value="<% out.print(book.getSynopsis()); %>" maxlength="255"></textarea>
							</div>
							<div>
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
							<legend>Grupor de Precificação</legend>
							<div>
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
						
						<button name="operation" value="SAVE" type="submit">Salvar</button>
						<small>Todos os campos marcados com * são obrigatórios.</small>
					</form>
				</div>
			</div>
		</div>
		<%
			}
		%>
						
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
	</body>
</html>
