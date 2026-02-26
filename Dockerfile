# Use Eclipse Temurin Java 21 JDK
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for dependency caching)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build Spring Boot JAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose dynamic port (Render sets PORT env)
ENV PORT 8080
EXPOSE 8080

# Run the built JAR
CMD ["java", "-jar", "target/expensemanager1-0.0.1-SNAPSHOT.jar"]