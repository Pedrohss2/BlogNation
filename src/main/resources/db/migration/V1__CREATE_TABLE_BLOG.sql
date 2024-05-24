CREATE TABLE Blogs (
    id INT NOT NULL PRIMARY KEY,
    name varchar(50),
    description varchar(255),
    created_At DATE,
    updated_At DATE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id)
)