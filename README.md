# Bamboo-BackEnd

### 사용 언어, 프레임워크 및 라이브러리
- java
- spring boot
- jpa
- redis
- retrofit2
- spring security
- jwt
- mysql
- spring cloud


## application.yml
### config
``` yaml
server:
  port: ${PORT}

spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri:
        native:
          search-locations: 
```

### discovery
```yaml
server:
  port: ${PORT}

spring:
  application:
    name: discovery-service

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

### apigateway
``` yaml
server:
  port: ${PORT}

spring:
  application:
    name: apigateway-service
  cloud:
    gateway:
      routes:
        - id: ${SERVICE-NAME}
          uri: lb://${SERVICE-NAME}
          predicates:
            - name: Path
              args:
                patterns: ${PATH}
          filters:
            - name: CircuitBreaker
              args:
                name: recruitlist
                fallbackUri: forward:/fallbacks/7
            - name: RewritePath
              args:
                regexp: /${PATH}/(?<path>.*)
                replacement: /$\{path}

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:${DISCOVERY_PORT}/eureka
      
```

### service
``` yaml
server:
  port: ${PORT}

spring:
  application:
    name: ${SERVICE_NAME}
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/{DB_NAME}?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: {DB_USERNAME}
    password: {DB_PASSWORD}

  jpa:
    show-sql: false
    generate-ddl: true
    hibernate:
      ddl-auto: update
  
  redis:
    host: ${REDIS_HOST}
    port: ${REDIS_PORT}

eureka:
  instance:
    instance-id: ${INSTANCE_ID}
  client:
    service-url:
      defaultZone: http://127.0.0.1:${DISCOVERY_PORT}/eureka
    fetch-registry: true
    register-with-eureka: true
```
