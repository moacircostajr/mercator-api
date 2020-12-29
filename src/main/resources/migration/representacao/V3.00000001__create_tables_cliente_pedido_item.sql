create table if not exists mercator_representacao.cliente(
    id bigint not null unique auto_increment,
    nome varchar (255) not null unique,
    cnpj varchar (20) null unique,
    cpf varchar (20) null unique,
    endereco varchar (255) not null,
    telefones varchar (48) not null,
    email varchar (64),
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id)
);

create table if not exists mercator_representacao.pedido(
    id bigint not null unique auto_increment,
    id_cliente bigint not null,
    momento timestamp not null,
    valor_total float,
    entregue boolean not null default false,
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) references mercator_representacao.cliente(id)
);

create table if not exists mercator_representacao.item(
    id bigint not null unique auto_increment,
    id_pedido bigint not null,
    discriminacao varchar (255) not null,
    quantidade float not null ,
    unidade varchar(60) not null,
    valor float not null,
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_pedido) references mercator_representacao.pedido(id)
);
