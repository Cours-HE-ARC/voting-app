#Dockerfile multistage qui build le projet et ensuite copie le jar généré
#Build stage
FROM maven:3.8.5-openjdk-17 as maven-build
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /opt/build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true
#Create final image stage
FROM openjdk:17.0.1-jdk-slim
MAINTAINER seb.chevre@gmail.com
COPY --from=maven-build /opt/build/target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]