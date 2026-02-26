# Use Eclipse Temurin Java 21
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Download dependencies (this layer will be cached)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src src

# Build the JAR inside the Docker image
RUN ./mvnw clean package -DskipTests

# The JAR is in target/
CMD ["java", "-jar", "target/expensemanager1-0.0.1-SNAPSHOT.jar"]