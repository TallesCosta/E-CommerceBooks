/*
	Populate activation-category table
*/
INSERT INTO StatusCategories(enabled, name, activationStatus, description)
VALUES (true, 'Nova Demanda', true, 'O mercado mudou e os consumidores querem comprar esse livro novamente.'),
		(true, 'Ordens Superiores', true, 'Um superior determinou que este livro deve ser ativado.'),
		(true, 'Fora de Mercado', false, 'Livros que se tornaram obsoletos e não atraem mais a atenção dos consumidores.'),
		(true, 'Ordens Superiores', false, 'Um superior determinou que este livro deveria ser desativado.');