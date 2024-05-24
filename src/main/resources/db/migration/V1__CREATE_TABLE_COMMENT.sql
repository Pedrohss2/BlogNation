CREATE TABLE Comment(
    id FLOAT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(50),
    content varchar(155),
    blog_id FLOAT,
    user_id FLOAT,
    FOREIGN KEY (blog_id) REFERENCES Blog(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
)