# Api-Automation-Test-Serenity-Rest

Realiza la automatización de las apis de la pagina [Rqres](https://reqres.in/) todo realizado con [Gradle](https://gradle.org/), [Java](https://www.java.com/es/), [SerenityBDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html), [Cucumber](https://cucumber.io/) y Screenplay.

## Estructura Codigo Fuente

La estructura del codigo fue realizada con Screenplay de la siguiente forma:
<table>
  <tr>
  <th>Interactions</th>
  <td>
    <h6>Contiene todas las interaciones que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Questions</th>
  <td>
    <h6>Contiene todas las questions que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Utils</th>
  <td>
    <h6>Contiene todo el codigo en propio de java que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Runners</th>
  <td>
    <h6>Contiene todos los ejecutores de las pruebas automatizadas</h6>
  </td>
</tr>
  <tr>
  <th>Steps Definitions</th>
  <td>
    <h6>Contiene todos los pasos de la ejecucion de cada prueba automatizada</h6>
  </td>
</tr>
  <tr>
  <th>Features</th>
  <td>
    <h6>Contiene todos los esenarios codificados en lenguaje Gherking</h6>
  </td>
</tr>
</table>

### Interactions

#### GetRest

Permite obtener la información de los usuarios y recursos de las apis de la pagina.

```java
public class GetRest implements Interaction{
	
	private String endPoint;

	public GetRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Get.resource(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
						.header("Accept","*/*")));
	}

	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(GetRest.class).withProperties(endPoint);
	}
}
```

#### PostRest

Permite enviar la información de los usuarios y recursos de las apis de la pagina.

```java
public class PostRest implements Interaction{

	private String endPoint;

	public PostRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		
		actor.attemptsTo(Post.to(endPoint)
				.with(request -> request
						.header("Content-Type","application/json")
						.header("Accept","*/*")
				.body(Metodos.leerArchivoJson())));
	}
	
	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(PostRest.class).withProperties(endPoint);
	}
}
```

#### PutRest

Permite actualizar la información de los usuarios y recursos de las apis de la pagina.

```java
public class PutRest implements Interaction{

	private String endPoint;
	
	public PutRest(String endPoint) {
		this.endPoint = endPoint;
	}
	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Put.to(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
						.header("Accept","*/*")
				.body(Metodos.leerArchivoJson())));
		
		
	}
	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(PutRest.class).withProperties(endPoint);
	}
}
```

#### PatchRest

Permite actualizar la información de los usuarios y recursos de las apis de la pagina.

```java
public class PatchRest implements Interaction{

	private String endPoint;
	
	public PatchRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Patch.to(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
						.header("Accept","*/*")
						.body(Metodos.leerArchivoJson())));
	}

	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(PatchRest.class).withProperties(endPoint);
	}
}
```

#### DeletehRest

Permite eliminar la información de los usuarios y recursos de las apis de la pagina.

```java
public class DeleteRest implements Interaction{

	private String endPoint;
	
	public DeleteRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Delete.from(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
				.header("Accept","*/*")));
	}
	
	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(DeleteRest.class).withProperties(endPoint);
	}
}
```

### Questions

#### CodigoEstado

Esta clase permite validar que el codigo de estado sea igual al codigo de estado esperado.

```java
public class CodigoEstado implements Question<Boolean>{

	private int codigoExpectativa;
	
	public CodigoEstado(int codigoExpectativa) {
		this.codigoExpectativa = codigoExpectativa;
	}	
	
	public static CodigoEstado delServicio(int codigoExpectativa) {
		return new CodigoEstado(codigoExpectativa);
	}
	
	@Override
	public Boolean answeredBy(Actor actor) {
		return String.valueOf(SerenityRest.lastResponse().statusCode()).equals(String.valueOf(codigoExpectativa));
	}
}
```

### Utils

#### Metodos

Esta clase contiene el metodo leerArchivoJson el cual permite leer un archivo Json que contiene el Body Request de las apis.

```java
public class Metodos {
	
	public static String jsonBodyRequestPath;
	
	public static JSONObject leerArchivoJson() {
		Object ob = null;
		try {
			ob = new JSONParser().parse(new FileReader("src/test/resources/dataJson/"+jsonBodyRequestPath+".json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jsonRequerimiento = (JSONObject) ob;
		
		return jsonRequerimiento;
	}
}
```

### Runners

#### ActualizarParcheUsuarioRunner

Ejecuta llama los pasos asignados en el feature `ActualizarParcheUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/ActualizarParcheUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class ActualizarParcheUsuarioRunner {
}
```

#### ActualizarUsuarioRunner

Ejecuta llama los pasos asignados en el feature `ActualizarUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/ActualizarUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class ActualizarUsuarioRunner {

}
```

#### CrearUsuarioRunner

Ejecuta llama los pasos asignados en el feature `CrearUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/CrearUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class CrearUsuarioRunner {

}
```

#### EliminarUsuarioRunner

Ejecuta llama los pasos asignados en el feature `EliminarUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/EliminarUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class EliminarUsuarioRunner {

}
```

#### ListaRecursosRunner

Ejecuta llama los pasos asignados en el feature `ListaRecursos.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/ListaRecursos.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class ListaRecursosRunner {

}
```

#### ListaUsuariosRunner

Ejecuta llama los pasos asignados en el feature `ListaUsuarios.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/ListaUsuarios.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class ListaUsuariosRunner {

}
```


#### LoginUsuarioRunner

Ejecuta llama los pasos asignados en el feature `LoginUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/LoginUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class LoginUsuarioRunner {

}
```

#### RegistroUsuarioRunner

Ejecuta llama los pasos asignados en el feature `RegistroUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/RegistroUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class RegistroUsuarioRunner {

}
```

### StepsDefinitions

Los steps definitions contienen todos los metodos llamados mediante el `Runner` al `Feature`. Los metodos ejecutan las `interactions` y `questions` mediante un `actor`.

#### ActualizarParcheUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Actualizar parche de usuario, este llama a la interacion `PatchRest` y le envia un String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class ActualizarParcheUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "ActualizacionUsuario";
	}
	
	@Cuando("^realice la solicitud para actualizar el parche de un usuario en (.*)$")
	public void realiceLaSolicitudParaActualizarElParcheDeUnUsuarioEnApiUsers(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PatchRest.on(endPoint));
	}
}
```

### ActualizarUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Actualziar usuario, este llama a la tarea `PutRest` y le envia un String con el valor del endPoint y ejecuta la questions `CodigoEstado` de Screenplay.

```java
public class ActualizarUsuarioStepDefinitions {
	
	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "ActualizacionUsuario";
	}
	
	@Cuando("^realice la solicitud para actualizar un usuario en (.*)$")
	public void realiceLaSolicitudParaActualizarUnUsuarioEnApiUsers(String endPoint) {
	    OnStage.theActorInTheSpotlight().attemptsTo(PutRest.on(endPoint));
	}
	
}
```

#### CrearUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Crear usuario, este llama a la tarea `PostRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class CrearUsuarioStepDefinitions {	
	
	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "CreacionUsuario";
	}
	
	@Cuando("^realice la solicitud para crear un usuario en (.*)$")
	public void realiceLaSolicitudParaCrearUnUsuarioEnApiUsers(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));
	}

}
```

#### EliminarUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Eliminar usuario, este llama a la tarea `DeleteRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class EliminarUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Cuando("^realice la solicitud para eliminar un usuario en (.*)$")
	public void realiceLaSolicitudParaEliminarUnUsuarioEnApiUsers(String endPoint) {
	    OnStage.theActorInTheSpotlight().attemptsTo(DeleteRest.on(endPoint));
	}
}
```

#### ListaRecursosStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Lista de recursos, este llama a la tarea `GetRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class ListaRecursosStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Cuando("^realice la solicitud para obtener la lista de recursos en (.*)$")
	public void realiceLaSolicitudParaObtenerLaListaDeRecursosEnApiUnknown(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(GetRest.on(endPoint));
	}
}
```

#### ListaUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Lista de usuarios, este llama a la tarea `GetRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class ListaUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Dado("^que me encuentro en la url (.*) de reqres$")
	public void queMeEncuentroEnLaUrlHttpsReqresInDeReqres(String url) {
		OnStage.theActorCalled("").whoCan(CallAnApi.at(url));
	}

	@Cuando("^realice la solicitud para obtener la lista de usuarios en (.*)$")
	public void realiceLaSolicitudParaObtenerLaListaDeUsuariosEnApiUsersPage(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(GetRest.on(endPoint));
	}

	@Entonces("^podre observar el estado (\\d+) de la solicitud$")
	public void podreObservarElEstadoDeLaSolicitud(int estado) {
		OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(CodigoEstado.delServicio(estado)));
	}
}
```

#### LoginUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Login de usuario, este llama a la tarea `PostRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class LoginUsuarioStepDefinitions {

	@Before
	public void before(Scenario scenario) throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "Login"+scenario.getName().split(" ")[4];
	}
	
	@Cuando("^realice la solicitud para loguear un usuario en (.*)$")
	public void realiceLaSolicitudParaLoguearUnUsuarioEnApiLogin(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));
	}
}
```

#### RegistroUsuarioStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Registro de usuario, este llama a la tarea `PostRest` y le envia una variable tipo String con el valor del endPoint y ejecuta la questions `CodigoEstado`.

```java
public class RegistroUsuarioStepDefinitions {

