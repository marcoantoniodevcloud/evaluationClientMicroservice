##Specifications
Se evalúan:

buenas prácticas de programación.
Uso de patrones de diseño
Uso de RESTful
 

Diseñar un microservicio RESTFUL para que permita la administración de clientes, la información del Cliente se define más adelante.
El microservicio debe permitir consultar, crear y actualizar los datos de un cliente, la base de datos puede ser Relacional o noSQL.
El microservicio debe recibir un header obligatorio de nombre “DATA”. Como valor debe recibir una cadena hasheada con sha256, la cadena a hashear es “ACLARACIONES”.
Si el header no contiene el valor “ACLARACIONES” esperado, debe rechazar la petición.
Método de consulta por RFC
Creación de Test unitarios (utilizar mock y Mockito)
Si es base de datos Relacional adjuntar scripts de creación de la base y tabla
Opcional puedes agregar un ORM para la persistencia.
Opcional Puedes agregar un Cache con redis en la consulta del cliente por RFC.
Opcional Se habilite swagger de los endpoints habilitados
Opcional Consulta de todos los registros de los clientes con paginación.
Opcional al insertar o actualizar un registro, enviar un mensaje a una cola de mensaje, de preferencia RabbitMQ
Opcional implementar el consumo de los mensajes del RabbitMQ e insertarlos en la base de datos
               

Los datos del Cliente son:

•         Nombre (requerido, opcional si trae valor validar caracteres especiales)

•         Apellido paterno (requerido, opcional si trae valor validar caracteres especiales)

•         Materno (requerido, opcional si trae valor validar caracteres especiales)

•         Fecha de nacimiento (requerido, opcional si trae valor validar caracteres especiales)

•         RFC (requerido y De valor único)

•         Numero celular (opcional)

•         Email (opcional, opcional si trae valor validar formato de Email)