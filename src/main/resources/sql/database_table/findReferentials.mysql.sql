SELECT
  constraint_name AS constraint_name,
  table_name AS from_table_name,
  referenced_table_name AS to_table_name
FROM information_schema.referential_constraints
WHERE
  constraint_schema = /*table_schema*/''
ORDER BY referenced_table_name