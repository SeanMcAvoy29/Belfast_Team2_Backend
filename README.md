# DropwizardApplication

How to start the DropwizardApplication application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/Team2_Belfast_API-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

# Team2_Belfast_Backend

Config
---
1. The following environment variables need to be set to enable database connection:
```
user
password
host
name
```
- user = is your username
- password = is your account password
- host = is the DB Host IP/String
- name = is the name of the database

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger#`

Tests
---

1. Run `mvn clean test` to run unit tests
2. Run `mvn clean integration-test` to run integration tests (this may require VPN for database access)

