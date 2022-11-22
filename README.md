# Bamboo-BackEnd

### 사용 언어, 프레임워크 및 라이브러리
- java
- spring boot
- jpa
- mybatis
- spring cloud


## application.yml
### config
``` yaml
server:
  port: 8888

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
  port: 8765

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
  port: 8000

spring:
  application:
    name: apigateway-service
  cloud:
    gateway:
      default-filters:
        - name: GlobalFilter
        args:
          baseMessage: Hello Spring Cloud Gateway Global Filter
          preLogger: true
          postLogger: true
      routes:
        - id: user-service
          uri: lb://USER-SERVICE
          predicates:
            - Path=/user-service/sign-in
            - Method=POST
          filters:
            - RemoveRequestHeader=Cookie
            - RewritePath=/user-service/(?<segment>.*), /$\{segment}

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8765/eureka
      
```

### service
``` yaml
server:
  port: 8080

spring:
  application:
    name: user-service
  datasource:
    url: jdbc:mysql://localhost:3306/bamboo?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: {DB_USERNAME}
    password: {DB_PASSWORD}

  jpa:
    show-sql: false
    generate-ddl: true
    hibernate:
      ddl-auto: update

eureka:
  instance:
    instance-id: ${spring.application.name}:${spring.application.instance_id:${random.value}}
  client:
    service-url:
      defaultZone: http://127.0.0.1:8765/eureka
    fetch-registry: true
    register-with-eureka: true
```
