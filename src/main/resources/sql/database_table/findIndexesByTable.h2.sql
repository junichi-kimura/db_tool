SELECT
  index_name AS index_name,
  CASE 
    WHEN non_unique = FALSE THEN TRUE
    ELSE FALSE END 
  AS is_unique
FROM information_schema.INDEXES 
WHERE
  table_catalog = /*table_schema*/''
  AND table_name = /*table_name*/''
  AND PRIMARY_KEY = FALSE
  AND INDEX_NAME NOT LIKE 'CONSTRAINT%'
GROUP BY index_name