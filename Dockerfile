FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -Dmaven.test.skip=true -f /usr/src/app/pom.xml clean package

FROM openjdk:11-jre
WORKDIR /usr/app/
COPY --from=build /usr/src/app/target/rest-0.0.1-SNAPSHOT.jar /usr/app/rest.jar

EXPOSE 8080
CMD ["java","-jar","/usr/app/rest.jar"]