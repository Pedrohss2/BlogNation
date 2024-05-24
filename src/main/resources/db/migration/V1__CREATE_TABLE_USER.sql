CREATE TABLE Users (
    id INT NOT NULL PRIMARY KEY,
    name varchar(50),
    email varchar(150),
    senha varchar(50),
    role ENUM('ADMIN', 'USER')
);