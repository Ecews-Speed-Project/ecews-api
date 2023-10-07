FROM openjdk:11
ADD target/ihvncr-api-0.0.1-SNAPSHOT.jar ihvncr-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","ihvncr-api-0.0.1-SNAPSHOT.jar"]