CREATE TABLE tesis (
    id_tesis VARCHAR(8) PRIMARY KEY,
    id_material VARCHAR(6),
    curso_id VARCHAR(255),
    id_autor VARCHAR(255),
    paginas int,
    units INT,
    FOREIGN KEY (id_material) REFERENCES `type`(material_id),
    FOREIGN KEY (curso_id) REFERENCES categorias(curso_id),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);