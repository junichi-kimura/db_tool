CREATE TABLE referential_columns(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  referential_id BIGINT NOT NULL,
  from_column_id BIGINT NOT NULL,
  from_column_name VARCHAR(500) NOT NULL,
  to_column_id BIGINT NOT NULL,
  to_column_name VARCHAR(500) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (referential_id) REFERENCES referentials(id),
  FOREIGN KEY (from_column_id) REFERENCES columns(id),
  FOREIGN KEY (to_column_id) REFERENCES columns(id)
);