CREATE TABLE IF NOT EXISTS user_type (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_app(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  email TEXT NOT NULL,
  password TEXT NOT NULL,
  phone TEXT NOT NULL,
  sex TEXT,
  birthdate DATE,
  active BOOLEAN DEFAULT 'true',
  meta JSONB,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  user_type_id INTEGER NOT NULL
  user_group_id INTEGER NOT NULL,
  FOREIGN KEY (user_type_id) REFERENCES user_type(id),
  FOREIGN KEY (user_group_id) REFERENCES user_group(id)
);