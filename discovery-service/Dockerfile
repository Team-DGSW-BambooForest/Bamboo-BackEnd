FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/discovery
EXPOSE 8761
COPY build/libs/discovery-service-0.0.1.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]