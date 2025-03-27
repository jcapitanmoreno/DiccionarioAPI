FROM openjdk:17-amazon-corretto-alpine-jdk

COPY out/artifacts/DiccionarioAPI_jar/DiccionarioAPI.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]