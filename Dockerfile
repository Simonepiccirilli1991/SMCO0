FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/SMOC0-0.0.1-SNAPSHOT.jar /app/IWDB0.jar

# Expose the port
EXPOSE 8089

LABEL name="smoc-img"

# Run the application
CMD ["java", "-jar", "IWDB0.jar"]
