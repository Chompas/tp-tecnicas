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

- El manejo de las múltiples instancias de Loggers con distintas configuraciones la manejamos con una clase Singleton, LoggerManager. 

- La decisión sobre qué mensajes se loggean y qué mensajes no la delegamos a una clase `Filter`, para separar incumbencias.

- La configuración del `Logger` se decide en la clase `LoggerConfig`, cuya responsabilidad es leer de un archivo de configuración los parámetros necesarios.

- Hicimos una interfaz `ILogFormatter`, para permitir que el programador modifique la forma en que quiere que sus mensajes se escriban.

- Hicimos una interfaz `IHandler`, para permitir que el programador agregue formas de emitir logs.

- Hicimos una interfaz `IProperties` para permitir que el programador agregue formas de configurar el logger.

- Utilizamos el patrón de diseño **Facade** en la clase `FormatterHelper` para implementar la obtención del nombre del método, nombre de archivo y número de línea de el código que que llama a `log()`. Esto se hizo así para ocultar los detalles de implementación, que son de muy bajo nivel.

- Utilizamos el patrón de diseño **Factory** para decidir en tiempo de ejecución el tipo de salida que se desea utilizar.


## TP Reloaded ##

### Impacto en el diseño ###

- Throwable: nuevo método log en la clase `Logger` que recibe el `Throwable` y lo agrega al mensaje

- Filtro Regex: permitir agregar un filtro al `Logger` a través de un nuevo método, y modificación de la clase `Filter` donde evalua esa expresión regular

- Nuevo nivel TRACE: el impacto fue mínimo, sólo se agregó un valor en `LogLevel`.

- Inicialización automática del Logger: Se modificó la clase `LoggerConfig` para que busque automáticamente el archivo de configuración

- Leer configuración por XML: nueva clase `XmlProperties` que implementa la interfaz `IProperties` permitiendo la configuración por XML

- Multiples Loggers: Se utilizó el patrón de diseño **Decorator** para agregar el nombre a un `Logger` sin modificar esta clase

- Formato de salida JSON: Nueva clase `LogMessage` la cual maneja el mensaje de salida, pudiendo ser texto plano o en formato json. Modificaciones en las clase `Logger` para utilizar esta nueva clase.

- Filter custom: nueva clase `CustomFilter` la cual define los atributos posibles para el filtrado y adaptación de la clase `Logger` para su uso

- Binding SL4J: nuevo paquete y clases `LoggerFactory` y `LoggerAdapter` para redefinir los métodos de SL4J

- Nueva variable %g: fuerte impacto en el diseño. Se tuvo que agregar nuevos métodos de `log()` en `ILogger` recibiendo el nombre para poder implementarla. Esto produjo la incorporación de los mismos en la clase `Logger`

- Destinos custom: no implementada
