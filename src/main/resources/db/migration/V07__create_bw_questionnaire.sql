CREATE TABLE IF NOT EXISTS bw_questionnaire(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  status TEXT NOT NULL,
  total_result REAL NOT NULL,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  meta jsonb,
  company_id UUID NOT NULL,
  FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS bw_section(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  section INTEGER NOT NULL,
  total_result REAL NOT NULL,
  meta jsonb,
  bw_questionnaire_id UUID NOT NULL,
  FOREIGN KEY (bw_questionnaire_id) REFERENCES bw_questionnaire(id)
);