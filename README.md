# Professors discord mention
This project was an idea from a lazy task like mention all the professors of a certain day and hour manually on discord. As you can guess, as a programmer, I wanted to automate it. Here is the result.

# Features
- [x] Data persistance with professors, given classes and courses
- [x] Access to remote Google Sheets with all the information.
- [x] RestAPI to register and list all the professors, given classes and courses. 
- [ ] Authentication by user admins.
- [ ] Send discord message.
- [ ] Frontend app with React.

# Requisites
- **Java 23**
- **Maven** for dependencies.
- **Docker** (optional).
- **MySQL** (if you don't use Docker).

# Instalation
1. Clone repository and open folder:
```bash
git clone https://github.com/Kirtasth/springboot-hexagonal-mentions.git
cd springboot-hexagonal-mentions
```
2. Configure variables from `application.properties` or `application.yml` to connect to MySQL
```properties
spring.datasource.url = jdbc:mysql://localhost:3306/db_menciones
spring.datasource.username=db_user_1
spring.datasource.password=db_pass_1
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.sql.init.mode=always

spring.jpa.open-in-view=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false
```
3. Compile project with Maven:
```bash
./mvnw clean install
```
4. Execute the project:
```bash
./mvnw spring-boot:run
```

# Usage
## Endpoints
- **Professors**:
  - Register: `POST /api/professors/register`
  - List all: `GET /api/professors/all`
  - List by role: `GET /api/professors/{id}`
    
- **Courses**:
  - Register: `POST /api/courses/register` 
  - List all: `GET /api/courses/all` 
  - List by day of week: `GET /api/courses/day/{dayOfWeek}` 
  - List by name: `GET /api/courses/name/{name}`
 
- **Given class**:
  - Register: `POST /api/given_class/register`
  - List all: `GET /api/given_class/all`
  - List by day of week: `GET /api/given_class/{dayOfWeek}`
  - List by day of week and hour: `GET /api/given_class/{dayOfWeek}/{localTime}`

- **Data from Google Sheets**:
  - Get data: `GET /api/data/list`

# Test
TODO
