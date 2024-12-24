
# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/lms-0.0.1-SNAPSHOT.jar lms-0.0.1-SNAPSHOT.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/lms-0.0.1-SNAPSHOT.jar"]

