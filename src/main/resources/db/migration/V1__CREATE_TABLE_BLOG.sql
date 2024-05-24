CREATE TABLE Blog (
    id  FLOAT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(50),
    description varchar(255),
    created_At DATE,
    updated_At DATE,
    user_id FLOAT
    FOREIGN KEY (user_id) REFERENCES User(id)
)