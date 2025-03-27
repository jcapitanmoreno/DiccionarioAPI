FROM openjdk:21-ea-1-jdk

COPY out/artifacts/DiccionarioAPI_jar/DiccionarioAPI.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]