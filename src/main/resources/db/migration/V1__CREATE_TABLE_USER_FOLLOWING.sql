CREATE TABLE Following (
    user_id INT,
    blog_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (blog_id) REFERENCES Blogs(id)
);