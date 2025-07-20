# Stage 1: Build the application

FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


# Stage 2: Run the application
# Use a lightweight Java 21 runtime image
FROM openjdk:21-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY --from=build /app/target/donorcrm-0.0.1-SNAPSHOT.jar app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
