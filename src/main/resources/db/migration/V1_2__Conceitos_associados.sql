create table corporativo.orgao(
    seq_pessoa number not null,
    nom_pessoa varchar2(100) not null
);
alter table corporativo.orgao add constraint corporativo.pk_orgao primary key(seq_pessoa);

create sequence corporativo.seq_associado start with 1 cache 1;
create table corporativo.associado(
seq_associado number not null,
seq_pessoa number not null,
tip_associado varchar2(13) not null,
dsc_cargo_funcao varchar2(50),
seq_pessoa_orgao number not null
);
alter table corporativo.associado add constraint corporativo.pk_associado primary key(seq_associado);
alter table corporativo.associado add constraint corporativo.ck_asso_tip_associado check(tip_associado in ('ASSOCIADO', 'GESTOR', 'REPRESENTANTE'));
alter table corporativo.associado add constraint corporativo.fk_orgao_asso foreign key(seq_pessoa_orgao) references corporativo.orgao(seq_pessoa); 
alter table corporativo.associado add constraint corporativo.fk_pessoa_asso foreign key(seq_pessoa) references corporativo.pessoa(seq_pessoa);