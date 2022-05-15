FROM openjdk:11
ARG JAR_FILE=target/IMAGENAME.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
#--platform=linux/amd64
