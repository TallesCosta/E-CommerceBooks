<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.talles.ecommercebooks.domain.PriceGroup"%>
<%@page import="br.com.talles.ecommercebooks.domain.PublishingCompany"%>
<%@page import="br.com.talles.ecommercebooks.domain.Author"%>
<%@page import="java.util.List"%>
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
	</head>
	<body>
		<div id="app">
			<h1>Criação de Livro</h1>
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
					}
				}
			%>
			
			<form action="save" method="POST">
				<fieldset>
					<legend>Dados básicos</legend>
					<div>
						<label for="title">Titulo</label>
						<input name="title" id="title" type="text">
					</div>
					<div>
						<label for="synopsis">Sinópse</label>
						<textarea name="synopsis" id="synopsis" maxlength="255"></textarea>
					</div>
					<div>
						<label for="author">Autor</label>
						<select name="author" id="author">
							<%
								if(result != null){
									List<Entity> authors = result.getEntities(Author.class.getSimpleName());
									for(Entity entity : authors){
										Author author = (Author) entity;
										out.println("<option value='" + author.getId() + "'>" + author.getName() + "</option>");
									}
								}
							%>
						</select>
					</div>
					<div>
						<label for="categories">Categoria</label>						
						<select name="categories" id="categories" multiple>
							<%
								if(result != null){
									List<Entity> categories = result.getEntities(Category.class.getSimpleName());
									for(Entity entity : categories){
										Category category = (Category) entity;
										if(category.getId() == 1){
											out.println("<option selected value='" + category.getId() + "'>" + category.getName() + "</option>");
										}else{
											out.println("<option value='" + category.getId() + "'>" + category.getName() + "</option>");
										}
									}
								}
							%>
						</select>
					</div>
					<div>
						<label for="publishingCompany">Editora</label>
						<select name="publishingCompany" id="publishingCompany">
							<%
								if(result != null){
									List<Entity> publishingCompanies = result.getEntities(PublishingCompany.class.getSimpleName());
									for(Entity entity : publishingCompanies){
										PublishingCompany publishingCompany = (PublishingCompany) entity;
										out.println("<option value='" + publishingCompany.getId() + "'>" + publishingCompany.getName() + "</option>");
									}
								}
							%>
						</select>
					</div>
					<div>
						<label for="edition">Edição</label>
						<input name="edition" id="edition" type="text">
					</div>
					<div>
						<label for="publicationYear">Ano</label>
						<input name="publicationYear" id="publicationYear" type="number" min="1889" max="2017">
					</div>
					<div>
						<label for="numberOfPages">Número de páginas</label>
						<input name="numberOfPages" id="numberOfPages" type="number">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Dimensões</legend>
					<div>
						<label for="height">Altura</label>
						<input name="height" id="height" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
					<div>
						<label for="widht">Largura</label>
						<input name="widht" id="widht" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
					<div>
						<label for="weight">Peso</label>
						<input name="weight" id="weight" type="number" step="0.001" min="0.001" max="10"> kg
					</div>
					<div>
						<label for="depth">Profundidade</label>
						<input name="depth" id="depth" type="number" step="0.01" min="0.01" max="100"> cm
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Identificação</legend>
					<div>
						<label for="isbn">ISBN</label>
						<input name="isbn" for="isbn" type="text">
					</div>
					<div>
						<label for="ean13">Código de barras</label>
						<input name="ean13" id="ean13" type="text">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Grupor de Precificação</legend>
					<div>
						<label for="priceGroup"></label>
						<select name="priceGroup" id="priceGroup">
							<%
								if(result != null){
									List<Entity> priceGroups = result.getEntities(PriceGroup.class.getSimpleName());
									for(Entity entity : priceGroups){
										PriceGroup priceGroup = (PriceGroup) entity;
										out.println("<option value='" + priceGroup.getId() + "'>" + priceGroup.getMarkup() + " %</option>");
									}
								}
							%>
						</select>
					</div>      
				</fieldset>
				
				<fieldset>
					<legend>Parâmetro de venda</legend>
					<div>
						<label for="minSaleLimit">Limite mínimo de vendas</label>
						<input name="minSaleLimit" id="minSaleLimit" type="number" min="1">
					</div>
					<div>
						<label for="periodicity">Período</label>
						<input name="periodicity" id="periodicity" type="number" min="1">
						<select name="classifierPeriod">
							<option value="m">Minuto</option>
							<option value="H">Hora</option>
							<option value="D">Dia</option>
							<option value="M">Mês</option>
							<option value="Y">Ano</option>
						  </select>
					</div>
				</fieldset>
				
				<button name="operation" value="SAVE" type="submit">Salvar</button>
			</form>
		</div>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
	</body>
</html>
