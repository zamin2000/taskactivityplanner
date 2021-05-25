#FROM maven:3.6.3-openjdk-11 AS build
FROM maven:3.6.3-openjdk-15
RUN mkdir app
WORKDIR /app
COPY . . 
#COPY src src
#COPY pom.xml .
CMD mvn -f /app/pom.xml clean package install && sleep 5 && cp /app/target/*.jar app.jar && java -jar app.jar 

#FROM openjdk:11.0-jre
#COPY --from=build /app/target/*.jar app.jar
#CMD ["java", "-jar", "/app.jar"]
