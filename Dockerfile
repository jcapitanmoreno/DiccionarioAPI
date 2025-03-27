FROM openjdk:21-ea-1-jdk

COPY target/DiccionarioAPI-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]