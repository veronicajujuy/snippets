-- Asegura que el campo name sea único
ALTER TABLE languages
    ADD UNIQUE KEY IF NOT EXISTS uq_languages_name (name);

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
