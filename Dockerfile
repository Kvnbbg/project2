# Base image
FROM openjdk:11-jre-slim

# Set working directory
WORKDIR /app

# Copy the project jar (assumes it's already built)
COPY target/step-tracker-0.0.1-SNAPSHOT.jar /app/step-tracker.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "step-tracker.jar"]
