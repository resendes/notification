FROM openjdk:14-jdk-alpine
COPY ./target/notification*.jar notification.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "notification.jar"]