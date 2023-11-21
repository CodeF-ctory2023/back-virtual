# Módulo Socios - Fabrica Escuela

Este es el módulo de Socios de la plataforma Fabrica Escuela, que permite la gestión y clasificación de socios en la organización.

## Requisitos Previos
Asegúrate de tener instalados los siguientes componentes antes de ejecutar la aplicación:

- Java Development Kit (JDK) 8 o superior: [Descargar e instalar JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Spring Boot: Se incluirá automáticamente como dependencia de Maven/Gradle en el proyecto.
- Oracle Database (o cualquier otra base de datos que desees utilizar): Asegúrate de configurar las propiedades de conexión en el archivo `application.properties` (ver más abajo).

## Instalación

1. Clona este repositorio o descarga el código fuente.
2. Abre el proyecto en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA o Eclipse).

## Ejecución

Para ejecutar la aplicación, puedes seguir estos pasos:

1. Asegúrate de que tu base de datos esté configurada correctamente en el archivo `application.properties`.
2. Ejecuta la clase principal `ModuloSociosApplication` como una aplicación Java.

La aplicación se iniciará y estará disponible en `http://localhost:8081`. Puedes acceder a la API y probar los diferentes endpoints a través de herramientas como Postman.

## Configuración de la Base de Datos

En el archivo `src/main/resources/application.properties`, encontrarás la configuración de la base de datos. Asegúrate de proporcionar los detalles de tu base de datos:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect

```

## KAFKA ##

# Instalación de Kafka con Docker

## Configuración de Docker Network

Crea una red Docker para facilitar la comunicación entre los contenedores Kafka y Zookeeper:

```bash
docker network create kafka-net
```

## Paso 1: Instalar y Ejecutar Wurstmeister Kafka

Ejecuta el siguiente comando para instalar y ejecutar la imagen de Wurstmeister Kafka:

```bash
docker run -d --name kafka --network kafka-net -p 9092:9092 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT="//localhost:9092" \
-e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP="PLAINTEXT:PLAINTEXT" \
-e KAFKA_ZOOKEEPER_CONNECT="zookeeper:2181" wurstmeister/kafka
```

## Paso 2: Instalar y Ejecutar Confluent Kafka

```bash
# Descargar la imagen de Confluent Kafka
docker pull confluentinc/cp-kafka

# Ejecutar el contenedor de Kafka con Confluent
docker run -d --name kafka --network kafka-net -p 9092:9092 -p 19092:19092 \
-e KAFKA_BROKER_ID="1" \
-e KAFKA_ADVERTISED_LISTENERS="PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:19092" \
-e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP="PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT" \
-e KAFKA_INTER_BROKER_LISTENER_NAME="PLAINTEXT" \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR="1" \
-e KAFKA_ZOOKEEPER_CONNECT="zookeeper:2181" confluentinc/cp-kafka
```

## Paso 3: Instalar y Ejecutar Confluent Zookeeper

```bash
# Descargar la imagen de Confluent Zookeeper
docker pull confluentinc/cp-zookeeper

# Ejecutar el contenedor de Zookeeper con Confluent
docker run -d --name zookeeper --network kafka-net -p 2181:2181 \
-e ZOOKEEPER_CLIENT_PORT="2181" \
-e ZOOKEEPER_TICK_TIME="2000" \
confluentinc/cp-zookeeper
```

## Paso 4: Crear un Tópico en Kafka

```bash
# Acceder al shell de Kafka
docker exec -it kafka /bin/sh

# Crear un tópico
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic KafkaPruebasFabrica
```

## Paso 5: Escuchar un Tópico en Kafka

```bash
# Escuchar un tópico
kafka-console-consumer --bootstrap-server localhost:9092 --topic KafkaPruebasFabrica --from-beginning
```


## Acceso a Swagger

Puedes acceder a la interfaz Swagger para explorar y probar los endpoints de la API. Asegúrate de que la aplicación esté en ejecución antes de acceder a Swagger.

- **Swagger UI:** [http://localhost:8081/api/swagger-ui/index.html#/](http://localhost:8081/api/swagger-ui/index.html#/)

### Instrucciones:

1. Asegúrate de que la aplicación esté en ejecución.
2. Abre el enlace proporcionado en tu navegador.
3. Explora los diferentes endpoints y realiza pruebas directamente desde Swagger UI.

