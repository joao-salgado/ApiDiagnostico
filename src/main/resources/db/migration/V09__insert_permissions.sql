INSERT INTO permission(role) VALUES ('ROLE_INVITE');

INSERT INTO permission(role) VALUES ('ROLE_USER');

INSERT INTO permission(role) VALUES ('ROLE_COMPANY');

INSERT INTO permission(role) VALUES ('ROLE_DIAGNOSIS');

INSERT INTO permission(role) VALUES ('ROLE_COMPANY_DASHBOARD');

INSERT INTO permission(role) VALUES ('ROLE_RESEARCH_DASHBOARD');

-- admin
INSERT INTO user_group_permission(user_group_id, permission_id) VALUES (1,1), (1,2), (1,3), (1,4), (1,5);

-- employee
INSERT INTO user_group_permission(user_group_id, permission_id) VALUES (2,1), (2,2), (2,5);

-- researcher
INSERT INTO user_group_permission(user_group_id, permission_id) VALUES (3,2), (3,6);