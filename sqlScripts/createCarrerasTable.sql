CREATE TABLE bibiliteca_amigosdedonbosco.carreras (
    carrera_id VARCHAR(6) NOT NULL,
    carrera_name VARCHAR(255) NOT NULL,
    facultad_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (carrera_id),
    FOREIGN KEY (facultad_id) REFERENCES bibiliteca_amigosdedonbosco.facultades(facultad_id)
);