FROM eclipse-temurin:18-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway-service.jar
#ENTRYPOINT ["java", "-jar", "/name-service.jar"]
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8002","-jar","gateway-service.jar"]
EXPOSE 8080
EXPOSE 8002