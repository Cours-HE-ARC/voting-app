FROM openjdk:17.0.1-jdk-slim
MAINTAINER seb.chevre@gmail.com
COPY target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]