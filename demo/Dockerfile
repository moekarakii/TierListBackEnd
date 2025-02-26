
# Use OpenJDK base image
FROM openjdk:17-jdk-slim
# Define the argument for the JAR file
ARG JAR_FILE=build/libs/*.jar

# Copy the JAR file into the container as app.jar
COPY ${JAR_FILE} app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]