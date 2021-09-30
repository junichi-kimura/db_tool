SELECT
  table_name AS from_table_name
  , column_name AS from_column_name
  , referenced_table_name AS to_table_name
  , referenced_column_name AS to_column_name
FROM information_schema.KEY_COLUMN_USAGE 
WHERE 
  position_in_unique_constraint IS NOT NULL
  AND constraint_schema = /*table_schema*/''
  AND constraint_name = /*constraint_name*/''
  AND table_name = /*from_table_name*/''
ORDER BY ordinal_position