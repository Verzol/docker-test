# Stage 1: build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Stage 2: runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
ENV TZ=UTC
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UseG1GC","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]
