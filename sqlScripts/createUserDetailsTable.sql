SELECT * FROM bibiliteca_amigosdedonbosco.users;CREATE TABLE bibiliteca_amigosdedonbosco.user_details (
    user_id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    last_name VARCHAR(255),
    carrera VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    email VARCHAR(255),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES bibiliteca_amigosdedonbosco.users(user_id)
);
