FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
# Copy the executable JAR file from the host file system to the container
COPY target/cathay-united-bank.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
