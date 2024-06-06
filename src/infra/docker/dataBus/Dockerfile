# --- Base image for java applications ---
FROM eclipse-temurin:latest

# --- ENV variables ---

ENV REDIS_API_URL=
ENV ENDERECOS_URL=
ENV PLANOS_URL=
ENV PLANOS_USUARIOS_URL=
ENV USUARIOS_URL=
ENV USUARIOS_PF_URL=
ENV USUARIOS_PJ_URL=
ENV SERVER_PORT=8090

COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
