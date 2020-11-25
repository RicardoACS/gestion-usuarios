# Gestión de Usuarios

Aplicación que permite mantener a los usuarios.

## Contenido
* [PreRequesitos](##Prerequisitos)
* [Despligue](##Despligue)

## Prerequisitos

1. Tener instalado [Gradle](https://gradle.org/install/)
2. Configurado el comando gradle en lineas de comando (gradle -v)

## Despligue

Para hacer el despligue y uso de la aplicación, se debe lanzar el siguiente comando en el terminal:
```
cd /[carpeta del proyecto donde se encuentra el build.gradle]
y 
gradle Tasks bootRun
```

Al hacer el lanzamiento de la aplicación, hay que ingresar a la siguiente URL para hacer uso de esta:

```
Para ver los endpoint disponibles
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

Para ver la DB local
http://localhost:8080/h2-console
```
