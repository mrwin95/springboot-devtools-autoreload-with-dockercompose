#FROM eclipse-temurin:latest
#FROM eclipse-temurin:8-jre-focal
#FROM eclipse-temurin:18-jre
#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} user-service.jar
##"-Dspring.profiles.active=docker",
#ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","user-service.jar"]
#EXPOSE 8081
#EXPOSE 8000
FROM eclipse-temurin:18-focal
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME

COPY run.sh ./
