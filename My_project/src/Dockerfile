FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY build/libs/my-application.jar /app/my-application.jar
EXPOSE 8080

ENTRYPOINT [ "java", "-jar","/app/my-application.jar" ]
