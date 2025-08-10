-- Asegura que el campo name sea único
SET @exist := (SELECT COUNT(*) FROM information_schema.statistics WHERE table_schema = DATABASE() AND table_name = 'languages' AND index_name = 'uq_languages_name');
SET @sqlstmt := IF(@exist > 0, 'SELECT ''Index already exists.''', 'ALTER TABLE languages ADD UNIQUE KEY uq_languages_name (name)');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Inserta los lenguajes con sus íconos solo si no existen
INSERT IGNORE INTO languages (name, icon) VALUES
('Bash',        '/icons/uploaded/bash.png'),
('CSS',         '/icons/uploaded/css.png'),
('Docker',      '/icons/uploaded/docker.png'),
('HTML',        '/icons/uploaded/html.png'),
('Java',        '/icons/uploaded/java.png'),
('JavaScript',  '/icons/uploaded/js.png'),
('MySQL',       '/icons/uploaded/mysql.png'),
('React',       '/icons/uploaded/react.png'),
('SQL',         '/icons/uploaded/sql.png'),
('YAML',        '/icons/uploaded/yaml.png');
