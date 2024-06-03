#FROM openjdk:17.0.2-alpine
#RUN apk update && apk add maven
#WORKDIR /app
#COPY . .
#RUN mvn package && mvn clean install -DskipTests
#CMD ["java", "-jar", "target/elaparato-0.0.1-SNAPSHOT.jar"]


FROM openjdk:17.0.2-jdk-slim

# Actualiza el índice de paquetes y instala Maven
RUN apt-get update && apt-get install -y maven

# Copia los archivos necesarios al contenedor
COPY . /app

# Establece el directorio de trabajo
WORKDIR /app

# Compila el proyecto (ajusta según tu proyecto específico)
RUN mvn clean package

# Expone el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/mi-aplicacion.jar"]
