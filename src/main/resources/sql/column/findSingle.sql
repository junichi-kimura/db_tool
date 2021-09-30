SELECT
  *
FROM columns
INNER JOIN tables ON columns.table_id = tables.id AND tables.project_id = /*project_id*/1
WHERE
  tables.physical_table_name = /*table_name*/''
  AND columns.physical_column_name = /*column_name*/''