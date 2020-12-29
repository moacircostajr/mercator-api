create table if not exists mercator_representacao.empresa(
                                                              id bigint not null unique auto_increment,
                                                              nome_fantasia varchar (255) not null unique,
                                                              razao_social varchar (255),
                                                              cnpj varchar (20) null unique,
                                                              cpf varchar (20) null unique,
                                                              proprietario varchar (255) not null,
                                                              endereco varchar (255) not null,
                                                              telefone varchar (48) not null,
                                                              email varchar (64),
                                                              ultima_atualizacao datetime,
                                                              versao integer,
                                                              codigo varchar(30) unique not null,
                                                              PRIMARY KEY (id)
);


alter table mercator_representacao.cliente add column (
    id_empresa bigint not null
    );
alter table mercator_representacao.cliente add FOREIGN KEY (id_empresa) references mercator_representacao.empresa(id);

alter table mercator_representacao.pedido add column (
    id_empresa bigint not null
    );
alter table mercator_representacao.pedido add FOREIGN KEY (id_empresa) references mercator_representacao.empresa(id);
