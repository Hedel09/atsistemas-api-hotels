## Bienvenido al proyecto de API REST de Antonio Martínez para la formación de Juniors en java de ATSistemas ## 

---

Antes de comentar la configuración necesaria para arrancar me gustaría decir que el proyecto ha sido programado completamente
en inglés para homogeneizarlo con los nombres en inglés de las entidades. Espero que no conlleve ninguna penalización por no estar en español.

Ahora sí, comenzaremos indicando lo que damos por supuesto para iniciar la configuración del proyecto:
 - Una base de datos PostgreSQL. Recomendable también un gestor de base de datos como DBeaver
 - Alguna manera de realizar las peticiones http a la API de manera cómoda y, aunque podríamos usar un navegador, recomiendo el uso de la aplicación
 de Postman para la tarea.
 - Eclipse o algún otro IDE para poder importar, ejecutar y testear el código ed la API. En la guía supondremos Eclipse debido a que es el IDE que
 usamos en las clases y con el que más experiencia tengo
 
 Con todo esto en cuenta, pasamos a los pasos que debemos dar para ejecutar y revisar el proyecto.
 
 ---
 
 ### PASO 1 - Importar el proyecto ###
 
 Para importar el proyecto, primero debemos descargarlo de GitHub, su url es [https://github.com/Hedel09/atsistemas-api-hotels](https://github.com/Hedel09/atsistemas-api-hotels) (esta no es la url para clonar el repositorio es es solo la url para ir a la página principal del proyecto y desde ahí hacer lo que se desee). También podremos clonar el proyecto si así lo preferimos. La rama que contiene la versión final del código es "main".
 Con el proyecto ya descargado o clonado, lo próximo es importarlo a eclipse. Para ello haremos click derecho en el explorador de paquetes > import > Existing Maven Projects y seleccionamos el proyecto descargado o la carpeta del proyecto allí donde lo hayamos clonado con git.
 También podemos clonar directamente el proyecto desde git a través de Eclipse pero desaconsejo esta opcíón puesto que es algo más tediosa a mi parecer, en caso de que quisieramos, tendríamos que copiar la url de clonado del proyecto, luego irnos a eclipse hacer click derecho en el explorador de paquetes > import > Projects from Git > Clone URI se pegará automáticamente la url que teníamos copiada (si no pasara, se puede pegar manualmente), seleccionaremos las ramas que queremos traer del repositorio remoto (en este caso nos interesa main), y daremos nombre al repositorio de git en nuestra máquina. Hecho esto habremos clonado el repositorio de git, ahora falta importarlo en eclipse. Se nos muestran varias opciones, recomiendo import as general project y le daremos al botón "finish" después de esto. El proyecto estará en nuestro explorador de paquetes pero sin la configuración correcta, para solucionar esto, le daremos click derecho al proyecto y buscaremos la opción de configure que está por debajo, se nos desplegarán algunas opciones y seleccionaremos la que diga "Convert to Maven Project".
 
 ---
 
### PASO 2 - Importar la base de datos ###

Para este paso solo debemos coger las sentencias SQL que encontramos dentro de src/main/resources/ en los archivos schema.sql y data.sql, debemos ejecutar primero las sentencias contenidas en schema.sql y posteriormente las sentencias de data.sql. 
Es posible que tengamos tablas con los mismos nombres de haber corregido otros proyectos. En ese caso recomiendo descomentar las sentencias DROP que vienen comentadas en los archivos o, si no quisieramos perder los datos almacenados en estas tablas por el motivo que sea, cambiar mínimamente las senteencias para que almacenen las tablas en un esquema distinto y cambiar la configuración del DataSource en el archivo aplication.properties para que acceda a ese esquema.

---

### PASO 3 - Ejecutar el proyecto ###

Para ejecutar el proyecto debemos ir a la claseApiHotelsApplication.java dentro del paquete com.atsistemas.formacion.base.apihotels y pulsar click derecho sobre el código > Run As > Java Application. Hecho esto el proyecto debería ejecutarse y desplegarse en el tomcat de SpringBoot.

debo destacar que al comienzo del desarrollo de la API me encontré con un extraño error que no se si es de la configuración de mi ordenador o de algún factor externo al proyecto o si es del proyecto en sí. Solo puedo garantizar que lo he arrancado y testeado todo multitud de veces en mi eclipse.
 El error es el siguiente:
 
<table><tr><td>
***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
</tr></td></table>


 Conseguí solucionarlo la primera vez añadiendo un espacio en el application.properties en la propiedad " spring.datasource.driver-class-name= org.postgresql.Driver ".


**Con todo esto el proyecto debería estar listo para ser probado.**

---

Ahora hay dos cosas que se pueden hacer:

### TESTS ###

Para correr los tests, nos iremos al archivo dee tests que queramos ejecutar (están dentro de src/test/java dentro del paquete com.atsistemas.formacion.base.apihotels.servic.impl) y pulsaremos click derecho > Run As > JUnit Test.

### FUNCIONALIDADES ###

Voy primero a mapear las peticiones http que hay que hacer para cada funcionalidad de las pedidas y después explico algunas cosas sobre restricciones.
 Todos los RequestBody son tipo raw(json).
 
 <table>
    <th>Funcionalidad</th>
    <th>Verbo y url</th>
    <th>RequestBody</th>
	<tr>
		<td>Crear Hotel </td>	
		<td>POST http://localhost:8080/hotels </td>
		<td>
		 {
		    "name": String,
		    "category": Integer
		 }
		 </td>	
	</tr>
	<tr>
		<td>Actualizar Hotel </td>
		<td>PUT http://localhost:8080/hotels/{id} </td>
		<td>
		{
		   "name": String,
		   "category": Integer
		}
		</td>
	</tr>
	<tr>
 		<td>Consultar Hotel </td>
		<td>GET http://localhost:8080/hotels/{id}</td>
		<td></td>
 	</tr>
	<tr>
 		<td>Consulta de hoteles </td>
		<td>GET http://localhost:8080/hotels</td>
		<td></td>
 	</tr>
	<tr>
 		<td>Abrir disponibilidad </td>
		<td>POST http://localhost:8080/availabilities </td>
		<td>
		{
		    "idHotel": Integer,
		    "checkIn": Date(YYYY-MM-DD),
		    "checkOut": Date(YYYY-MM-DD),
		    "rooms": Integer
		}
		</td>
	</tr>
	<tr>
 		<td>Consulta de disponibilidad </td>
		<td>GET http://localhost:8080/hotels/availability </td>
		<td>
		{
		    "checkIn": Date(YYYY-MM-DD),
		    "checkOut": Date(YYYY-MM-DD),
		    (opcional) "category": Integer,
		    (opcional) "name": String
		}
	</td>
	</tr>
	<tr>
 		<td>Reserva de habitación </td>
		<td>POST http://localhost:8080/bookings </td>
		<td>
		{
		    "idHotel": Integer,
		    "dateFrom": Date(YYYY-MM-DD),
		    "dateTo": Date(YYYY-MM-DD),
		    "email": String
		}
		</td>
	</tr>
	<tr>
 		<td>Consulta de reservas hotel </td>
		<td>GET http://localhost:8080/hotels/{idHotel}/bookings </td
		<td>
		{
		    "dateFrom": Date(YYYY-MM-DD),
		    "dateTo": Date(YYYY-MM-DD)
		}
		</td>
	</tr>
	<tr>
 		<td>Obtener reserva </td>
		<td>GET http://localhost:8080/bookings/{id} </td>
		<td></td>
 	</tr>
	<tr>
 		<td>Cancelar reserva </td><td>DELETE http://localhost:8080/bookings/{id}</td><td></td>
	</tr>
 </tr></td></table>
 
 Esas serían las funcionalidades que se piden y cómo acceder a los recursos que dichas funcionalidades implican.
 
 RESTRICCIONES DE LA API
 En la API he establecido algunas restricciones sobre las fechas: 
  - No se puede crear ni acceder a recursos con fechas en el pasado
  - La fecha de inicio en cualquier situacion debe ser anterior a la fecha de fin