	@Before
	public void before(Scenario scenario) throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "Registro"+scenario.getName().split(" ")[4];
	}
	
	@Cuando("^realice la solicitud para registrar un usuario en (.*)$")
	public void realiceLaSolicitudParaRegistrarUnUsuarioEnApiRegister(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));	    
	}
}
```

### Features

#### ActualizarParcheUsuario.feature

Contiene el escenario de Actualizar parche de usuario y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Actualizacion de parche de usuario

	Esquema del escenario: Actualizar parche de usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para actualizar el parche de un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|200|
```

#### ActualizarUsuario.feature

Contiene el escenario de Cerrar Sesion y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Actualizacion de usuario

	Esquema del escenario: Actualizar usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para actualizar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|200|
```

#### CrearUsuario.feature

Contiene el escenario de Crear Usuario y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Creacion de usuarios

	Esquema del escenario: Crear usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para crear un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users|201|
```

#### EliminarUsuario.feature

Contiene el escenario de Eliminar Usuario y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Eliminacion de usuarios

	Esquema del escenario: Eliminar usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para eliminar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|204|
```

#### ListaRecursos.feature

Contiene el escenario de Lista de Recursos y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Obtener lista de recursos

	Esquema del escenario: Obtener lista de recursos
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para obtener la lista de recursos en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/unknown|200|
```

