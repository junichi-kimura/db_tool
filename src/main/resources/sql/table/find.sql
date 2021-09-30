SELECT
  tables.id AS id,
  tables.project_id AS project_id,
  tables.physical_table_name AS physical_table_name,
  tables.logical_table_name AS logical_table_name,
  tables.table_comment AS table_comment,
  tables.note AS note
FROM tables
LEFT JOIN columns ON tables.id = columns.table_id
WHERE
  project_id = /*project_id*/0
  /*IF table_name != null*/
  AND (tables.physical_table_name LIKE /*table_name*/'' OR tables.logical_table_name LIKE /*table_name*/'')
  /*END*/
  /*IF column_name != null*/
  AND (columns.physical_column_name LIKE /*column_name*/'' OR columns.logical_column_name LIKE /*column_name*/'')
  /*END*/
GROUP BY id, project_id, physical_table_name, logical_table_name, table_comment, note
ORDER BY physical_table_name