FROM openjdk:13-jdk-alpine

EXPOSE 5432
EXPOSE 8080

RUN mkdir -p /app
COPY ./build/libs/CaloriesMeter-1.0.jar /app/CaloriesMeter.jar

ENTRYPOINT ["java","-jar","/app/CaloriesMeter.jar"]