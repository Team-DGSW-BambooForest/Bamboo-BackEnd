FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/admin
EXPOSE 7003
COPY build/libs/admin-service-0.0.1.jar AdminService.jar
ENTRYPOINT ["java", "-jar", "AdminService.jar"]