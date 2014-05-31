# TP 2 #


**IMPORTANTE**: escribir acá cómo impactó en el código cada nueva funcionalidad!

----------


- Multiples logggers
	
    logger = xx.getLogger("nombre-del-modulo")

ó

    logger = xx.getLogger(MiClase.class)


- nuevo nivel TRACE
    
    TRACE > DEBUG > INFO ....

(no impactó en nada, solo fue agregar un valor a un enum)

- nuevo formato de salida JSON

    {"fecha": "29-05-2014"; "level": "INFO"; "message : "mensaje" }

- nuevo formato de salida XML

- %g es el nombre del logger

- binding a SL4J (simple logging  facade for java)

- api += throwables

_**Javier**: Agregué un método en la clase `Logger` que recibe un `Throwable`, y este le appendea al mensaje ya formateado el mensaje de la excepción_

- inicializacion automatica del logger (ya está implementado asi en el constructor de `LoggerConfig`)

- filtrado regex

por ejemplo, filtrar los logs que tengan una palabra en particular

_**Javier**: Agregué la regex como atributo del `Logger`, modificando el método de filter, el cual evalua la expresión regular_

- filtros custom (?)

por ejemplo, no loggear mensajes en horas pico (???)

- destinos custom
