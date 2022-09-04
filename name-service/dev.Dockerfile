#FROM eclipse-temurin:18-jre
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} name-service.jar
#ENTRYPOINT ["java", "-jar", "/name-service.jar"]
#ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8001","-jar","name-service.jar"]
#EXPOSE 8761
#EXPOSE 8001
FROM eclipse-temurin:18-focal
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME

COPY run.sh ./
