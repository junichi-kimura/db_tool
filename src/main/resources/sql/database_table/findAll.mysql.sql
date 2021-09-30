SELECT 
  table_name,
  table_comment
FROM information_schema.tables 
WHERE table_schema = /*database_name*/''
ORDER BY table_name