create table if not exists mercator_autenticacao.empresa(
                                                              id bigint not null unique auto_increment,
                                                              nome_fantasia varchar (255) not null unique,
                                                              razao_social varchar (255),
                                                              cnpj varchar (20) null unique,
                                                              ultima_atualizacao datetime,
                                                              versao integer,
                                                              codigo varchar(30) unique not null,
                                                              PRIMARY KEY (id)
);

alter table mercator_autenticacao.usuario add column (
    id_empresa bigint not null
    );
alter table mercator_autenticacao.usuario add FOREIGN KEY (id_empresa) references mercator_autenticacao.empresa(id);
