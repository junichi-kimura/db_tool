SELECT
  index_name AS index_name,
  CASE 
    WHEN non_unique = 0 THEN TRUE
    ELSE FALSE END 
  AS is_unique
FROM information_schema.STATISTICS 
WHERE
  table_schema = /*table_schema*/''
  AND table_name = /*table_name*/''
  AND index_name != 'PRIMARY'
GROUP BY index_name