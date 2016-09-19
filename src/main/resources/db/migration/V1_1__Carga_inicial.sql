-- 9 +/- SELECT COUNT(*) FROM UAA.TIPO_INFORMACAO;     
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES
(1, 'PETICAO'),
(2, 'PROCESSO'),
(3, 'DASHLET'),
(4, 'NOTIFICACAO'),
(5, 'TAREFA'),
(6, 'ASSOCIADO'),
(7, 'CLASSE'),
(8, 'PERMISSAO'),
(9, 'DASHBOARD');     

-- 2 +/- SELECT COUNT(*) FROM UAA.GRUPO;               
INSERT INTO UAA.GRUPO(SEQ_GRUPO, NOM_GRUPO, TIP_GRUPO) VALUES
(1, 'usuario', 'CONFIGURACAO'),
(2, 'SEJ', 'SETOR');   

-- 23 +/- SELECT COUNT(*) FROM UAA.PAPEL;              
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES
(1, 'peticionador', NULL),
(2, 'recebedor', NULL),
(3, 'representante', NULL),
(5, 'autuador', 2),
(6, 'distribuidor', 2),
(7, 'cartoraria', 2),
(8, 'gestor-autuacao', 2),
(11, 'gestor-recebimento', NULL),
(9, 'gestor-orgao', NULL),
(10, 'gestor-cadastro', NULL),
(12, 'cidadao', NULL),
(13, 'advogado', NULL),
(14, 'preautuador-recursal', 2),
(4, 'preautuador-originario', 2),
(15, 'autuador-recursal-criminal-eleitoral', 2),
(16, 'analista-pressupostos', 2),
(17, 'revisor-processo-recursal-inapto', 2),
(18, 'analista-repercussao-geral', 2),
(19, 'revisor-repercussao-geral', 2),
(20, 'autuador-recursal', 2),
(21, 'organizador-pecas', 2),
(22, 'gestor-modelo', 2),
(23, 'representante-tribunal', 2);   

-- 39 +/- SELECT COUNT(*) FROM UAA.SEGMENTO;           
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES
(1, 'ELETRONICA', 1),
(2, 'FISICA', 1),
(3, 'ORGAO', 1),
(4, 'TAREFA', 3),
(5, 'PETICIONADO', 3),
(6, 'GESTAO', 3),
(7, 'PREAUTUACAO', 5),
(8, 'AUTUACAO', 5),
(9, 'DISTRIBUICAO', 5),
(10, 'DEVOLUCAO', 5),
(11, 'ASSINATURA', 5),
(12, 'CIDADAO', 7),
(13, 'ADVOGADO', 7),
(14, 'INTERNO', 7),
(15, 'UI', 4),
(16, 'peticionamento', 9),
(17, 'recebimento', 9),
(18, 'gestao-recebimento', 9),
(19, 'distribuicao', 9),
(20, 'autuacao', 9),
(21, 'gestao-autuacao', 9),
(22, 'gestao-orgao', 9),
(23, 'gestao-cadastro', 9),
(24, 'assumir', 5),
(25, 'delegacao', 5),
(26, 'PREAUTUACAO-RECURSAL', 5),
(27, 'PREAUTUACAO-RECURSAL-CRIMINAL-ELEITORAL', 5),
(28, 'ANALISE-PRESSUPOSTOS', 5),
(29, 'REVISAO-PROCESSO-RECURSAL-INAPTO', 5),
(30, 'ANALISE-REPERCUSSAO-GERAL', 5),
(31, 'REVISAO-REPERCUSSAO-GERAL', 5),
(32, 'AUTUACAO-PROCESSO-RECURSAL', 5),
(33, 'ORGANIZAR-PECAS', 5),
(34, 'INSERIR-PECAS', 5),
(35, 'EXCLUIR-PECAS', 5),
(36, 'DIVIDIR-PECA', 5),
(37, 'UNIR-PECAS', 5),
(38, 'EDITAR-PECA', 5),
(39, 'JUNTAR-PECAS', 5);

