CREATE TABLE indexes(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  table_id BIGINT NOT NULL,
  index_name VARCHAR(500) NOT NULL, -- ユニークキー名
  is_unique BOOLEAN NOT NULL, -- ユニークキーかどうか
  note TEXT, -- このインデックスについてのメモ
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  extra_column VARCHAR(1000) AS table_id || '_' || index_name,
  PRIMARY KEY (id),
  FOREIGN KEY (table_id) REFERENCES tables(id),
  CONSTRAINT indexes_uq1 UNIQUE(extra_column)
);