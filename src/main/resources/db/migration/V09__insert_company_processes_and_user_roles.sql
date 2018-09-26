DELETE FROM company_process;

DELETE FROM user_type;

INSERT INTO user_type(name) VALUES ('Admin'),
									('Administrador(a) de banco de dados'),
									('Analista de segurança'),
									('Analista de sistemas'),
									('Arquiteto de software'),
									('Cientista de dados'),
									('Designer'),
									('Engenheiro(a) de software'),
									('Gerente de projetos'),
									('Programador(a)'),
									('Suporte'),
									('Tester'),
									('Outro');

INSERT INTO company_process(name) VALUES ('AD HOC'),
											('Desenvolvimento ágil de software'),
											('Desenvolvimento iterativo e incremental'),
											('Desenvolvimento rápido de aplicação (RAD)'),
											('Modelo cascata'),
											('Modelo baseado em componentes'),
											('Modelo em espiral'),
											('Prototipação'),
											('Outro');