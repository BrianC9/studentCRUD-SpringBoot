## Functionality

### Get all the students

Endpoint **[GET]**: localhost:PORT/api/v1/student
Retrieve all the Students in the DB, with the age calculated on the server

### Get a single Student by the id

Endpoint **[GET]**: localhost:PORT/api/v1/student/{idStudent}
Retrieve a single Student searching by the id, specified on the URI

### Create a Student

Endpoint **[POST]**: localhost:PORT/api/v1/student
We want to create a new Student in the DB.

1. We must check if the email does not exists already in the DB.
2. If the email does exists, we should let the user know.

### Update the name and email

Endpoint **[PUT]**: localhost:8080/api/v1/student/{idStudent}?name={value}&email={value}
We want the user to be capable of changing the name and email from a Student, searching him by the id specified on the
URI.
We must validate:

1. The Student exists on the DB
2. The name and email passed are not null nor empty
3. The email does not exist already in the DB

### Delete a Student from the DB by the id

Endpoint **[DELETE]: localhost:PORT/api/v1/student/{idStudent}
Delete a Student from the DB. We should let know the user if the Student searced is not in the DB.
