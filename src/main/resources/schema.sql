CREATE TABLE IF NOT EXISTS TB_broths (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_inactive VARCHAR(255),
    image_active VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    price INT
);

CREATE TABLE IF NOT EXISTS TB_proteins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_inactive VARCHAR(255),
    image_active VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    price INT
);