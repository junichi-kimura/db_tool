SELECT 
  TABLE_NAME AS table_name,
  REMARKS AS table_comment
FROM INFORMATION_SCHEMA.TABLES 
WHERE 
  TABLE_CATALOG = /*database_name*/''
  AND TABLE_SCHEMA = /*database_schema*/''
ORDER BY TABLE_NAME