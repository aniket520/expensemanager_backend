# Use Eclipse Temurin Java 21
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy your Spring Boot JAR
COPY target/expensemanager1-0.0.1-SNAPSHOT.jar app.jar

# Use Render PORT dynamically
ENV PORT 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]