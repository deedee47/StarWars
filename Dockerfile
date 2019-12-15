FROM openjdk:8
ADD target/deestarwars-0.0.1-SNAPSHOT.jar deestarwars-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "deestarwars-0.0.1-SNAPSHOT.jar"]