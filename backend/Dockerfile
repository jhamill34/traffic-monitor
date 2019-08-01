#####################
#   Build Stage     #
#####################
FROM openjdk:8-alpine as builder
WORKDIR /app 
COPY . /app
RUN ./gradlew --no-daemon build

#####################
#   Final Stage     #
#####################
FROM openjdk:8-alpine
EXPOSE 8080

RUN apk add --no-cache bash 

# Copy the built executable
COPY --from=builder /app/build/libs/traffic-monitor-0.0.1-SNAPSHOT.jar traffic-monitor.jar

# Copy the startup scripts
COPY --from=builder /app/wait-for-it.sh wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "-t", "15", "db:5432", "--", "java", "-jar", "/traffic-monitor.jar"]
