CREATE SCHEMA UAA;      

CREATE SEQUENCE UAA.SEQ_RECURSO START WITH 1 CACHE 1;  
CREATE SEQUENCE UAA.SEQ_USUARIO START WITH 27 CACHE 1; 
CREATE SEQUENCE UAA.SEQ_TIPO_INFORMACAO START WITH 70 CACHE 1;         
CREATE SEQUENCE UAA.SEQ_INFORMACAO START WITH 1 CACHE 1;               
CREATE SEQUENCE UAA.SEQ_PAPEL START WITH 1 CACHE 1;    
CREATE SEQUENCE UAA.SEQ_GRUPO START WITH 1 CACHE 1;    
CREATE SEQUENCE UAA.SEQ_SEGMENTO START WITH 1 CACHE 1; 

CREATE TABLE UAA.TIPO_INFORMACAO(
    SEQ_TIPO_INFORMACAO NUMBER NOT NULL,
    NOM_TIPO_INFORMACAO VARCHAR2(100) NOT NULL
); 
ALTER TABLE UAA.TIPO_INFORMACAO ADD CONSTRAINT UAA.PK_TIPO_INFORMACAO PRIMARY KEY(SEQ_TIPO_INFORMACAO);         

CREATE TABLE UAA.PAPEL(
    SEQ_PAPEL NUMBER NOT NULL,
    NOM_PAPEL VARCHAR2(100) NOT NULL,
    SEQ_GRUPO NUMBER
);        
ALTER TABLE UAA.PAPEL ADD CONSTRAINT UAA.PK_PAPEL PRIMARY KEY(SEQ_PAPEL);       

CREATE TABLE UAA.SEGMENTO(
    SEQ_SEGMENTO NUMBER NOT NULL,
    NOM_SEGMENTO VARCHAR2(100) NOT NULL,
    SEQ_TIPO_INFORMACAO NUMBER NOT NULL
);            
ALTER TABLE UAA.SEGMENTO ADD CONSTRAINT UAA.PK_SEGMENTO PRIMARY KEY(SEQ_SEGMENTO);              
               
CREATE TABLE UAA.RECURSO(
    SEQ_RECURSO NUMBER NOT NULL,
    NOM_RECURSO VARCHAR2(50) NOT NULL,
    TIP_RECURSO VARCHAR2(20) NOT NULL
);  
ALTER TABLE UAA.RECURSO ADD CONSTRAINT UAA.PK_RECURSO PRIMARY KEY(SEQ_RECURSO); 

CREATE TABLE UAA.GRUPO(
    SEQ_GRUPO NUMBER NOT NULL,
    NOM_GRUPO VARCHAR2(100) NOT NULL,
    TIP_GRUPO VARCHAR2(20) NOT NULL
);         
ALTER TABLE UAA.GRUPO ADD CONSTRAINT UAA.PK_GRUPO PRIMARY KEY(SEQ_GRUPO);       

CREATE TABLE UAA.GRUPO_USUARIO(
    SEQ_GRUPO NUMBER NOT NULL,
    SEQ_USUARIO NUMBER NOT NULL
);            
ALTER TABLE UAA.GRUPO_USUARIO ADD CONSTRAINT UAA.PK_GRUPO_USUARIO PRIMARY KEY(SEQ_GRUPO, SEQ_USUARIO);          

CREATE TABLE UAA.INFORMACAO(
    SEQ_INFORMACAO NUMBER NOT NULL,
    SEQ_TIPO_INFORMACAO NUMBER NOT NULL,
    SEQ_SEGMENTO NUMBER,
    COD_IDENTIDADE VARCHAR2(30) NOT NULL
);             
ALTER TABLE UAA.INFORMACAO ADD CONSTRAINT UAA.PK_INFORMACAO PRIMARY KEY(SEQ_INFORMACAO);        

CREATE TABLE UAA.PAPEL_USUARIO(
    SEQ_PAPEL NUMBER NOT NULL,
    SEQ_USUARIO NUMBER NOT NULL
);            
ALTER TABLE UAA.PAPEL_USUARIO ADD CONSTRAINT UAA.PK_PAPEL_USUARIO PRIMARY KEY(SEQ_PAPEL, SEQ_USUARIO);          

