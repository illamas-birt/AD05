# AD05 - Gestor de proyectos de videojuegos
Aplicación web para gestionar proyectos de videojuegos y sus tareas.

## Tecnologías utilizadas
- Java 25 (JDK Temurin)
- Maven
- Spring Boot 4.2.0
- Spring Data JPA
- Spring MVC
- Oracle Database XE 21c
- Oracle JDBC (ojdbc17)
- Thymeleaf
- Bootstrap 5.3.9

## Configuración de Oracle
La configuración se puede cambiar en `src/main/resources/application.properties`.<br>Por defecto se utilizan los valores:
- Servidor: localhost
- Puerto: 1521
- Servicio: XEPDB1
- Usuario: AD05_APP
- Contraseña: variable de entorno `ORACLE_PASSWORD`.

## Arranque
1. Oracle XE 21c iniciado
2. Usuario AD05_APP configurado por el Script de preparación del usuario Oracle.
3. Importar el proyecto Maven en Eclipse.
4. Configurar `ORACLE_PASSWORD` desde Run `Configurations` -> `Environment`.
5. Ejecutar `Ad05Application.java`.

Y con eso ya se puede abrir en el navegador la aplicación en http://localhost:8080.