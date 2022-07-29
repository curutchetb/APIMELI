# API MeLI  🖥️


- 1: Crear una API REST, en donde se pueda envie lista de items y el monto del cupón. devolver items que tendría que comprar el user.
- 2: devuelver el top 5 de items más likeados por los users

# Indice

-  [ApiMeli](#apiMeli)
  -  [Especificaciones](#especificaciones)
  -  [Implementación y tecnologias usadas](#implementación-y-tecnología-usada)
    	- [Local](#local)
-  [Setup](#setup)
  -  [Instrucciones](#instrucciones)
  -  [Uso](#uso)
  -  [API Url](#api-url)
  -  [Servicios](#servicios)
     -  [Crear usuario](#crear-usuario)
     -  [Login](#login)
     -  [Coupon](#coupon)
     -  [Primeros 5 items favoritos generales](#items-favoritos)
     -  [Datos del usuario](#datos-del-usuario)
     -  [Datos de item](#datos-del-item-marcado-como-favorito)
     -  [Lista de items por usuario](#lista-total-de-items-favoritos-del-usuario)
     -  [Eliminar item favorito del usuario](#eliminar-item-de-la-lista-de-favoritos-del-usuario)

## ApiMeli

### Especificaciones

Para la comprensión del desarrollo de la aplicación y el uso de clases y métodos, se provee  `JAVADOC` dentro de la carpeta `doc`.

### Implementación y tecnología usada

#### Local

- [JAVA 11](http://https://www.oracle.com/ar/java/technologies/javase/jdk11-archive-downloads.html "JAVA 11")
- [Apache Maven](https://maven.apache.org/ "Apache Maven")
- [Spring Boot](https://projects.spring.io/spring-boot/)

Para las pruebas de los endpoints se hizo uso de [Postman](https://www.postman.com/downloads/ "Postman")

## Setup
### Instrucciones
Requisitos para correr el proyecto en *forma local*:
  - Instalar Java JDK 11
  - Instalar Maven
  - Ejecutar la clase main para iniciar la App.

También se puede iniciar la aplicación con el comando desde el directorio raíz del proyecto:
```
mvn exec:java -Dexec.mainClass="com.apiMeli.apimeli.ApiMeliApplication"
```
Una vez iniciada la aplicación se pueden realizar las request a la API.

El puerto predeterminado de la API es 8080.

### Uso

Para iniciar la aplicación de forma local, puede recurrir a las [Instrucciones](#instrucciones) 

La idea de la aplicación se basa en  generar un usuario para la compra de items mediante un coupon con un monto específico.

La sesión sera autenticada mediante un token el cual tiene una duración de 2 días.

Una vez el usuario haga el login y esté autenticado y autorizado podra listar una cantidad de items que desee comprar y el monto del coupon. La aplicación le devolverá la lista de items posibles de compra y el monto a gastar.

Además se podrá saber cuales son los 5 items mas favoriteados por todos los usuarios para la compra mediante el coupon.

El usuario podrá obtener los datos con que fue registrado, los datos de un item que haya marcado como favorito como también borrarlo de su lista , siempre y cuando este dentro de su sesión.

### API Url

URL local: http://localhost:8080

### Servicios
#### Crear usuario
Creando un usuario en la app le permitirá luego poder acceder a su sesión.
Se le asignará un userId público para mas seguridad.

Request:
- POST http://localhost:8080/users

Request body:
```
{
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "j@gmail.com",
    "password": "1234"
}
```

Response Status 200 OK:

```
{
    "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "j@gmail.com",
    "items": null
}
```
#### Login
Loguearse y acceder a su sesión le permitirá obtener los datos con que fue registrado, los datos de un item que haya marcado como favorito como tambien borrarlo de su lista , siempre y cuando el user este autenticado.

Request:
- GET http://localhost:8080/users/login

Request body:
```
{
    "email": "j@gmail.com",
    "password": "1234"
}
```

Response Status 200 OK:

Se procederá a copiar el token generado en el header `Authorization` para luego poder autenticar el user. (Ver imagen)

#### Coupon
Se podrá listar una serie de items junto con el monto del coupon y se obtendrá la lista de los items posibles de compra junto con el monto total.
Para esto será necesario pegar en el **headers** el token adquirido al iniciar la sesión.
-- **KEY**: *Authorization*
--**VALUE**: *token copiado*
(Ver imagen)

- POST http://localhost:8080/users/coupon

```
{
    "itemId": [
        "MLA1137105311",
        "MLA645862630",
        "MLA923902194",
        "MLA1138746052",
        "MLA1134764177"],
    "amount": 35000
}
```
Response Status 200 OK: (datos pueden variar)
```
{
    "itemId": [
        "MLA1137105311",
        "MLA645862630",
        "MLA923902194",
        "MLA1138746052"
    ],
    "total": 20690.0
}
```
#### Items favoritos
Se podrá consultar cuales son los 5 items mas favoriteados por todos los usuarios al usar el coupon. No es necesaria la autenticación.

Request:
- GET http://localhost:8080/coupon/favorites

Response Status 200 OK: (ejemplos pueden variar)

```
[{"MLA1138746052":5}, {"MLA1134764177":4}, {"MLA645862630":3}, {"MLA923902194":2}, {"MLA1137105311":1}]
```

#### Datos del usuario
El usuario autenticado podrá acceder a sus propios datos con que fue registrado.
Pegar en el **headers** el token adquirido al iniciar la sesión.
-- **KEY**: *Authorization*
--**VALUE**: *token copiado*

Request:
- GET http://localhost:8080/users

Response Status 200 OK:

```
{
    "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
    "firstName": "Juan",
    "lastName": "Perez",
    "email": "j@gmail.com",
    "items": null
}
```

#### Datos del item marcado como favorito
El usuario autenticado podrá consultar los datos de un item en especifico que este dentro de su lista de favoritos.
Pegar en el **headers** el token adquirido al iniciar la sesión.
-- **KEY**: *Authorization*
--**VALUE**: *token copiado*

Request:
- GET http://localhost:8080/items/{id}

ej: id = MLA1134764177

Response Status 200 OK:

```
{
    "itemId": "MLA1134764177",
    "price": 14499.0,
    "user": {
        "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
        "firstName": "Juan",
        "lastName": "Perez",
        "email": "j@gmail.com",
        "items": null
    }
```
#### Lista total de items favoritos del usuario
El usuario autenticado podrá acceder a su propia lista de items favoritos.
Pegar en el **headers** el token adquirido al iniciar la sesión.
-- **KEY**: *Authorization*
--**VALUE**: *token copiado*

Request:
- GET http://localhost:8080/users/items

Response Status 200 OK: (varía segun user)

```
[
    {
        "itemId": "MLA1138746052",
        "price": 9999.0,
        "user": {
            "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
            "firstName": "Juan",
            "lastName": "Perez",
            "email": "j@gmail.com",
            "items": null
        }
    },
    {
        "itemId": "MLA645862630",
        "price": 2559.0,
        "user": {
            "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
            "firstName": "Juan",
            "lastName": "Perez",
            "email": "j@gmail.com",
            "items": null
        }
    },
    {
        "itemId": "MLA1137105311",
        "price": 833.0,
        "user": {
            "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
            "firstName": "Juan",
            "lastName": "Perez",
            "email": "j@gmail.com",
            "items": null
        }
    },
    {
        "itemId": "MLA923902194",
        "price": 7299.0,
        "user": {
            "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
            "firstName": "Juan",
            "lastName": "Perez",
            "email": "j@gmail.com",
            "items": null
        }
    },
    {
        "itemId": "MLA1134764177",
        "price": 14499.0,
        "user": {
            "userId": "d711fee3-96e0-405f-b178-b28415b2a500",
            "firstName": "Juan",
            "lastName": "Perez",
            "email": "j@gmail.com",
            "items": null
        }
    }
]
```
#### Eliminar item de la lista de favoritos del usuario
El usuario autenticado podrá eliminar un item en específico de su lista de items favoritos.
Pegar en el **headers** el token adquirido al iniciar la sesión.
-- **KEY**: *Authorization*
--**VALUE**: *token copiado*

Request:
- DELETE  http://localhost:8080/items/{id}

ej: id = MLA1134764177

Response Status 200 OK:

```
{
    "name": "DELETE",
    "result": "SUCCESS"
}
```
