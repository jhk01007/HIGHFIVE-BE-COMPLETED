FROM openjdk:17-slim
VOLUME /tmp
COPY build/libs/data-collector-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-XX:+IgnoreUnrecognizedVMOptions", "-XX:-UseContainerSupport", "-jar", "/app.jar"]