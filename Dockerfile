# Use official OpenJDK 17 image
FROM openjdk:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into the container
COPY target/expensemanager1-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (backend port)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]

