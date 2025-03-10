# Challenge API #
# Descripción #
# Este proyecto es una API REST desarrollada en Spring Boot para el challenge backend. La API implementa funcionalidades de cálculo con porcentaje dinámico y un historial de llamadas, utilizando PostgreSQL como base de datos. #

# Tecnologías Utilizadas #
# • Java 21 #
# • Spring Boot 3.4.3 #
# • Spring Data JPA #
# • PostgreSQL #
# • Docker #
# • Lombok #
# • JUnit 5 y Mockito (para pruebas unitarias) #
# • JaCoCo (para la cobertura de pruebas) #

# Requisitos #
# • Docker y Docker Desktop #
# • Java 21 (opcional, para ejecutar localmente sin Docker) #
# • Maven #
# • Postman (opcional, para consumir los endpoints) #

# Instrucciones para Ejecutar el Proyecto #
# Ejecución con Docker Compose #
# El proyecto incluye un archivo docker-compose.yml que levanta tanto la base de datos PostgreSQL como la aplicación. #

# a. Clonar el repositorio #
# Abra una terminal y ejecute: #

# git clone https://github.com/xkzu/challenge-tempo.git #
# cd challenge-tempo #

# b. Configurar las variables de entorno (opcional) #
# Puede modificar el archivo .env para cambiar los valores por defecto de la base de datos. Por defecto, el archivo .env contiene: #

# POSTGRES_DB=challengeDB #
# POSTGRES_USER=challengeuser #
# POSTGRES_PASSWORD=challengepass #

# c. Compilar el proyecto #
# Utilice Maven para compilar y empaquetar la aplicación: #

# mvn clean package #

# Esto generará el archivo JAR en la carpeta target. #

# d. Levantar la aplicación con Docker Compose #
# Desde la raíz del proyecto, ejecute: #

# docker-compose up --build #

# Este comando construirá la imagen de la aplicación, levantará el contenedor de PostgreSQL y ejecutará la API en el puerto 8080. #

# Colección Postman para verificar el funcionamiento de la aplicación: #
# Nombre del archivo ubicado en la raíz del proyecto: Challenge Tempo.postman_collection.json #
# Importar colección a postman y consumir los endpoints #

# Pruebas Unitarias y Cobertura de Código #
# El proyecto cuenta con pruebas unitarias implementadas con JUnit 5 y Mockito para simular escenarios (por ejemplo, fallos del servicio externo). JaCoCo se utiliza para generar el reporte de cobertura. #

# Para ejecutar las pruebas y generar el reporte de cobertura, ejecute: #

# mvn clean test jacoco:report #

# El reporte se generará en la carpeta target/site/jacoco/index.html. Abra este archivo en su navegador para ver la cobertura de código. #

# Configuración #
# La configuración de conexión a la base de datos se define en el archivo application.properties utilizando variables de entorno con valores por defecto. #

# En el entorno Docker, estas propiedades se sobreescriben mediante las variables definidas en el archivo docker-compose.yml. #

# Estructura de Archivos Clave #
# • Dockerfile: Define la imagen base (Eclipse Temurin con Java 21), copia el archivo JAR y establece el entrypoint. #
# • docker-compose.yml: Levanta los contenedores para PostgreSQL y la aplicación. #
# • .env: Archivo para definir las variables de entorno (opcional, pero recomendado). #
# • application.properties: Configuración local de la aplicación. #
# • src/main/java: Código fuente de la API. #
# • src/test/java: Pruebas unitarias con JUnit 5 y Mockito. #

# Notas Adicionales #
# • Volúmenes en Docker: Se utiliza el volumen “db-data” para persistir los datos de PostgreSQL. #
# • Variables de Entorno: La configuración en application.properties utiliza variables de entorno, lo que permite que Docker Compose sobrescriba la configuración con los valores adecuados para el entorno de producción. #