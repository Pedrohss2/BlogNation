#BlogNatio
An API that allows a user to log into the system, post a blog, follow and comment on the blog

## Functionalities
- User authentication
- User follows and unfollows a blog
- User comments on a blog
- CRUD User
- CRUD blog

## Usage/Examples
End-point to register a user 

Method: POST
```
localhost:8080/auth/register
```
Body (raw)
```
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
```
{
    "email": "pedro@gmail.com",
    "password": "peddro@hh"
}
```
The returned value will be a token: 
Body (pretty)
```
{
    "token": "akshrediow54545exxxxwedexemplo"
}
```

Authentication bearer

An authentication token is required to return the user

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `barear` | `string` | {{token}} |

End point to see logged in user

Method: POST
```
localhost:8080/auth/users/me
```
Body(raw)
```
{
    "id": 1,
    "name": "Pedro henrique",
    "email": "pedro@gmail.com",
    "password": "$2a$10$P3ezQf4YM0VmomEkSEsFvO6olLsX7egQmugrkEdBMQmx8BiwNwskW"
}
```





