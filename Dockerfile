FROM eclipse-temurin:8-jre-alpine
#FROM openjdk:17
EXPOSE 9000
ADD target/employee-service-v2.0.0-snapshot.jar employee-service-v2.0.0-snapshot.jar
ENTRYPOINT ["java", "-jar","/employee-service-v2.0.0-snapshot.jar"]