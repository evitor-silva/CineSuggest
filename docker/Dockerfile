FROM gradle:8.14 AS build
WORKDIR /api-cinesuggest


COPY . .

RUN gradle clean bootJar --no-daemon -x test

FROM eclipse-temurin:21-jdk AS deploy
WORKDIR /app

COPY --from=build /api-cinesuggest/build/libs/*.jar app_runcine.jar

RUN chmod 555 app_runcine.jar

USER springuser

ENTRYPOINT ["java", "-jar", "app_runcine.jar"]

EXPOSE 8080