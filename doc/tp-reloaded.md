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

*__Javier__: Agregué un método en la clase `Logger` que recibe un `Throwable`, y este le appendea al mensaje ya formateado el mensaje de la excepción *

- inicializacion automatica del logger (ya está implementado asi en el constructor de `LoggerConfig`)

- filtrado regex

por ejemplo, filtrar los logs que tengan una palabra en particular

- filtros custom (?)

por ejemplo, no loggear mensajes en horas pico (???)

- destinos custom
