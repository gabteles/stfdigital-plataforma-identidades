create sequence corporativo.seq_pessoa start with 1 nomaxvalue minvalue 1 nocycle nocache;
create table corporativo.pessoa(
    seq_pessoa number not null,
    nom_pessoa varchar2(100) not null,
    cod_cpf VARCHAR2(11),
    cod_oab varchar2(20),
    dsc_email varchar2(30),
    dsc_telefone varchar2(20)
);
alter table corporativo.pessoa add constraint pk_pessoa primary key(seq_pessoa);

create table corporativo.orgao(
    seq_pessoa number not null,
    nom_pessoa varchar2(100) not null
);
alter table corporativo.orgao add constraint pk_orgao primary key(seq_pessoa);

create sequence corporativo.seq_associado start with 1 nomaxvalue minvalue 1 nocycle nocache;
create table corporativo.associado(
seq_associado number not null,
seq_pessoa number not null,
tip_associado varchar2(13) not null,
dsc_cargo_funcao varchar2(50),
seq_pessoa_orgao number not null
);
alter table corporativo.associado add constraint pk_associado primary key(seq_associado);
alter table corporativo.associado add constraint ck_asso_tip_associado check(tip_associado in ('ASSOCIADO', 'GESTOR', 'REPRESENTANTE'));
alter table corporativo.associado add constraint fk_orgao_asso foreign key(seq_pessoa_orgao) references corporativo.orgao(seq_pessoa); 
alter table corporativo.associado add constraint fk_pessoa_asso foreign key(seq_pessoa) references corporativo.pessoa(seq_pessoa);