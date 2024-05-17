ALTER TABLE bibiliteca_amigosdedonbosco.categorias
ADD COLUMN carrera_id VARCHAR(6) NOT NULL,
ADD CONSTRAINT FK_categorias_carreras
FOREIGN KEY (carrera_id) REFERENCES bibiliteca_amigosdedonbosco.carreras(carrera_id);