-- 50 +/- SELECT COUNT(*) FROM UAA.RECURSO;            
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES
(1, 'peticionar', 'ACAO'),
(2, 'registrar-remessa', 'ACAO'),
(3, 'peticionar-orgao', 'ACAO'),
(4, 'preautuar-originario', 'ACAO'),
(5, 'autuar-originario', 'ACAO'),
(6, 'distribuir-processo', 'ACAO'),
(7, 'preparar-oficio-devolucao', 'ACAO'),
(8, 'minhas-tarefas', 'DASHLET'),
(9, 'minhas-peticoes', 'DASHLET'),
(10, 'grafico-gestao', 'DASHLET'),
(11, 'notificar-ui', 'NOTIFICACAO'),
(12, 'do-nothing-long', 'ACAO'),
(13, 'do-nothing', 'ACAO'),
(14, 'dummy-action', 'ACAO'),
(15, 'devolver-remessa', 'ACAO'),
(16, 'registrar-associado', 'ACAO'),
(17, 'configurar-permissao', 'ACAO'),
(19, 'peticionamento', 'DASHBOARD'),
(20, 'recebimento', 'DASHBOARD'),
(21, 'gestao-recebimento', 'DASHBOARD'),
(22, 'distribuicao', 'DASHBOARD'),
(23, 'autuacao', 'DASHBOARD'),
(24, 'gestao-autuacao', 'DASHBOARD'),
(25, 'gestao-orgao', 'DASHBOARD'),
(26, 'gestao-cadastro', 'DASHBOARD'),
(27, 'assumir-tarefa', 'ACAO'),
(28, 'delegar-tarefa', 'ACAO'),
(29, 'preautuar-recursal', 'ACAO'),
(30, 'autuar-recursal-criminal-eleitoral', 'ACAO'),
(31, 'analisar-pressupostos-formais', 'ACAO'),
(32, 'revisar-pressupostos-formais', 'ACAO'),
(33, 'analisar-repercussao-geral', 'ACAO'),
(34, 'revisar-repercussao-geral', 'ACAO'),
(35, 'autuar-recursal', 'ACAO'),
(36, 'organizar-pecas', 'ACAO'),
(37, 'inserir-pecas', 'ACAO'),
(38, 'excluir-pecas', 'ACAO'),
(39, 'dividir-peca', 'ACAO'),
(40, 'unir-pecas', 'ACAO'),
(41, 'juntar-pecas', 'ACAO'),
(42, 'editar-peca', 'ACAO'),
(43, 'salvar-pesquisa-avancada', 'ACAO'),
(44, '9', 'DASHBOARD'),
(45, 'criar-modelo', 'ACAO'),
(46, 'gerar-texto', 'ACAO'),
(47, 'finalizar-texto-devolucao', 'ACAO'),
(48, 'consultar-processo-envio', 'ACAO'),
(49, 'enviar-processo', 'ACAO'),
(50, 'excluir-pesquisa-avancada', 'ACAO'),
(51, 'gerar-acronimos', 'ACAO'),
(52, 'upload-documento-assinado', 'ACAO'),
(53, 'salvar-documentos', 'ACAO'),
(54, 'cadastrar-pessoas', 'ACAO'),
(55, 'pesquisar-processos', 'PESQUISA'),
(56, 'sugerir-processos', 'PESQUISA'),
(57, 'concluir-texto', 'ACAO'),
(58, 'assinar-oficio-devolucao', 'ACAO'),
(59, 'assinar-texto', 'ACAO'),
(60, 'upload-documento', 'ACAO'),
(61, 'gestao-modelos', 'DASHBOARD'),
(62, 'minhas-remessas', 'DASHLET'),
(63, 'grafico-remessas', 'DASHLET'),
(64, 'modelos', 'DASHLET');

