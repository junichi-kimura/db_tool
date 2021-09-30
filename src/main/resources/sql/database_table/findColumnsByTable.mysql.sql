SELECT
  table_schema
  , table_name
  , column_name
  , column_comment
  , ordinal_position
  , UPPER(data_type) AS column_type
  , character_maximum_length
  , CASE
    WHEN is_nullable = 'YES' THEN TRUE
    WHEN is_nullable = 'NO' THEN FALSE
    END AS is_nullable
  , column_default
  , column_key
  , extra
FROM information_schema.columns
WHERE
  table_schema = /*table_schema*/'' 
  AND table_name = /*table_name*/''
ORDER BY ordinal_position