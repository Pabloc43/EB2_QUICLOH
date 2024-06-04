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
EXPOSE 9090

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/mi-aplicacion.jar"]
