# Specifications
Se evalúan:

buenas prácticas de programación.
Uso de patrones de diseño
Uso de RESTfull
                
Los datos del Cliente son:

•         Nombre (requerido, opcional si trae valor validar caracteres especiales)

•         Apellido paterno (requerido, opcional si trae valor validar caracteres especiales)

•         Materno (requerido, opcional si trae valor validar caracteres especiales)

•         Fecha de nacimiento (requerido, opcional si trae valor validar caracteres especiales)

•         RFC (requerido y De valor único)

•         Numero celular (opcional)

•         Email (opcional, opcional si trae valor validar formato de Email)


✅Diseñar un microservicio RESTFUL para que permita la administración de clientes, la información del Cliente se define más adelante.
El microservicio debe permitir consultar, crear y actualizar los datos de un cliente, la base de datos puede ser Relacional o noSQL.
El microservicio debe recibir un header obligatorio de nombre “DATA”. Como valor debe recibir una cadena hasheada con sha256, la cadena a hashear es “ACLARACIONES”.
Si el header no contiene el valor “ACLARACIONES” esperado, debe rechazar la petición.
Método de consulta por RFC
Creación de Test unitarios (utilizar mock y Mockito)

```
✅Si es base de datos Relacional adjuntar scripts de creación de la base y tabla
✅Opcional puedes agregar un ORM para la persistencia.(Hibernate)
Opcional Puedes agregar un Cache con redis en la consulta del cliente por RFC.
Opcional Se habilite swagger de los endpoints habilitados
✅Opcional Consulta de todos los registros de los clientes con paginación.
Opcional al insertar o actualizar un registro, enviar un mensaje a una cola de mensaje, de preferencia RabbitMQ
Opcional implementar el consumo de los mensajes del RabbitMQ e insertarlos en la base de datos
```

# Solutions
was implemented on spring Boot and Hibernate, web, mockito, maven, etc
Mysql Please use a container with mysql you can reference this link https://hub.docker.com/_/mysql then you can run the container with you config pass and name
then just create database:
```
CREATE DATABASE microservicio_db;
use microservicio_db
```
the create table script is not necessary 'couse Hibernate is on autocreate and it should be crated first time you execute it
but if you need the sql, here it is.
```
CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido_paterno VARCHAR(255),
    apellido_materno VARCHAR(255),
    fecha_nacimiento DATE,
    rfc VARCHAR(13) UNIQUE,
    numero_celular VARCHAR(20),
    email VARCHAR(255)
);
```
## how to run application
the properties file is on the repository please use your credentials and every is done for you use. dont forget download your maven dependecies, now execute your spring boot application, here there an example for endpoint create:
```
Route: {Domain}/clients
Method: Post 
Header: DATA = e38d91567f74abfed468f8175b8eb59b427ddc2997c420ec4137acf3f58bfbcc
Body:
{
  "nombre": "juanito",
  "apellidoPaterno":"martin",
  "apellidoMaterno":"ramirez",
  "fechaNacimiento":"1995-02-14",
  "rfc":"MANM950214CC8",
  "numeroCelular":"9512449673",
  "email":"marcoaaaaa@gmail.com"
}
```
Pagination
```
Route: {Domain}/clients?page=0&size=10
Method: Get 
Header: DATA = e38d91567f74abfed468f8175b8eb59b427ddc2997c420ec4137acf3f58bfbcc

```

you can update, list with the appropiate methods