CREATE TABLE IF NOT EXISTS company_process (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

INSERT INTO company_process(name) VALUES ('AD HOC'),
											('Desenvolvimento ágil de software'),
											('Desenvolvimento iterativo e incremental'),
											('Desenvolvimento rápido de aplicação (RAD)'),
											('Modelo cascata'),
											('Modelo baseado em componentes'),
											('Modelo em espiral'),
											('Prototipação'),
											('Outro');

CREATE TABLE IF NOT EXISTS company(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  meta jsonb,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  company_process_id INTEGER NOT NULL,
  FOREIGN KEY (company_process_id) REFERENCES company_process(id)
);