CREATE TABLE tables(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  project_id BIGINT NOT NULL,
  physical_table_name VARCHAR(500) NOT NULL, --物理テーブル名
  logical_table_name VARCHAR(500) NOT NULL, --論理テーブル名
  table_comment TEXT, -- テーブルコメント
  note TEXT, -- このテーブルについてのメモ
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (project_id) REFERENCES projects(id),
  CONSTRAINT tables_uq1 UNIQUE(project_id, physical_table_name)
);