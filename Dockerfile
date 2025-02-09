FROM openjdk:17-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

COPY target/backend.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]