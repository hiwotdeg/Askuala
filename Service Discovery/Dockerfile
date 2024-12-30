FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/service-discovery-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8761
