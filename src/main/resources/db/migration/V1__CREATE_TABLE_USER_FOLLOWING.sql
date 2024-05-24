CREATE TABLE Following (
    user_id FLOAT,
    blog_id FLOAT,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (blog_id) REFERENCES Blog(id)
)