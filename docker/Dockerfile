FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY build/libs/*.jar app_runcine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app_runcine.jar"]