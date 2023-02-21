FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/upload
EXPOSE 7005
COPY build/libs/upload-service-0.0.1.jar UploadService.jar
ENTRYPOINT ["java", "-jar", "UploadService.jar"]