FROM openjdk:8-jdk-alpine
ADD target/Proyecto_bootcamp-0.0.1-SNAPSHOT*.jar Proyecto.jar
ENTRYPOINT ["java","-jar","Proyecto.jar"]