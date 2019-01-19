UPDATE company
SET company_process_id = 11
WHERE company_process_id = 2;

DELETE FROM company_process
WHERE id = 2;