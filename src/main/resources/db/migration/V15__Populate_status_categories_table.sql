/*
	Populate status-category table
*/
INSERT INTO StatusCategories(enabled, activation, name, description)
VALUES (true, false, 'Fora de Mercado', 'Livros que se tornaram obsoletos e não atraem mais a atenção dos consumidores.'),
		(true, false, 'Equívoco', 'Houve um engano ao desabilitar este livro.'),
		(true, true, 'Nova Demanda', 'O mercado mudou e os consumidores querem comprar esse livro novamente.'),
		(true, true, 'Ordens Superiores', 'Um superior determinou que este livro deve ser ativado.');