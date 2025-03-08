FROM maven:3.9.8-eclipse-temurin-22 AS build

WORKDIR /home/maven/src

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre

EXPOSE 8081

COPY --from=build /home/maven/src/target/scheduler-service-0.0.1-SNAPSHOT.jar /app/scheduler-service.jar

CMD ["java", "-jar", "/app/scheduler-service.jar"]
