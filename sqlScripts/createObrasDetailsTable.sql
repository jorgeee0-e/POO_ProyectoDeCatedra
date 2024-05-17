CREATE TABLE obras (
    id_obra VARCHAR(6) PRIMARY KEY,
    nombre_obra VARCHAR(255) NOT NULL,
    id_material VARCHAR(6),
    curso_id VARCHAR(255),
    id_autor VARCHAR(255),
    fecha_publicacion DATE,
    ISBN VARCHAR(255),
    stock INT,
    FOREIGN KEY (id_material) REFERENCES `type`(material_id),
    FOREIGN KEY (curso_id) REFERENCES categorias(curso_id),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);
