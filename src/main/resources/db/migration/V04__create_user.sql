CREATE TABLE IF NOT EXISTS user_type (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

INSERT INTO user_type(name) VALUES ('Tester'), ('Analista de sistema'),
('DBA'), ('Programador'), ('Engenheiro de software');

CREATE TABLE IF NOT EXISTS user_account(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  email TEXT NOT NULL,
  password TEXT NOT NULL,
  phone TEXT,
  sex TEXT,
  birthdate DATE,
  active BOOLEAN DEFAULT 'true',
  meta jsonb,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  user_type_id INTEGER NOT NULL,
  user_group_id INTEGER NOT NULL,
  company_id UUID NOT NULL,
  FOREIGN KEY (user_type_id) REFERENCES user_type(id),
  FOREIGN KEY (user_group_id) REFERENCES user_group(id),
  FOREIGN KEY (company_id) REFERENCES company(id)
);