
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY patientservice/target/patientservice-0.0.1-SNAPSHOT.jar patientservice.jar

EXPOSE 4000


ENTRYPOINT ["java", "-jar", "patientservice.jar"]
