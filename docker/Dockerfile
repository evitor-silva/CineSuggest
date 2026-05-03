FROM gradle:8.14-jdk-21-and-24 AS build
WORKDIR /api-cinesuggest

COPY ../.. .

RUN ./gradlew clean bootJar --no-daemon -x test

RUN cp /api-cinesuggest/build/libs/*.jar app_runcine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app_runcine.jar"]