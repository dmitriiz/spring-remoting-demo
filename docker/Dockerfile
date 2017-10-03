FROM openjdk:8-jre-alpine
MAINTAINER dmitriiz@hotmail.com
ADD demo.jar /
EXPOSE ["8080"]
VOLUME ["/tmp"]
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo.jar"]