create table if not exists mercator_representacao.empresas(
     id bigint not null unique auto_increment,
     nome_fantasia varchar (255) not null unique,
     razao_social varchar (255),
     cnpj varchar (20) null unique,
     cpf varchar (20) null unique,
     proprietario varchar (255) not null,
     endereco varchar (255) not null,
     telefones varchar (48) not null,
     email varchar (64),
     ultima_atualizacao datetime,
     versao integer,
     codigo varchar(30) unique not null,
     PRIMARY KEY (id)
);

alter table mercator_autenticacao.usuario add column (
    id_empresa bigint not null
);
alter table mercator_autenticacao.usuario add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);

alter table mercator_representacao.cliente add column (
    id_empresa bigint not null
    );
alter table mercator_representacao.cliente add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);

alter table mercator_representacao.pedido add column (
    id_empresa bigint not null
    );
alter table mercator_representacao.pedido add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);

alter table mercator_financeiro.classificacao add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.classificacao add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);

alter table mercator_financeiro.lancamento add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.lancamento add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);

alter table mercator_financeiro.caixa add column (
    id_empresa bigint not null
    );
alter table mercator_financeiro.caixa add FOREIGN KEY (id_empresa) references mercator_representacao.empresas(id);
