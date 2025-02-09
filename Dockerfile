FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/backend.jar backend.jar
EXPOSE 8081
CMD ["java", "-jar", "backend.jar"]