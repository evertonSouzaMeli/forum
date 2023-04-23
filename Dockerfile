FROM maven:3.5-jdk-11 AS build

COPY src /usr/src/app/src

COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM adoptopenjdk/openjdk11

COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/usr/app/app.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]