FROM openjdk:8-alpine

EXPOSE 8080

ARG JAR_FILE=build/libs/traffic-monitor-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} traffic-monitor.jar

ENTRYPOINT ["java", "-jar", "/traffic-monitor.jar"]

