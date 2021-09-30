CREATE TABLE columns(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  table_id BIGINT NOT NULL,
  physical_column_name VARCHAR(500) NOT NULL, --物理カラム名
  logical_column_name VARCHAR(500) NOT NULL, --論理カラム名
  column_comment TEXT,
  ordinal INT, -- カラムの順番
  column_type TEXT NOT NULL, -- カラムの型
  character_maximum_length INT, -- 文字列の最大長
  is_nullable BOOLEAN NOT NULL, -- NULL許容かどうか
  column_default TEXT, -- デフォルト値
  column_key TEXT, -- プライマリキーならPRI
  extra TEXT NOT NULL, -- AUTO_INCREMENTやON UPDATE等が入る
  note TEXT, -- このカラムについてのメモ
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  extra_column VARCHAR(1000) AS table_id || '_' || physical_column_name,
  PRIMARY KEY (id),
  FOREIGN KEY (table_id) REFERENCES tables(id),
  CONSTRAINT columns_uq1 UNIQUE(extra_column)
);
