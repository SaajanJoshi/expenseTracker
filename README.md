# expenseTracker
For db creation:
psql -U postgres --file expensetracker_db.sql

Changes in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expensetrackerdb
spring.datasource.username=expensetracker
spring.datasource.password=password

Payload 

POST: Url: http://localhost:8080/api/users/login
payload for login
{
    "email":"",
    "password":""
}

POST: Url: http://localhost:8080/api/users/register
payload for register
{
    "firstName":"",
    "lastName":"",
    "email":"",
    "password":""
} 

POST: Url: http://localhost:8080/api/categories
