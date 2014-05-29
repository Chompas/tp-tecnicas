# Documentación #

## API ##

La clase principal es `Logger`, que tiene un método principal `log()`. Este método recibe un `string`, que es el mensaje a loggear, y `LogLevel`, un nivel de log asociado a ese mensaje.

## Decisiones de Diseño ##

- La decisión sobre qué mensajes se loggean y qué mensajes no la delegamos a una clase `Filter`, para separar incumbencias.

- La configuración del `Logger` se decide en la clase `LoggerConfig`, cuya responsabilidad es leer de un archivo de configuración los parámetros necesarios.

- Hicimos una interfaz `ILogFormatter`, para permitir que el programador modifique la forma en que quiere que sus mensajes se escriban.

- Hicimos una interfaz `IHandler`, para permitir que el programador agregue formas de emitir logs.

- Hicimos una interfaz `IProperties` para permitir que el programador agregue formas de configurar el logger.

- Utilizamos el patrón de diseño **Facade** en la clase `FormatterHelper` para implementar la obtención del nombre del método, nombre de archivo y número de línea de el código que que llama a `log()`. Esto se hizo así para ocultar los detalles de implementación, que son de muy bajo nivel.

- Utilizamos el patrón de diseño **Factory** para decidir en tiempo de ejecución el tipo de salida que se desea utilizar.




