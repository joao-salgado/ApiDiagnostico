CREATE TABLE IF NOT EXISTS send_invite (
  id SERIAL PRIMARY KEY,
  email TEXT NOT NULL,
  situation TEXT NOT NULL,
  code TEXT NOT NULL,
  company_id UUID NOT NULL,
  FOREIGN KEY (company_id) REFERENCES company(id)
);
