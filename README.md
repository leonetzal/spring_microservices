# Spring Boot MicroServices

## Introduction

> Proyecto de SpringBoot usando microservicios.

## Estructura del Proyecto

> spring-config-server -> Microservicio que apunta a .properties; en un repositorio privado de Github para la conexión y llaves de OAuth2.

> spring-service-commons -> Microservicio común para reutilizar código de la clase de entidad 'Products'.

> spring-service-eureka -> Microservicio para registrar el Servidor Eureka.

> spring-service-item -> Microservicio Cliente-Rest para manejar los Items de Products.

> spring-service-oauth2 -> Microservicio para el manejo de seguridad de las rutas con OAuth2.

> spring-service-products -> Microservicio  Cliente-Rest para manejar los Products.

> spring-service-users-commons -> Microservicio común para reutilizar código de los Usuarios y Roles.

> spring-service-users -> Microservicio Cliente-Rest para manejar los Users.

> spring-service-zuul -> Microservicio para registrar el Proxy Zuul.
