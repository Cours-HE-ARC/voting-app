FROM openjdk:17.0.1-jdk-slim
MAINTAINER seb.chevre@gmail.com
COPY target/voting-app-admin.jar voting-app-admin.jar
ENTRYPOINT ["java","-jar","/voting-app-admin.jar"]