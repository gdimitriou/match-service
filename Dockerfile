FROM java:8-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} match-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/match-service-0.0.1-SNAPSHOT.jar"]