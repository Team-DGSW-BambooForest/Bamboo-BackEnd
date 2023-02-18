FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/post
EXPOSE 7001
COPY build/libs/post-service-0.0.1.jar PostService.jar
ENTRYPOINT ["java", "-jar", "PostService.jar"]