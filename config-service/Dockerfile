FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/config
EXPOSE 8888
COPY build/libs/config-service-0.0.1.jar ConfigService.jar
ENTRYPOINT ["java", "-jar", "ConfigService.jar"]