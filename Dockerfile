FROM openjdk:17-jdk-slim
LABEL authors="Hardik"
VOLUME /tmp
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]