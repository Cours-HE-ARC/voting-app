#Dockerfile qui effectue une copie du jar local pour construire l'image
FROM openjdk:17.0.1-jdk-slim
MAINTAINER seb.chevre@gmail.com
COPY target/admin-service.jar admin-service.jar
ENTRYPOINT ["java","-jar","/admin-service.jar"]