CREATE TABLE UAA.USUARIO(
    SEQ_USUARIO NUMBER NOT NULL,
    SIG_USUARIO VARCHAR2(30) NOT NULL,
    SEQ_PESSOA NUMBER NOT NULL,
    NOM_PESSOA VARCHAR2(100) NOT NULL,
    COD_CPF_PESSOA VARCHAR2(11),
    COD_OAB_PESSOA VARCHAR2(20),
    DSC_EMAIL_PESSOA VARCHAR2(30),
    DSC_TELEFONE_PESSOA VARCHAR2(20),
    COD_SETOR_LOTACAO NUMBER
);          
ALTER TABLE UAA.USUARIO ADD CONSTRAINT UAA.PK_USUARIO PRIMARY KEY(SEQ_USUARIO); 

CREATE TABLE UAA.USUARIO_RECURSO(
    SEQ_USUARIO NUMBER NOT NULL,
    SEQ_RECURSO NUMBER NOT NULL
);        
ALTER TABLE UAA.USUARIO_RECURSO ADD CONSTRAINT UAA.PK_USUARIO_RECURSO PRIMARY KEY(SEQ_USUARIO, SEQ_RECURSO);    

CREATE TABLE UAA.GRUPO_RECURSO(
    SEQ_GRUPO NUMBER NOT NULL,
    SEQ_RECURSO NUMBER NOT NULL
);            
ALTER TABLE UAA.GRUPO_RECURSO ADD CONSTRAINT UAA.PK_GRUPO_RECURSO PRIMARY KEY(SEQ_GRUPO, SEQ_RECURSO);          

CREATE TABLE UAA.PAPEL_RECURSO(
    SEQ_PAPEL NUMBER NOT NULL,
    SEQ_RECURSO NUMBER NOT NULL
);            
ALTER TABLE UAA.PAPEL_RECURSO ADD CONSTRAINT UAA.PK_PAPEL_RECURSO PRIMARY KEY(SEQ_PAPEL, SEQ_RECURSO);          

CREATE TABLE UAA.SETOR(
    COD_SETOR NUMBER NOT NULL,
    NOM_SETOR VARCHAR2(100) NOT NULL,
    SIG_SETOR VARCHAR2(30) NOT NULL
);
ALTER TABLE UAA.SETOR ADD CONSTRAINT UAA.PK_SETOR PRIMARY KEY(COD_SETOR);

