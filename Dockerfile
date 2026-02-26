# Use official OpenJDK 21 slim image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy your built JAR
COPY target/expensemanager1-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render will provide PORT)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]