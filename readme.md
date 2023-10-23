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
