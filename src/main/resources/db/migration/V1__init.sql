CREATE TABLE projects(
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  project_name VARCHAR(500) NOT NULL,
  database_type INT NOT NULL,
  database_host_name TEXT NOT NULL,
  database_name VARCHAR(500) NOT NULL,
  database_schema VARCHAR(500) NOT NULL,
  database_user VARCHAR(500) NOT NULL,
  database_pass VARCHAR(500) NOT NULL,
  database_port VARCHAR(10) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id)
);
