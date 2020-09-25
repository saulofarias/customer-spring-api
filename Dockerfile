FROM openjdk:8-jdk-alpine 
MAINTAINER limocs.com.br
VOLUME /tmp
EXPOSE 8080
ADD target/customerapi*SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
