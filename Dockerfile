FROM openjdk:11

WORKDIR /app
COPY . .

COPY target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar", "com.too_codemen.Application", "--spring.config.location=src/main/resources/application.properties"]
