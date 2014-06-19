# Documentación #

## Modo de uso ##

Para utilizar el `logger`, en primera instancia se debe cargar su configuración.

    LoggerMananager loggerManager = LoggerManager.getInstance();
    loggerManager.loadConfiguration();

Se permiten utilizar múltiples `loggers` con distintos nombres, y loggear en todos ellos o solo en algunos. Por ejemplo, asumiendo que en la configuración se definió un Logger con nombre `Logger-Module-A`:
   
    ILogger logger = loggerManager.getLogger("Logger-Module-A");
    logger.log(new Date(), "processing...", LogLevel.DEBUG);
	....
	loggerManager.logAll(new Date(), "processing...", LogLevel.DEBUG);

También puede utilizarse un `logger` que tiene una configuración default (formato predefinido y salida únicamente por consola):

	ILogger logger = new LoggerDefault();
	logger.log(new Date(), "processing...", LogLevel.DEBUG);

Por último, se pueden loggear `exceptions`:

	Exception myException = new Exception();
	logger.log(new Date(), "processing...", LogLevel.DEBUG, myException);

## Decisiones de Diseño ##

- El manejo de las múltiples instancias de Loggers con distintas configuraciones la manejamos con una clase **Singleton**, `LoggerManager`. 

- Respecto a la configuración de los Loggers:
	- Si el usuario prefiere utilizar un Logger por default, puede utilizar la clase `LoggerDefault`.
	- Si quiere utilizar distintos Loggers, la clase `LoggerManager` delega la responsabilidad en objetos tipo 
*Parsers* para leer la configuración de un archivo de *Properties* o de un archivo *XML*.

- Hicimos una interfaz `ILogFormatter`, para permitir que el programador cree sus propias formas en que quiere loggear.

- Hicimos una interfaz `IHandler`, para permitir que el programador agregue formas de emitir logs (por consola, por archivo, etc.).

- Utilizamos el patrón de diseño **Facade** en la clase `FormatterHelper` para implementar la obtención del nombre del método, nombre de archivo y número de línea de el código que que llama a `log()`. Esto se hizo así para ocultar los detalles de implementación, que son de muy bajo nivel. 
	- Tuvimos algunos problemas con dicha implementación, ya que se necesita utilizar el stacktrace, y saber en qué nivel del stack se produjo la llamada al log.

-  Para poder decidir en tiempo de ejecución, en base al archivo de configuración, que tipo de IHandler instanciar, utilizamos el patrón de diseño **Factory**. La clase `HandlerFactory` también permite instanciar destinos custom.


## TP Reloaded ##

### Impacto en el diseño ###

- Throwable: nuevo método log en la clase `Logger` que recibe el `Throwable` y lo agrega al mensaje.

- Filtro Regex: permitir agregar un filtro al `Logger` a través de un nuevo método, y modificación de la clase `Filter` donde evalua esa expresión regular.

- Nuevo nivel TRACE: el impacto fue mínimo, sólo se agregó un valor en `LogLevel`.

- Inicialización automática del Logger: Se modificó la clase `LoggerManager` para que busque automáticamente el archivo de configuración mediante sentencias `try {...} catch {...}`.

- Leer configuración por XML: nueva clase `XmlParser` que crea las clases `Logger`s definidas en el archivo XML. Esta clase es utilizada por el `LoggerManager`.

- Multiples loggers: Inicialmente se utilizó el patrón de diseño **Decorator** para agregar el nombre a un `Logger` sin modificar esta clase. Pero luego simplificamos el diseño agregando simplemente un nuevo atributo `name` en la clase `Logger`, y agregando un método a la interfaz `ILogger`: `getName()`.

- Formato de salida JSON: nueva clase `JsonLogFormatter`, que toma un objeto de tipo `LogMessage` y devuelve un `string` con los atributos que dicho `LogMessage` le proporciona.

- Filter custom: nueva clase `CustomFilter` la cual define los atributos posibles para el filtrado y adaptación de la clase `Logger` para su uso

- Binding SL4J: nuevo paquete y clases `LoggerFactory` y `LoggerAdapter` para redefinir los métodos de SL4J

- Nueva variable %g: fuerte impacto en el diseño. Se tuvieron que agregar nuevos métodos de `log()` en `ILogger` recibiendo el nombre para poder implementarla, y agregar un método en la interfaz `ILogger`. Esto produjo la incorporación de los mismos en la clase `Logger`

- Destinos custom: ????
