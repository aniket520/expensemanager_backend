# Use Eclipse Temurin Java 21 JDK
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build JAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port (Render will set PORT environment variable)
ENV PORT 8080
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/expensemanager1-0.0.1-SNAPSHOT.jar"]