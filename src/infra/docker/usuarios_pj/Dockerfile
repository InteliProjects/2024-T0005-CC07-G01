# --- Base image for java applications ---
FROM eclipse-temurin:latest

# --- ENV variables ---
ENV DATABASE_URL=
ENV DATABASE_USER=
ENV DATABASE_PASSWORD=
ENV SERVER_PORT=8086

COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