-- 69 +/- SELECT COUNT(*) FROM UAA.INFORMACAO;         
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES
(1, 7, 12, 'HC'),
(2, 7, 13, 'AC'),
(3, 7, 13, 'ACO'),
(4, 7, 13, 'ADC'),
(5, 7, 13, 'ADI'),
(6, 7, 13, 'ADO'),
(7, 7, 13, 'ADPF'),
(8, 7, 13, 'AImp'),
(9, 7, 13, 'AO'),
(10, 7, 13, 'AOE'),
(11, 7, 13, 'AR'),
(12, 7, 13, 'AS'),
(13, 7, 13, 'CC'),
(14, 7, 13, 'EI'),
(15, 7, 13, 'EL'),
(16, 7, 13, 'HC'),
(17, 7, 13, 'HD'),
(18, 7, 13, 'IF'),
(19, 7, 13, 'MI'),
(20, 7, 13, 'MS'),
(21, 7, 13, 'PSV'),
(22, 7, 13, 'Pet'),
(23, 7, 13, 'Rcl'),
(24, 7, 13, 'RvC'),
(25, 7, 13, 'SL'),
(26, 7, 13, 'SS'),
(27, 7, 13, 'STA'),
(28, 7, 14, 'AC'),
(29, 7, 14, 'ACO'),
(30, 7, 14, 'ADC'),
(31, 7, 14, 'ADI'),
(32, 7, 14, 'ADO'),
(33, 7, 14, 'ADPF'),
(34, 7, 14, 'AI'),
(35, 7, 14, 'AImp'),
(36, 7, 14, 'AO'),
(37, 7, 14, 'AOE'),
(38, 7, 14, 'AP'),
(39, 7, 14, 'AR'),
(40, 7, 14, 'ARE'),
(41, 7, 14, 'AS'),
(42, 7, 14, 'CC'),
(43, 7, 14, 'Cm'),
(44, 7, 14, 'EI'),
(45, 7, 14, 'EL'),
(46, 7, 14, 'EP'),
(47, 7, 14, 'Ext'),
(48, 7, 14, 'HC'),
(49, 7, 14, 'HD'),
(50, 7, 14, 'IF'),
(51, 7, 14, 'Inq'),
(52, 7, 14, 'MI'),
(53, 7, 14, 'MS'),
(54, 7, 14, 'OACO'),
(55, 7, 14, 'PPE'),
(56, 7, 14, 'PSV'),
(57, 7, 14, 'Pet'),
(58, 7, 14, 'RC'),
(59, 7, 14, 'RE'),
(60, 7, 14, 'RHC'),
(61, 7, 14, 'RHD'),
(62, 7, 14, 'RMI'),
(63, 7, 14, 'RMS'),
(64, 7, 14, 'Rcl'),
(65, 7, 14, 'RvC'),
(66, 7, 14, 'SEC'),
(67, 7, 14, 'SL'),
(68, 7, 14, 'SS'),
(69, 7, 14, 'STA'); 

-- 3 +/- SELECT COUNT(*) FROM UAA.SETOR
INSERT INTO UAA.SETOR(COD_SETOR, NOM_SETOR, SIG_SETOR) VALUES
('600000627', 'SECRETARIA JUDICIÁRIA', 'SEJ'),
('600000629', 'COORDENADORIA DE PROCESSAMENTO INICIAL', 'CPIN'),
('600000680', 'SEÇÃO DE RECEBIMENTO E DISTRIBUIÇÃO DE RECURSOS', 'SRDR');

