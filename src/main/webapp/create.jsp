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
		<title>Cria��o de Livro</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<div id="app">
			<h1>Cria��o de Livro</h1>
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
			
			<form action="books/save" method="POST">
				<fieldset>
					<legend>Dados b�sicos</legend>
					<div>
						<label for="title">Titulo</label>
						<input name="title" id="title" type="text">
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
										out.println("<option value='" + category.getId() + "'>" + category.getName() + "</option>");
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
						<label for="edition">Edi��o</label>
						<input name="edition" id="edition" type="text">
					</div>
					<div>
						<label for="publicationYear">Ano</label>
						<input name="publicationYear" id="publicationYear" type="number" min="1889" max="2017">
					</div>
					<div>
						<label for="numberOfPages">N�mero de p�ginas</label>
						<input name="numberOfPages" id="numberOfPages" type="number">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Dimens�es</legend>
					<div>
						<label for="height">Altura</label>
						<input name="height" id="height" type="number">
					</div>
					<div>
						<label for="widht">Largura</label>
						<input name="widht" id="widht" type="text">
					</div>
					<div>
						<label for="weight">Peso</label>
						<input name="weight" id="weight" type="number">
					</div>
					<div>
						<label for="depth">Profundidade</label>
						<input name="depth" id="depth" type="text">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Identifica��o</legend>
					<div>
						<label for="isbn">ISBN</label>
						<input name="isbn" for="isbn" type="text">
					</div>
					<div>
						<label for="ean13">C�digo de barras</label>
						<input name="ean13" id="ean13" type="text">
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Grupor de Precifica��o</legend>
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
					<legend>Par�metro de venda</legend>
					<div>
						<label for="minSaleLimitS">Limite m�nimo de vendas</label>
						<input name="minSaleLimitS" id="minSaleLimitS" type="number" min="1">
					</div>
					<div>
						<label for="periodicity">Per�odo</label>
						<input name="periodicity" id="periodicity" type="number" min="1">
						<select name="classifierPeriod">
							<option value="m">Minuto</option>
							<option value="H">Hora</option>
							<option value="D">Dia</option>
							<option value="M">M�s</option>
							<option value="Y">Ano</option>
						  </select>
					</div>
				</fieldset>
				
				<button type="submit">Salvar</button>
			</form>
		</div>
		
		<script src="https://use.fontawesome.com/51922b6b29.js"></script>
	</body>
</html>
