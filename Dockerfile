FROM openjdk:17-amazon-corretto-alpine-jdk

COPY target/DiccionarioAPI-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]