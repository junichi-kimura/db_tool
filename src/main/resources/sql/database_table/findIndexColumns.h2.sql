SELECT
  column_name
FROM information_schema.INDEXES 
WHERE
  table_catalog = /*table_schema*/''
  AND table_name = /*table_name*/''
  AND index_name = /*index_name*/''
ORDER BY ORDINAL_POSITION