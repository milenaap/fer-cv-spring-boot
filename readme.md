# CV Project Spring Boot

- Java 21
- Spring Boot version 3.5.10, generate by (https://start.spring.io/)
- Maven
- Jar
-

# Run Server

```sh
./mvnw spring-boot:run                                  // para ejecutar servidor de spring

```

# Docker

```sh
cd docker
docker compose up -d

```

# Comando para eliminar el proceso

```sh
lsof -i :8080
kill -9 33036 --> reemplazar el ultimo numero por el PID
```
