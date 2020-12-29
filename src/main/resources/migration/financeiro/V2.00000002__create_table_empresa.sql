create table if not exists mercator_financeiro.empresa(
                                                              id bigint not null unique auto_increment,
                                                              nome_fantasia varchar (255) not null unique,
                                                              razao_social varchar (255),
                                                              cnpj varchar (20) null unique,
                                                              endereco varchar (255) not null,
                                                              telefone varchar (48) not null,
                                                              ultima_atualizacao datetime,
                                                              versao integer,
                                                              codigo varchar(30) unique not null,
                                                              PRIMARY KEY (id)
);

alter table mercator_financeiro.classificacao add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.classificacao add FOREIGN KEY (id_empresa) references mercator_financeiro.empresa(id);

alter table mercator_financeiro.lancamento add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.lancamento add FOREIGN KEY (id_empresa) references mercator_financeiro.empresa(id);

alter table mercator_financeiro.caixa add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.caixa add FOREIGN KEY (id_empresa) references mercator_financeiro.empresa(id);
