# Usa una imagen base con Java 21 (Eclipse Temurin es una buena opcion)
FROM eclipse-temurin:21-jdk-alpine

# Define un volumen para almacenar archivos temporales
VOLUME /tmp

# Argumento para el nombre del archivo JAR generado, por defecto challenge-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=target/challenge-0.0.1-SNAPSHOT.jar

# Copia el JAR en la imagen y lo renombra a app.jar
COPY ${JAR_FILE} app.jar

# Define el entrypoint para ejecutar la aplicacion
ENTRYPOINT ["java", "-jar", "/app.jar"]
