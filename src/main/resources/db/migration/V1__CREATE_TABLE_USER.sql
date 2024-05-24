CREATE TABLE User (
    id  FLOAT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(50),
    email(150),
    senha varchar(50),
    role ENUM('ADMIN', 'USER')
);