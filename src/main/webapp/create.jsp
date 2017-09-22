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
			<form action="books/save" method="POST">
				<fieldset>
					<legend>Dados básicos</legend>
					<div>
						<label for="title">Titulo</label>
						<input name="title" id="title" type="text">
					</div>
					<div>
						<label for="">Autor</label>
						<select name="" id="">
							<option value="">selecione...</option>
							<option value="1">DOSTOIEVSKI, Fiodor</option>
							<option value="2">GOGÓL, Nikolai</option>
							<option value="3">TOLSTÓI, Liev</option>
						</select>
					</div>
					<div>
						<label for="">Categoria</label>
						<select name="listBox" id="listBox" multiple>
							<option value="1">Romance</option>
							<option value="2">Suspense</option>
							<option value="3">Terror</option>
						</select>
					</div>
					<div>
						<label for="">Editora</label>
						<select name="" id="">
							<option value="">selecione...</option>
							<option value="1">Editora 34</option>
							<option value="2">Novatec</option>
							<option value="3">Cada do código</option>
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
						<label for=""></label>
						<select name="" id="">
							<option value="">selecione...</option>
							<option value="1">P1</option>
							<option value="2">P2</option>
							<option value="3">P3</option>
						</select>
					</div>      
				</fieldset>
				
				<fieldset>
					<legend>Parâmetro de venda</legend>
					<div>
						<label for="minSaleLimitS">Limite mínimo de vendas</label>
						<input name="minSaleLimitS" id="minSaleLimitS" type="number" min="1">
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
				
				<button type="submit">Salvar</button>
			</form>
		</div>
	</body>
</html>
