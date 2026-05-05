FROM gradle:8.14 AS build
WORKDIR /api-cinesuggest


COPY . .

RUN gradle clean bootJar --no-daemon -x test

FROM openjdk:21-ea AS deploy
WORKDIR /app

COPY --from=build /api-cinesuggest/build/libs/*.jar app_runcine.jar


ENTRYPOINT ["java", "-jar", "app_runcine.jar"]

EXPOSE 8080