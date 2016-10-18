create sequence configuracao.seq_pesquisa start with 1 nomaxvalue minvalue 1 nocycle nocache;

create table configuracao.pesquisa(
    seq_pesquisa number not null,
    dsc_pesquisa varchar2(200) not null,
    dsc_contexto varchar2(20) not null,
    bin_criterio clob not null,
    flg_execucao_automatica char not null,
    seq_usuario number not null
);
alter table configuracao.pesquisa add constraint pk_pesquisa primary key(seq_pesquisa);
ALTER TABLE configuracao.pesquisa add constraint ck_pesq_flg_execucao_auto check (flg_execucao_automatica in ('S','N'));