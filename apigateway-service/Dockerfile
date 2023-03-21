FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/gateway
EXPOSE 8081
COPY build/libs/apigateway-service-0.0.1.jar GatewayService.jar
ENTRYPOINT ["java", "-jar", "GatewayService.jar"]
