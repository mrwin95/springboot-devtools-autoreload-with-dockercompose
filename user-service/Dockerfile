FROM eclipse-temurin:17-jdk-focal as build

WORKDIR /src/build

COPY .mvn ./.mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} name-service.jar
#ENTRYPOINT ["java", "-jar", "/name-service.jar"]
#EXPOSE 8761