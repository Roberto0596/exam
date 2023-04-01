# exam
examen de prueba

para acceder a la documentacion, ingresar en la siguiente url

http://[local]/swagger-ui/index.html

version java 17

codigos:

1000 : correcto
1001 : no hay datos para mostrar
1002 : generico
1003 : datos requeridos
1005 : error interno

El script para la creación de la base de datos viene en la carpeta bd/ anexada en el repositorio.

Para utilizar la aplicacion, se requiere agregar en primera instancia
las zonas horarias para poder crear al alumno, después el examen.

para la asignación de examenes alumnos es mediante el servicio
/v1/test-assignation
aquí es donde se tienen las opciones para obtener el historial del alumno.

aunque no haya preguntas, se puede asignar y posteriormente agregar preguntas 
mediante el servicio de crear examen, en donde se puede mandar el objeto de las preguntas también.

una vez teniendo las preguntas y respuestas y ya el examen asignado al estudiante
se utiliza el servicio:

/v1/answer-alumn/evaluate

esto para poder calificar al alumno, en donde solo se le agregará el id del alumno y del examen
con esto se calificará al alumno en ese examen de forma automatica.

más detalles en la documentación de swagger.

De igual forma, por normas del examen, adjunto la coleccion de postman que usé para hacer pruebas.


Saludos =)



