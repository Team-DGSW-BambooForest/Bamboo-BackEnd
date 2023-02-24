FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/comment
EXPOSE 7004
COPY build/libs/comments-service-0.0.1.jar CommentService.jar
ENTRYPOINT ["java", "-jar", "CommentService.jar"]