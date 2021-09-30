SELECT
  referentials.from_table_id AS from_table_id
  , referentials.from_table_name AS from_table_name
  , referentials.to_table_id AS to_table_id
  , referentials.to_table_name AS to_table_name
  , referentials.referential_type AS referential_type
  , referential_columns.id AS referential_column_id
  , referential_columns.from_column_id AS from_column_id
  , referential_columns.from_column_name AS from_column_name
  , referential_columns.to_column_id AS to_column_id
  , referential_columns.to_column_name AS to_column_name
FROM referentials
INNER JOIN referential_columns ON referentials.id = referential_columns.referential_id
WHERE
  referentials.project_id = /*project_id*/1
  /*IF from_table_name != null*/
  AND referentials.from_table_name = /*from_table_name*/''
  /*END*/
  /*IF to_table_name != null*/
  AND referentials.to_table_name = /*to_table_name*/''
  /*END*/
ORDER BY referentials.id, referential_columns.id