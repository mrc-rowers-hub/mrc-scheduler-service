# -------- Build stage --------
FROM maven:3.9.8-eclipse-temurin-22 AS build

WORKDIR /home/maven/src

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:22-jre

WORKDIR /app

# Expose service port
EXPOSE 8081

# Copy built jar from the build stage
COPY --from=build /home/maven/src/target/scheduler-service-0.0.1-SNAPSHOT.jar ./scheduler-service.jar

# Copy wait-for-it script
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

# Set entrypoint to wait for MySQL to be ready before launching the app
CMD ["./wait-for-it.sh", "mrc-mysql-3:3306", "--", "java", "-jar", "scheduler-service.jar"]
