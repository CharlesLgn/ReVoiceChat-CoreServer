# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build
COPY . .
RUN ./mvnw clean install -DskipTests -T4C -Dquarkus.config.locations=server.properties

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /build/app/target/quarkus-app/ /app/
COPY --from=builder /build/server.properties /app/server.properties
COPY --from=builder /build/jwt /app/jwt

EXPOSE 8080

CMD ["java", "-Dquarkus.config.locations=server.properties", "-jar", "quarkus-run.jar"]