ALTER TABLE UAA.GRUPO ADD CONSTRAINT UAA.CK_GRUP_TIP_GRUPO CHECK(TIP_GRUPO IN('COMISSAO', 'CONFIGURACAO', 'GRUPO_TRABALHO', 'ORGAO_CONVENIADO', 'SETOR', 'TRIBUNAL'));  
ALTER TABLE UAA.RECURSO ADD CONSTRAINT UAA.CK_RECU_TIP_RECURSO CHECK(TIP_RECURSO IN('ACAO', 'DASHLET', 'DASHBOARD', 'NOTIFICACAO', 'PESQUISA', 'TAREFA'));              
ALTER TABLE UAA.GRUPO ADD CONSTRAINT UAA.UK_GRUP_NOM_GRUPO UNIQUE(NOM_GRUPO, TIP_GRUPO);        
ALTER TABLE UAA.TIPO_INFORMACAO ADD CONSTRAINT UAA.UK_TIIN_NOM_TIPO_INFORMACAO UNIQUE(NOM_TIPO_INFORMACAO);     
ALTER TABLE UAA.SEGMENTO ADD CONSTRAINT UAA.UK_SEGM_NOM_SEGMENTO UNIQUE(NOM_SEGMENTO, SEQ_TIPO_INFORMACAO);     
ALTER TABLE UAA.USUARIO ADD CONSTRAINT UAA.UK_USUA_SIG_USUARIO UNIQUE(SIG_USUARIO);             
ALTER TABLE UAA.RECURSO ADD CONSTRAINT UAA.UK_RECU_NOM_RECURSO UNIQUE(NOM_RECURSO, TIP_RECURSO);
ALTER TABLE UAA.PAPEL ADD CONSTRAINT UAA.UK_PAPE_NOM_PAPEL UNIQUE(NOM_PAPEL);   
ALTER TABLE UAA.INFORMACAO ADD CONSTRAINT UAA.UK_INFO_SEQ_TIPO_INFORMACAO UNIQUE(SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE);            
ALTER TABLE UAA.USUARIO ADD CONSTRAINT UAA.FK_SETOR_USUA FOREIGN KEY(COD_SETOR_LOTACAO) REFERENCES UAA.SETOR(COD_SETOR);        
ALTER TABLE UAA.GRUPO_RECURSO ADD CONSTRAINT UAA.FK_GRUPO_GRRE FOREIGN KEY(SEQ_GRUPO) REFERENCES UAA.GRUPO(SEQ_GRUPO);           
ALTER TABLE UAA.INFORMACAO ADD CONSTRAINT UAA.FK_TIPO_INFORMACAO_INFO FOREIGN KEY(SEQ_TIPO_INFORMACAO) REFERENCES UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO);      
ALTER TABLE UAA.SEGMENTO ADD CONSTRAINT UAA.FK_TIPO_INFORMACAO_SEGM FOREIGN KEY(SEQ_TIPO_INFORMACAO) REFERENCES UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO);        
ALTER TABLE UAA.PAPEL_USUARIO ADD CONSTRAINT UAA.FK_PAPEL_PAUS FOREIGN KEY(SEQ_PAPEL) REFERENCES UAA.PAPEL(SEQ_PAPEL);           
ALTER TABLE UAA.GRUPO_USUARIO ADD CONSTRAINT UAA.FK_GRUPO_GRUS FOREIGN KEY(SEQ_GRUPO) REFERENCES UAA.GRUPO(SEQ_GRUPO);           
ALTER TABLE UAA.PAPEL_RECURSO ADD CONSTRAINT UAA.FK_PAPEL_PARE FOREIGN KEY(SEQ_PAPEL) REFERENCES UAA.PAPEL(SEQ_PAPEL);           
ALTER TABLE UAA.INFORMACAO ADD CONSTRAINT UAA.FK_SEGMENTO_INFO FOREIGN KEY(SEQ_SEGMENTO) REFERENCES UAA.SEGMENTO(SEQ_SEGMENTO);  
ALTER TABLE UAA.PAPEL_USUARIO ADD CONSTRAINT UAA.FK_USUARIO_PAUS FOREIGN KEY(SEQ_USUARIO) REFERENCES UAA.USUARIO(SEQ_USUARIO);   
ALTER TABLE UAA.USUARIO_RECURSO ADD CONSTRAINT UAA.FK_USUARIO_USRE FOREIGN KEY(SEQ_USUARIO) REFERENCES UAA.USUARIO(SEQ_USUARIO); 
ALTER TABLE UAA.GRUPO_RECURSO ADD CONSTRAINT UAA.FK_RECURSO_GRRE FOREIGN KEY(SEQ_RECURSO) REFERENCES UAA.RECURSO(SEQ_RECURSO);   
ALTER TABLE UAA.USUARIO_RECURSO ADD CONSTRAINT UAA.FK_RECURSO_USRE FOREIGN KEY(SEQ_RECURSO) REFERENCES UAA.RECURSO(SEQ_RECURSO); 
ALTER TABLE UAA.GRUPO_USUARIO ADD CONSTRAINT UAA.FK_USUARIO_GRUS FOREIGN KEY(SEQ_USUARIO) REFERENCES UAA.USUARIO(SEQ_USUARIO);   
ALTER TABLE UAA.PAPEL_RECURSO ADD CONSTRAINT UAA.FK_RECURSO_PARE FOREIGN KEY(SEQ_RECURSO) REFERENCES UAA.RECURSO(SEQ_RECURSO);   
ALTER TABLE UAA.SETOR ADD CONSTRAINT UAA.UK_SETO_SIG_SETOR UNIQUE(SIG_SETOR);
ALTER TABLE UAA.SETOR ADD CONSTRAINT UAA.UK_SETO_NOM_SETOR UNIQUE(NOM_SETOR);

create schema corporativo;
create sequence corporativo.seq_pessoa start with 1 cache 1;
create table corporativo.pessoa(
    seq_pessoa number not null,
    nom_pessoa varchar2(100) not null,
    cod_cpf VARCHAR2(11),
    cod_oab varchar2(20),
    dsc_email varchar2(30),
    dsc_telefone varchar2(20)
);
alter table corporativo.pessoa add constraint corporativo.pk_pessoa primary key(seq_pessoa);