#### ListaUsuarios.feature

Contiene el escenario de Lista de Usuarios y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Obtener lista de usuarios

	Esquema del escenario: Obtener lista de usuario de la pagina dos
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para obtener la lista de usuarios en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users?page=2|200|
```

#### LoginUsuario.feature

Contiene los escenarios exitosos y alternos de Login de Usuario y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Login de usuarios

	Esquema del escenario: <Escenario>
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para loguear un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Escenario|Url|EndPoint|Estado|
		|Login de usuario - Successful|https://reqres.in/|api/login|200|
		|Login de usuario - Unsuccessful|https://reqres.in/|api/login|400|
```

#### RegistroUsuario.feature

Contiene los escenarios exitosos y alternos de Registro de Usuario y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Registro de usuarios

	Esquema del escenario: <Escenario>
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para registrar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Escenario|Url|EndPoint|Estado|
		|Registro de usuario - Successful|https://reqres.in/|api/register|200|
		|Registro de usuario - Unsuccessful|https://reqres.in/|api/register|400|
```

## Ejecucion

Al momento de ejecutar el proyecto y obtener el reporte debemos ubicarnos en la carpeta del proyecto y abrir el `CMD` para ejecutar el siguiente comando

```yml
    gradle clean test aggregate
```

Este comando ejecutara todos los escenarios implementados en el proyecto

```cmd
    10 actionable tasks: 10 executed
```

Al finalizar debemos ingresar y abrir el archivo `index.html` que se encuentra en la siguiente ruta

```yml
  <ProyectoName>\target\site\serenity\index.html
```
