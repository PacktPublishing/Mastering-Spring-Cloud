FROM openjdk:8u151-jdk-slim-stretch
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ENV SPRING_PROFILES_ACTIVE zone1
ENV EUREKA_DEFAULT_ZONE http://localhost:8761/eureka/
ADD target/customer-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx160m", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Deureka.client.serviceUrl.defaultZone=${EUREKA_DEFAULT_ZONE}", "/app.jar"]
EXPOSE 8092