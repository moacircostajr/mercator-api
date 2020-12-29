create table if not exists mercator_financeiro.classificacao(
    id int not null unique auto_increment,
    id_genero_classificacao int,
    classificacao varchar (255) not null,
    natureza enum('RECEITA','DESPESA'),
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id)
);

create table if not exists mercator_financeiro.lancamento(
    id bigint not null unique auto_increment,
    id_classificacao int not null,
    momento timestamp not null,
    valor float not null,
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_classificacao) references mercator_financeiro.classificacao(id)
);

create table if not exists mercator_financeiro.caixa(
    id bigint not null unique auto_increment,
    data date not null unique,
    receitas float,
    despesas float,
    saldo_dia float,
    saldo_acumulado float,
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id)
);
