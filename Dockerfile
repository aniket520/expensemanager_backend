# Use Java 21 JDK
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching dependencies
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies offline
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the JAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port (Render sets PORT environment variable)
ENV PORT 8080
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/expensemanager1-0.0.1-SNAPSHOT.jar"]