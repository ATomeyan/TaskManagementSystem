FROM maven:3.9.6-openjdk-17

LABEL author="Artur Tomeyan"

COPY target/TaskManagementSystem-0.0.1-SNAPSHOT.jar TaskManagementSystem-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/TaskManagementSystem-0.0.1-SNAPSHOT.jar"]