
# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Define the JAR file name (replace with your actual JAR file name)
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container as app.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080 for Heroku
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
