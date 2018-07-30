CREATE TABLE IF NOT EXISTS company_process (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS company(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  meta JSONB,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  company_process_id INTEGER NOT NULL
  FOREIGN KEY (company_process_id) REFERENCES company_process(id)
);