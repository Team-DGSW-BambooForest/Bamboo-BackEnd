FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/user
EXPOSE 7000
COPY build/libs/user-service-0.0.1.jar UserService.jar
ENTRYPOINT ["java", "-jar", "UserService.jar"]
