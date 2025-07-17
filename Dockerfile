# Use a lightweight Java 21 runtime image
FROM openjdk:21-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/donorcrm-0.0.1-SNAPSHOT.jar app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
