CREATE TABLE tipo_produto(
	id_tProduto SERIAL,
	descricao_tProduto varchar(50),
	CONSTRAINT tProduto_PK PRIMARY KEY(id_tProduto)	
);

--drop table tipo_produto;

CREATE TABLE tipo_pagamento(
	id_tPagamento SERIAL,
	descricao_tPagamento varchar(50),
	CONSTRAINT tPagamento_PK PRIMARY KEY(id_tPagamento)	
);

CREATE TABLE produto(
	id_produto SERIAL,
	descricao_produto varchar(50),
	preco_produto real,
	id_tProduto integer,
	CONSTRAINT produto_PK PRIMARY KEY (id_produto),
	CONSTRAINT tProduto_FK FOREIGN KEY (id_tProduto) REFERENCES tipo_produto(id_tProduto)
);

CREATE TABLE cliente(
	id_cliente SERIAL,
	nome varchar(100),
	telefone varchar(13),
	endereco varchar(100),
	bairro varchar(50),
	cep varchar(10),
	CONSTRAINT cliente_PK PRIMARY KEY (id_cliente)
);

CREATE TABLE pedido(
	id_pedido SERIAL,
	data_pedido date,
	id_cliente integer,
	total real,
	desconto real,
	id_tPagamento integer,
	troco real,
	subTotal real,
	CONSTRAINT pedido_PK PRIMARY KEY (id_pedido),
	CONSTRAINT cliente_FK FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
	CONSTRAINT tPagamento_FK FOREIGN KEY (id_tPagamento) REFERENCES tipo_pagamento(id_tPagamento)
);

CREATE TABLE itens_pedido(
	id_pedido integer,
	id_produto integer,
	quantidade integer,
	CONSTRAINT pedido_FK FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
	CONSTRAINT produto_FK FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
	CONSTRAINT itensPedido_PK PRIMARY KEY (id_pedido,id_produto)
);
