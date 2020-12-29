create table if not exists mercator_autenticacao.usuario(
    id bigint not null unique auto_increment,
    email varchar (255) not null unique,
    senha varchar (128) not null,
    nome varchar (64) not null,
    sobrenome varchar (64),
    ultima_atualizacao datetime,
    versao integer,
    codigo varchar(30) unique not null,
    PRIMARY KEY (id)
)
