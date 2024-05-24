CREATE TABLE Comments (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(50),
    content varchar(155),
    blog_id INT,
    user_id INT,
    FOREIGN KEY (blog_id) REFERENCES Blogs(id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
)