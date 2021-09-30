SELECT
  column_name
FROM information_schema.STATISTICS 
WHERE
  table_schema = /*table_schema*/''
  AND table_name = /*table_name*/''
  AND index_name = /*index_name*/''
  AND index_name != 'PRIMARY'