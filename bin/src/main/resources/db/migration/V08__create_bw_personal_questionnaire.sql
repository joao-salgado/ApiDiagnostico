CREATE TABLE IF NOT EXISTS bw_personal_questionnaire(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  total_result INTEGER NOT NULL,
  created TIMESTAMP DEFAULT current_timestamp,
  modified TIMESTAMP,
  meta jsonb,
  bw_questionnaire_id UUID NOT NULL,
  user_account_id UUID NOT NULL,
  FOREIGN KEY (user_account_id) REFERENCES user_account(id),
  FOREIGN KEY (bw_questionnaire_id) REFERENCES bw_questionnaire(id)
);

CREATE TABLE IF NOT EXISTS bw_personal_section(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  section INTEGER NOT NULL,
  total_result INTEGER NOT NULL,
  meta jsonb,
  bw_personal_questionnaire_id UUID NOT NULL,
  FOREIGN KEY (bw_personal_questionnaire_id) REFERENCES bw_personal_questionnaire(id)
);