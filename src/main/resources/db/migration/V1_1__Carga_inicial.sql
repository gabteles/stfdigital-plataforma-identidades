-- 9 +/- SELECT COUNT(*) FROM UAA.TIPO_INFORMACAO;     
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (1, 'PETICAO');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (2, 'PROCESSO');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (3, 'DASHLET');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (4, 'NOTIFICACAO');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (5, 'TAREFA');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (6, 'ASSOCIADO');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (7, 'CLASSE');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (8, 'PERMISSAO');
INSERT INTO UAA.TIPO_INFORMACAO(SEQ_TIPO_INFORMACAO, NOM_TIPO_INFORMACAO) VALUES (9, 'DASHBOARD');     

-- 2 +/- SELECT COUNT(*) FROM UAA.GRUPO;               
INSERT INTO UAA.GRUPO(SEQ_GRUPO, NOM_GRUPO, TIP_GRUPO) VALUES (1, 'usuario', 'CONFIGURACAO');
INSERT INTO UAA.GRUPO(SEQ_GRUPO, NOM_GRUPO, TIP_GRUPO) VALUES (2, 'SEJ', 'SETOR');   

-- 23 +/- SELECT COUNT(*) FROM UAA.PAPEL;              
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (1, 'peticionador', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (2, 'recebedor', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (3, 'representante', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (5, 'autuador', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (6, 'distribuidor', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (7, 'cartoraria', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (8, 'gestor-autuacao', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (11, 'gestor-recebimento', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (9, 'gestor-orgao', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (10, 'gestor-cadastro', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (12, 'cidadao', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (13, 'advogado', NULL);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (14, 'preautuador-recursal', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (4, 'preautuador-originario', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (15, 'autuador-recursal-criminal-eleitoral', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (16, 'analista-pressupostos', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (17, 'revisor-processo-recursal-inapto', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (18, 'analista-repercussao-geral', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (19, 'revisor-repercussao-geral', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (20, 'autuador-recursal', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (21, 'organizador-pecas', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (22, 'gestor-modelo', 2);
INSERT INTO UAA.PAPEL(SEQ_PAPEL, NOM_PAPEL, SEQ_GRUPO) VALUES (23, 'representante-tribunal', 2);

-- 39 +/- SELECT COUNT(*) FROM UAA.SEGMENTO;           
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (1, 'ELETRONICA', 1);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (2, 'FISICA', 1);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (3, 'ORGAO', 1);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (4, 'TAREFA', 3);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (5, 'PETICIONADO', 3);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (6, 'GESTAO', 3);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (7, 'PREAUTUACAO', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (8, 'AUTUACAO', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (9, 'DISTRIBUICAO', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (10, 'DEVOLUCAO', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (11, 'ASSINATURA', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (12, 'CIDADAO', 7);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (13, 'ADVOGADO', 7);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (14, 'INTERNO', 7);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (15, 'UI', 4);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (16, 'peticionamento', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (17, 'recebimento', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (18, 'gestao-recebimento', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (19, 'distribuicao', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (20, 'autuacao', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (21, 'gestao-autuacao', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (22, 'gestao-orgao', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (23, 'gestao-cadastro', 9);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (24, 'assumir', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (25, 'delegacao', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (26, 'PREAUTUACAO-RECURSAL', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (27, 'PREAUTUACAO-RECURSAL-CRIMINAL-ELEITORAL', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (28, 'ANALISE-PRESSUPOSTOS', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (29, 'REVISAO-PROCESSO-RECURSAL-INAPTO', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (30, 'ANALISE-REPERCUSSAO-GERAL', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (31, 'REVISAO-REPERCUSSAO-GERAL', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (32, 'AUTUACAO-PROCESSO-RECURSAL', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (33, 'ORGANIZAR-PECAS', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (34, 'INSERIR-PECAS', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (35, 'EXCLUIR-PECAS', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (36, 'DIVIDIR-PECA', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (37, 'UNIR-PECAS', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (38, 'EDITAR-PECA', 5);
INSERT INTO UAA.SEGMENTO(SEQ_SEGMENTO, NOM_SEGMENTO, SEQ_TIPO_INFORMACAO) VALUES (39, 'JUNTAR-PECA', 5);

-- 50 +/- SELECT COUNT(*) FROM UAA.RECURSO;            
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (1, 'peticionar', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (2, 'registrar-remessa', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (3, 'peticionar-orgao', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (4, 'preautuar-originario', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (5, 'autuar-originario', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (6, 'distribuir-processo', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (7, 'preparar-oficio-devolucao', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (8, 'minhas-tarefas', 'DASHLET');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (9, 'minhas-peticoes', 'DASHLET');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (10, 'grafico-gestao', 'DASHLET');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (11, 'notificar-ui', 'NOTIFICACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (12, 'do-nothing-long', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (13, 'do-nothing', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (14, 'dummy-action', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (15, 'devolver-remessa', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (16, 'registrar-associado', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (17, 'configurar-permissao', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (19, '1', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (20, '2', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (21, '3', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (22, '4', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (23, '5', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (24, '6', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (25, '7', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (26, '8', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (27, 'assumir-tarefa', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (28, 'delegar-tarefa', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (29, 'preautuar-recursal', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (30, 'autuar-recursal-criminal-eleitoral', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (31, 'analisar-pressupostos-formais', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (32, 'revisar-pressupostos-formais', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (33, 'analisar-repercussao-geral', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (34, 'revisar-repercussao-geral', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (35, 'autuar-recursal', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (36, 'organizar-pecas', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (37, 'inserir-pecas', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (38, 'excluir-pecas', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (39, 'dividir-peca', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (40, 'unir-pecas', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (41, 'juntar-peca', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (42, 'editar-peca', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (43, 'salvar-pesquisa-avancada', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (44, '9', 'DASHBOARD');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (45, 'criar-modelo', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (46, 'gerar-texto', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (47, 'finalizar-texto-devolucao', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (48, 'consultar-processo-envio', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (49, 'enviar-processo', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (50, 'excluir-pesquisa-avancada', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (51, 'gerar-acronimos', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (52, 'upload-documento-assinado', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (53, 'salvar-documentos', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (54, 'cadastrar-pessoas', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (55, 'pesquisar-processos', 'PESQUISA');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (56, 'sugerir-processos', 'PESQUISA');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (57, 'concluir-texto', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (58, 'assinar-oficio-devolucao', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (59, 'assinar-texto', 'ACAO');
INSERT INTO UAA.RECURSO(SEQ_RECURSO, NOM_RECURSO, TIP_RECURSO) VALUES (60, 'upload-documento', 'ACAO');

-- 69 +/- SELECT COUNT(*) FROM UAA.INFORMACAO;         
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (1, 7, 12, 'HC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (2, 7, 13, 'AC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (3, 7, 13, 'ACO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (4, 7, 13, 'ADC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (5, 7, 13, 'ADI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (6, 7, 13, 'ADO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (7, 7, 13, 'ADPF');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (8, 7, 13, 'AImp');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (9, 7, 13, 'AO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (10, 7, 13, 'AOE');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (11, 7, 13, 'AR');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (12, 7, 13, 'AS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (13, 7, 13, 'CC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (14, 7, 13, 'EI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (15, 7, 13, 'EL');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (16, 7, 13, 'HC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (17, 7, 13, 'HD');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (18, 7, 13, 'IF');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (19, 7, 13, 'MI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (20, 7, 13, 'MS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (21, 7, 13, 'PSV');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (22, 7, 13, 'Pet');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (23, 7, 13, 'Rcl');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (24, 7, 13, 'RvC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (25, 7, 13, 'SL');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (26, 7, 13, 'SS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (27, 7, 13, 'STA');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (28, 7, 14, 'AC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (29, 7, 14, 'ACO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (30, 7, 14, 'ADC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (31, 7, 14, 'ADI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (32, 7, 14, 'ADO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (33, 7, 14, 'ADPF');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (34, 7, 14, 'AI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (35, 7, 14, 'AImp');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (36, 7, 14, 'AO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (37, 7, 14, 'AOE');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (38, 7, 14, 'AP');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (39, 7, 14, 'AR');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (40, 7, 14, 'ARE');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (41, 7, 14, 'AS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (42, 7, 14, 'CC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (43, 7, 14, 'Cm');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (44, 7, 14, 'EI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (45, 7, 14, 'EL');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (46, 7, 14, 'EP');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (47, 7, 14, 'Ext');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (48, 7, 14, 'HC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (49, 7, 14, 'HD');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (50, 7, 14, 'IF');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (51, 7, 14, 'Inq');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (52, 7, 14, 'MI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (53, 7, 14, 'MS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (54, 7, 14, 'OACO');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (55, 7, 14, 'PPE');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (56, 7, 14, 'PSV');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (57, 7, 14, 'Pet');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (58, 7, 14, 'RC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (59, 7, 14, 'RE');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (60, 7, 14, 'RHC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (61, 7, 14, 'RHD');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (62, 7, 14, 'RMI');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (63, 7, 14, 'RMS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (64, 7, 14, 'Rcl');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (65, 7, 14, 'RvC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (66, 7, 14, 'SEC');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (67, 7, 14, 'SL');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (68, 7, 14, 'SS');
INSERT INTO UAA.INFORMACAO(SEQ_INFORMACAO, SEQ_TIPO_INFORMACAO, SEQ_SEGMENTO, COD_IDENTIDADE) VALUES (69, 7, 14, 'STA'); 

-- 3 +/- SELECT COUNT(*) FROM UAA.SETOR
INSERT INTO UAA.SETOR(COD_SETOR, NOM_SETOR, SIG_SETOR) VALUES ('600000627', 'SECRETARIA JUDICIÁRIA', 'SEJ');
INSERT INTO UAA.SETOR(COD_SETOR, NOM_SETOR, SIG_SETOR) VALUES ('600000629', 'COORDENADORIA DE PROCESSAMENTO INICIAL', 'CPIN');
INSERT INTO UAA.SETOR(COD_SETOR, NOM_SETOR, SIG_SETOR) VALUES ('600000680', 'SEÇÃO DE RECEBIMENTO E DISTRIBUIÇÃO DE RECURSOS', 'SRDR');

-- 26 +/- SELECT COUNT(*) FROM UAA.USUARIO;            
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (1, 'peticionador', 16, 'Peticionador', NULL);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (3, 'representante', 18, 'Representante', NULL);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (11, 'gestor-recebimento', 26, 'Gestor Recebimento', NULL);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (13, 'gestor-cadastro', 28, 'Gestor Cadastro', NULL);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (14, 'cidadao', 29, 'Cidadao', NULL);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (5, 'autuador', 20, 'Autuador', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (6, 'distribuidor', 21, 'Distribuidor', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (7, 'cartoraria', 22, 'Cartoraria', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (8, 'gestor-autuacao', 23, 'Gestor Autuacao', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (9, 'usuario-teste', 24, 'Usuario Teste', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (10, 'TOMAS.GODOI', 25, 'TOMAS GODOI', 600000629);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (12, 'gestor-orgao', 27, 'Gestor Orgao', 600000629);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (2, 'recebedor', 17, 'Recebedor', 600000680);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (15, 'preautuador-recursal', 30, 'Preautuador Recursal', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (4, 'preautuador-originario', 19, 'Preautuador Originario', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (16, 'autuador-recursal-ce', 31, 'Autuador Recursal Ce', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (17, 'analista-pressupostos', 32, 'Analista Pressupostos', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (18, 'revisor-processo-ri', 33, 'Revisor Processo Ri', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (19, 'analista-repercussao-g', 34, 'Analista Repercussao G', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (20, 'revisor-repercussao-g', 35, 'Revisor Repercussao G', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (21, 'autuador-recursal', 36, 'Autuador Recursal', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (22, 'organizador-pecas', 37, 'Organizador Pecas', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (23, 'gestor-modelo', 38, 'Gestor Modelo', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (24, 'gestor-modelo-01', 39, 'Gestor Modelo 01', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (25, 'gestor-modelo-02', 40, 'Gestor Modelo 02', 600000627);
INSERT INTO UAA.USUARIO(SEQ_USUARIO, SIG_USUARIO, SEQ_PESSOA, NOM_PESSOA, COD_SETOR_LOTACAO) VALUES (26, 'representante-tribunal', 41, 'Representante Tribunal', 600000627);             

-- 28 +/- SELECT COUNT(*) FROM UAA.PAPEL_USUARIO;      
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (1, 1);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (2, 2);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (3, 3);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (4, 4);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (5, 5);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (6, 6);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (7, 7);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (8, 8);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (5, 8);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (5, 9);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (11, 11);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (9, 12);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (10, 13);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (12, 14);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (13, 1);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (13, 3);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (14, 15);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (15, 16);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (16, 17);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (17, 18);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (18, 19);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (19, 20);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (20, 21);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (21, 22);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (22, 23);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (22, 24);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (22, 25);
INSERT INTO UAA.PAPEL_USUARIO(SEQ_PAPEL, SEQ_USUARIO) VALUES (23, 26);              

-- 28 +/- SELECT COUNT(*) FROM UAA.GRUPO_USUARIO;      
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 1);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 2);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 3);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 4);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 5);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 6);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 7);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 8);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 9);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (2, 4);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (2, 5);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (2, 6);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (2, 7);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (2, 8);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 11);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 12);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 13);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 14);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 15);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 16);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 17);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 18);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 19);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 20);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 21);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 22);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 23);
INSERT INTO UAA.GRUPO_USUARIO(SEQ_GRUPO, SEQ_USUARIO) VALUES (1, 26); 

-- 3 +/- SELECT COUNT(*) FROM UAA.GRUPO_RECURSO;       
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES (1, 11);
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES (1, 43);
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES (1, 50);
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES (1, 55);
INSERT INTO UAA.GRUPO_RECURSO(SEQ_GRUPO, SEQ_RECURSO) VALUES (1, 56);             

-- 91 +/- SELECT COUNT(*) FROM UAA.PAPEL_RECURSO;      
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 1);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 52);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 53);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 54);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (12, 1);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (2, 2);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 3);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 52);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 53);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 54);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 3);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (4, 4);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (5, 5);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (6, 6);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 7);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (4, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (5, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (6, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (14, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (15, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (16, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (17, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (18, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (19, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (20, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 8);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (2, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (12, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (13, 9);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 10);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 15);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 16);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (10, 16);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 17);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (1, 19);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (3, 19);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 19);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (2, 20);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 21);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (6, 22);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (4, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (5, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (14, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (15, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (16, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (17, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (18, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (19, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (20, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 23);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 24);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 25);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (9, 26);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (10, 26);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (4, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (5, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (6, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (14, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (15, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (16, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (17, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (18, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (19, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (20, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 27);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (8, 28);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (14, 29);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (15, 30);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (16, 31);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (17, 32);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (18, 33);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (19, 34);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (20, 35);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 36);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 37);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 38);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 39);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 40);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 41);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (21, 42);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (22, 44);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (22, 45);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 46);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 47);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (23, 48);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (23, 49);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (23, 51);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (14, 15);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (4, 15);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (7, 57);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 58);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 59);
INSERT INTO UAA.PAPEL_RECURSO(SEQ_PAPEL, SEQ_RECURSO) VALUES (11, 60);

-- 1 +/- SELECT COUNT(*) FROM UAA.USUARIO_RECURSO;      
INSERT INTO UAA.USUARIO_RECURSO(SEQ_USUARIO, SEQ_RECURSO) VALUES (9, 51);