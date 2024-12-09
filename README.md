## BlogNation
An API that allows a user to log into the system, post a blog, follow and comment on the blog

## Functionalities
- User authentication
- User follows and unfollows a blog
- User comments on a blog
- CRUD User
- CRUD blog

## Obs: ALL END-POINS MUST BE LOGGED IN

## Usage/Examples
End-point to register a user 

Method: POST
```
localhost:8080/auth/register
```
Body (raw)
```json
{
    "name": "Pedro henrique",
    "email": "pedro@gmail.com",
    "password": "peddro@hh",
    "role": "ADMIN"
}
```
End-point for logging in a user

Method: POST
```
localhost:8080/auth/login
```
Body (raw)
```json
{
    "email": "pedro@gmail.com",
    "password": "peddro@hh"
}
```
The returned value will be a token: 
Body (pretty)
```json
{
    "token": "akshrediow54545exxxxwedexemplo"
}
```

Authentication bearer

An authentication token is required to return the user

| Parameter   | Type       | Description  |
| :---------- | :--------- |:-------------|
| `{{token}}` | `string` | Barear token |

End point to see logged in user

Method: GET
```
localhost:8080/auth/users/me
```
Body(raw)
```json
{
    "id": 1,
    "name": "Pedro henrique",
    "email": "pedro@gmail.com",
    "password": "$2a$10$P3ezQf4YM0VmomEkSEsFvO6olLsX7egQmugrkEdBMQmx8BiwNwskW"
}
```

End-point for posting a blog

Method: POST
```
localhost:8080/blogs
```
Body(raw)
```json
{
    "title": "My weekend",
    "description": "Blog about my weekend my friend",
    "author_id": {
        "id": 1 
    },
    "created_At": "2024-01-06"
}
```
End-point to follow a blog

Method: POST
```
localhost:8080/user/{user_id}/follow/{blog_id}
```

End-point to unfollow a blog

Method: DELETE
```
localhost:8080/user/{user_id}/unfollow/{blog_id}
```
End-point to comment a blog

Method: POST
```
localhost:8080/comment
```

Body(raw)
```json
{
    "content": "Gostei muito do blog",
    "author_id": {
        "id": 1
    },
    "blog_id": {
        "id": 1
    }
}
```

## Author
- [@pedroH](https://github.com/Pedrohss2)








