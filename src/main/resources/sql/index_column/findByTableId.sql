SELECT
  indexes.id AS index_id,
  columns.physical_column_name AS column_name,
FROM indexes
INNER JOIN index_columns ON indexes.id = index_columns.index_id
INNER JOIN columns ON index_columns.column_id = columns.id
WHERE
  indexes.table_id = /*table_id*/0
GROUP BY indexes.id, columns.physical_column_name
ORDER BY index_columns.id