-- 26 +/- SELECT COUNT(*) FROM UAA.USUARIO;            
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES
(1, 'peticionador', 16, 'Peticionador', NULL),
(3, 'representante', 18, 'Representante', NULL),
(11, 'gestor-recebimento', 26, 'Gestor Recebimento', NULL),
(13, 'gestor-cadastro', 28, 'Gestor Cadastro', NULL),
(14, 'cidadao', 29, 'Cidadao', NULL),
(5, 'autuador', 20, 'Autuador', 600000627),
(6, 'distribuidor', 21, 'Distribuidor', 600000627),
(7, 'cartoraria', 22, 'Cartoraria', 600000627),
(8, 'gestor-autuacao', 23, 'Gestor Autuacao', 600000627),
(9, 'usuario-teste', 24, 'Usuario Teste', 600000627),
(10, 'TOMAS.GODOI', 25, 'TOMAS GODOI', 600000629),
(12, 'gestor-orgao', 27, 'Gestor Orgao', 600000629),
(2, 'recebedor', 17, 'Recebedor', 600000680),
(15, 'preautuador-recursal', 30, 'Preautuador Recursal', 600000627),
(4, 'preautuador-originario', 19, 'Preautuador Originario', 600000627),
(16, 'autuador-recursal-ce', 31, 'Autuador Recursal Ce', 600000627),
(17, 'analista-pressupostos', 32, 'Analista Pressupostos', 600000627),
(18, 'revisor-processo-ri', 33, 'Revisor Processo Ri', 600000627),
(19, 'analista-repercussao-g', 34, 'Analista Repercussao G', 600000627),
(20, 'revisor-repercussao-g', 35, 'Revisor Repercussao G', 600000627),
(21, 'autuador-recursal', 36, 'Autuador Recursal', 600000627),
(22, 'organizador-pecas', 37, 'Organizador Pecas', 600000627),
(23, 'gestor-modelo', 38, 'Gestor Modelo', 600000627),
(24, 'gestor-modelo-01', 39, 'Gestor Modelo 01', 600000627),
(25, 'gestor-modelo-02', 40, 'Gestor Modelo 02', 600000627),
(26, 'representante-tribunal', 41, 'Representante Tribunal', 600000627);             

-- 28 +/- SELECT COUNT(*) FROM UAA.PAPEL_USUARIO;      
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(5, 8),
(5, 9),
(11, 11),
(9, 12),
(10, 13),
(12, 14),
(13, 1),
(13, 3),
(14, 15),
(15, 16),
(16, 17),
(17, 18),
(18, 19),
(19, 20),
(20, 21),
(21, 22),
(22, 23),
(22, 24),
(22, 25),
(23, 26);              

-- 28 +/- SELECT COUNT(*) FROM UAA.GRUPO_USUARIO;      
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 26); 

-- 3 +/- SELECT COUNT(*) FROM UAA.GRUPO_RECURSO;       
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES
(1, 11),
(1, 43),
(1, 50),
(1, 55),
(1, 56);             

-- 91 +/- SELECT COUNT(*) FROM UAA.PAPEL_RECURSO;      
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES
(1, 1),
(1, 52),
(1, 53),
(1, 54),
(12, 1),
(2, 2),
(3, 3),
(3, 52),
(3, 53),
(3, 54),
(9, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(4, 8),
(5, 8),
(6, 8),
(7, 8),
(11, 8),
(14, 8),
(15, 8),
(16, 8),
(17, 8),
(18, 8),
(19, 8),
(20, 8),
(21, 8),
(1, 9),
(2, 9),
(3, 9),
(9, 9),
(12, 9),
(13, 9),
(8, 10),
(11, 15),
(9, 16),
(10, 16),
(8, 17),
(1, 19),
(3, 19),
(9, 19),
(2, 20),
(11, 21),
(6, 22),
(4, 23),
(5, 23),
(7, 23),
(8, 23),
(14, 23),
(15, 23),
(16, 23),
(17, 23),
(18, 23),
(19, 23),
(20, 23),
(21, 23),
(8, 24),
(9, 25),
(9, 26),
(10, 26),
(4, 27),
(5, 27),
(6, 27),
(7, 27),
(8, 27),
(11, 27),
(14, 27),
(15, 27),
(16, 27),
(17, 27),
(18, 27),
(19, 27),
(20, 27),
(21, 27),
(8, 28),
(14, 29),
(15, 30),
(16, 31),
(17, 32),
(18, 33),
(19, 34),
(20, 35),
(21, 36),
(21, 37),
(21, 38),
(21, 39),
(21, 40),
(21, 41),
(21, 42),
(22, 44),
(22, 45),
(7, 46),
(7, 47),
(23, 48),
(23, 49),
(23, 51),
(14, 15),
(4, 15),
(7, 57),
(11, 58),
(11, 59),
(11, 60),
(21, 52),
(21, 53),
(22, 61),
(11, 62),
(11, 63),
(2, 62),
(22, 64);

-- 1 +/- SELECT COUNT(*) FROM UAA.USUARIO_RECURSO;      
INSERT INTO UAA.USUARIO_RECURSO(SEQ_USUARIO, SEQ_RECURSO) VALUES
(9, 51);