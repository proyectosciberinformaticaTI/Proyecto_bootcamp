FROM openjdk:8-jdk-alpine
ADD target/Proyecto_bootcamp-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]