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
<%@page import="java.util.Arrays"%>
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
		<title>Histórico do Livro</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			Book book = new Book("", "", 0, 0, "", "", "", 
					new Dimension(0.0, 0.0, 0.0, 0.0, 0L), new PriceGroup(0.0, 0L), 
					new PublishingCompany(0L), new SaleParameterization(0, 0, 0L),
					new ChangeStatus(new StatusCategory(0L), 0L), new Author(0L), 
					Arrays.asList(new Category(0L)), 0L);
			
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
			<h1 id="show-book">Histórico do Livro</h1>
			
			<fieldset>
				<legend>Dados básicos</legend>
				<div>
					<label>Titulo: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getTitle()); %> </span>
				</div>
				<div>
					<label>Sinópse: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getSynopsis()); %> </span>
				</div>
				<div>
					<input type="hidden" name="idAuthor" id="idAuthor" 
						   <% out.print("value='" + ((Book) book.getHistory().getEntity()).getAuthor().getId() + "'"); %> >
					<label>Autor: </label>
					<select name="author" id="author" disabled>
		<%	
			for(Entity entity : result.getEntities(Author.class.getSimpleName())){
				Author author = (Author) entity;
				out.print("<option value='" + author.getId() + "'>" + author.getName() + "</option>");
			}
		%>
					</select>
				</div>
				<div>
					<input type="hidden" name="idCategory" id="idCategory" 
						   <% 
								String value = "";
								for (Category c : ((Book) book.getHistory().getEntity()).getCategories()) {
									value += c.getId() + "-";
								}

								out.print("value='" + value.substring(0, value.length() - 1) + "'"); %> >
					<label>Categoria: </label>
					<select name="category" id="category" multiple disabled>
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
					<input type="hidden" name="idPublishingCompany" id="idPublishingCompany" 
						   <% out.print("value='" + ((Book) book.getHistory().getEntity()).getPublishingCompany().getId() + "'"); %> >
					<label>Editora: </label>
					<select name="publishingCompany" id="publishingCompany" disabled="">
		<%
			for(Entity entity : result.getEntities(PublishingCompany.class.getSimpleName())){
				PublishingCompany publishingCompany = (PublishingCompany) entity;
				out.print("<option value='" + publishingCompany.getId() + "'>" + publishingCompany.getName() + "</option>");
			}
		%>
					</select>
				</div>
				<div>
					<label>Edição: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getEdition()); %> </span>
				</div>
				<div>
					<label>Ano: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getPublicationYear()); %> </span>
				</div>
				<div>
					<label>Número de páginas: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getNumberOfPages()); %> </span>
				</div>
			</fieldset>

			<fieldset>
				<legend>Dimensões</legend>
				<div>
					<label>Altura: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getDimension().getHeight()); %> </span>
				</div>
				<div>
					<label>Largura: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getDimension().getWidht()); %> </span>
				</div>
				<div>
					<label>Peso: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getDimension().getWeight()); %> </span>
				</div>
				<div>
					<label>Profundidade: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getDimension().getDepth()); %> </span>
				</div>
			</fieldset>

			<fieldset>
				<legend>Identificação</legend>
				<div>
					<label>ISBN: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getIsbn()); %> </span>
				</div>
				<div>
					<label>Código de barras: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getEan13()); %> </span>
				</div>
			</fieldset>

			<fieldset>
				<legend>Grupo de Precificação</legend>
				<div>
					<input type="hidden" name="idPriceGroup" id="idPriceGroup" 
						   <% out.print("value='" + ((Book) book.getHistory().getEntity()).getPriceGroup().getId() + "'"); %> >
					<label>Porcentagem: </label>
					<select name="priceGroup" id="priceGroup" disabled>
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
					<label>Limite mínimo de vendas: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getSaleParameterization().getMinSaleLimit()); %> </span>
				</div>
				<div>
					<label>Periodicidade: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getSaleParameterization().getPeriodicity()); %> </span>
					<select name="classifierPeriod" id="classifierPeriod" disabled>
						<option value="m">Minuto(s)</option>
						<option value="H">Hora(s)</option>
						<option value="D">Dia(s)</option>
						<option value="M">Mês(es)</option>
						<option value="Y">Ano(s)</option>
					  </select>
				</div>
			</fieldset>

			<fieldset>
				<legend>Status de Mudança</legend>
				<div>
					<label>Justicativa: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getChangeStatus().getJustification()); %> </span>
				</div>
				<div>
					<label>Categoria de Ativação: </label>
					<span> <% out.print(((Book) book.getHistory().getEntity()).getChangeStatus().getStatusCategory().getName()); %> </span>
				</div>
			</fieldset>

			<a href="javascript:window.history.go(-1)">Voltar</a>
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
		</script>
		
	</body>
</html>
