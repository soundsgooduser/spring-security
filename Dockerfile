FROM openjdk:8
ADD target/spring.security-1.0.0.jar spring.security-1.0.0.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "spring.security-1.0.0.jar"]