FROM maven:3-jdk-8 as CONSTRUCTOR

#1. CREA DIRECTORIO 'build':  
RUN mkdir -p /build

#2. DEFINIR UBICACION: 
WORKDIR /build

#3. COPIAR 'pom.xml' A DIRECTORIO 'build': 
COPY pom.xml /build

#4. DESCARGAR DEPENDENCIAS 'MAVEN': 
RUN mvn -B dependency:resolve dependency:resolve-plugins

#5. COPIAR 'src' A DIRECTORIO '/build/src': 
COPY src /build/src

#6. EJECUTAR 'MAVEN': 
RUN mvn clean package

#//----------------------------------------------------------------//#

FROM openjdk:8-jdk-slim as RUNTIME

#7. DOCUMENTANDO: 
MAINTAINER cesar guerra cesarricardo_guerra19@hotmail.com

#8. EXPONER PUERTO '8080': 
EXPOSE 8080

#9. CREAR 'VARIABLE DE ENTORNO' 'APP_HOME': 
ENV APP_HOME /app

#10. CREAR 'VARIABLE DE ENTORNO' 'JAVA_OPTS':  
ENV JAVA_OPTS=""

#11. CREANDO DIRECTORIO 'BASE': 
RUN mkdir $APP_HOME

#12. CREANDO DIRECTORIO PARA 'ARCHIVOS DE CONFIGURACION': 
RUN mkdir $APP_HOME/config

#13. CREANDO DIRECTORIO PARA 'LOGs': 
RUN mkdir $APP_HOME/log

#14. CREANDO 'VOLUME' PARA 'ARCHIVOS DE CONFIGURACION': 
VOLUME $APP_HOME/config

#15. CREANDO 'VOLUME' PARA 'LOGs':  
VOLUME $APP_HOME/log

#16. DEFINIR UBICACION: 
WORKDIR $APP_HOME

#17. COPIAR .JAR DE LA IMAGEN:  
COPY --from=CONSTRUCTOR /build/target/*.jar app.jar

#18. EJECUTAR 'JAR': 
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
