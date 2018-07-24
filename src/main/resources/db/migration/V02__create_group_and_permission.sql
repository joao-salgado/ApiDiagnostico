CREATE TABLE IF NOT EXISTS user_group (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);
      
CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    role TEXT NOT NULL
);

CREATE TABLE user_group_permission (
    user_group_id INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    PRIMARY KEY (user_group_id, permission_id),
    FOREIGN KEY (user_group_id) REFERENCES user_group(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);