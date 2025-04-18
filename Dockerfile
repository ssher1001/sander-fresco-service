#Use an open jdk base image
FROM openjdk:17-alpine
EXPOSE 8080
ADD target/sander-fresco-service.jar sander-fresco-service.jar
ENTRYPOINT ["java","-jar","/sander-fresco-service.jar"]
