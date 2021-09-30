CREATE TABLE referentials(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  project_id BIGINT NOT NULL,
  referential_type INT NOT NULL, -- 外部キータイプ(物理 or 論理)
  constraint_name VARCHAR(500) NOT NULL, -- 外部キー名
  from_table_name VARCHAR(500) NOT NULL, -- 外部キーを貼ったテーブル名
  from_table_id BIGINT NOT NULL, -- 外部キーを貼ったテーブルID
  from_cardinari INT, -- 外部キーを貼ったテーブルのER図のカーディナリ
  to_table_name VARCHAR(500) NOT NULL, -- 外部キーを貼られたテーブル名
  to_table_id BIGINT NOT NULL, -- 外部キーを貼られたテーブルID
  to_cardinari INT, -- 外部キーを貼られたテーブルのER図のカーディナリ
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (from_table_id) REFERENCES tables(id),
  FOREIGN KEY (to_table_id) REFERENCES tables(id)
);