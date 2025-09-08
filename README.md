## Journal App (Spring Boot + MongoDB)

A minimal journaling REST API built with Spring Boot 3, Spring Security, and MongoDB.

### Stack
- Java 17, Maven
- Spring Boot 3 (Web, Security, Validation)
- Spring Data MongoDB

### Prerequisites
- Java 17+
- Maven 3.9+
- MongoDB (Atlas or local). For transactions, Mongo must run as a replica set (Atlas is OK).

### Configuration
Use environment variables for secrets.

- MONGODB_URI — Mongo connection string

The app reads `spring.data.mongodb.uri` from `MONGODB_URI` in `src/main/resources/application.properties`.

Example (PowerShell):
```powershell
$env:MONGODB_URI = "mongodb+srv://<user>:<pass>@<cluster>/<db>?retryWrites=true&w=majority"
```

### Run
```powershell
mvn spring-boot:run
```
Server: `http://localhost:8080`.

### Authentication
- HTTP Basic Auth
- `/journal/**` and `/user/**` require authentication.
- `/public/**` is open.

### Endpoints
- Public
  - POST `/public/create-user`
    - Body:
      ```json
      { "userName": "alice", "password": "StrongPass123" }
      ```
    - Returns 201
  - GET `/public/health-check`

- User (auth required)
  - GET `/user`
  - PUT `/user` — update username/password
  - DELETE `/user`

- Journal (auth required)
  - GET `/journal` — list current user’s entries
  - POST `/journal` — create entry
    - Body example:
      ```json
      { "title": "9 sept", "content": "i am a software engineer" }
      ```
  - GET `/journal/id/{id}` — get entry by id
  - PUT `/journal/id/{userName}/{id}` — update entry (note: trusts path userName)
  - DELETE `/journal/id/{userName}/{id}` — delete entry (note: trusts path userName)



### Build
```powershell
mvn clean package
```
Jar: `target/journal-app-<version>.jar`

### License
See `LICENSE`.
