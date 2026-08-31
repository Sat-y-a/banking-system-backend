FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/banking-system-backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
