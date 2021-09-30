CREATE TABLE index_columns(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  index_id BIGINT NOT NULL,
  column_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  extra_column VARCHAR(1000) AS index_id || '_' || column_id,
  PRIMARY KEY (id),
  FOREIGN KEY (index_id) REFERENCES indexes(id),
  FOREIGN KEY (column_id) REFERENCES columns(id),
  CONSTRAINT index_columns_uq1 UNIQUE(extra_column)
);