Documentación 
==============

Sige escueta.

Git & maven 
============

Git: todo ok

Maven: todo ok


Diseño: 
========

- Filter esta sumando responsabilidades. Muchos: shouldShowMessage distintos.
- El xml de configuración esta raro, uno esperaría ver loggers como primer nivel, ej:
<logger>
	<name>Logger1</name>
	<level>INFO</level>
	<format>json</format>
	<output>file:out1.log</output>
	<output>console</output>
</logger>

<logger>
	<name>Logger2</name>
	<level>DEBUG</level>
	<output>console</output>
</logger>
- Por que XmlProperties? Por que implementa IProperties? Idem DefaultProperties.
- LoggerWithName extends LoggerDecorator, me parece que es mas claro adaptar el diseño anterior que ahcer un decorator en este caso. Es agregar una var de instancia al Logger. Mete ruido al diseño general.
- Falto Custom Filter y Custom Handler?
- Por que resolvieron el formato nuevo como un Handler? y si quiero sacar el json por consola? o loggear un json en una BD o a un lugar remoto? No seria un nuevo Formatter en su esquema original?
- Estaría bueno tener un ejemplo de uso de la herramienta usando el binding de SLF4J, can varios loggers, distintos formatos y distintos handlers.

Código: 
========

- Naming: IProperties

- Código Duplicado:
public void log(Date date, String message, LogLevel level) {
	LogMessage logMessage = filter(date, message, level, "");
	if (logMessage != null) {
		write(logMessage);
	}
}

public void log(Date date, String message, LogLevel level, String loggerName) {
	LogMessage logMessage = filter(date, message, level, loggerName);
	if (logMessage != null) {
		write(logMessage);
	}
}

public void log(Date date, String message, LogLevel level, Throwable e) {
	LogMessage logMessage = filter(date, message, level, "");		
	if (logMessage != null) {
		logMessage.addException(e);
		write(logMessage);
	}
}

public void log(Date date, String message, LogLevel level, Throwable e, String loggerName) {
	LogMessage logMessage = filter(date, message, level, loggerName);		
	if (logMessage != null) {
		logMessage.addException(e);
		write(logMessage);
	}
}

- Diamond operator: 
loggerMap = new HashMap<String, LoggerAdapter>() -> loggerMap = new HashMap<>()

- String comparation:
key == "LogLevel"
- equals -> va a verificar que sean objetos iguales que representan la misma cosa (esto lo puede redefinir un programador), en el caso de strings que tengan el mismo contenido.
- "==" verifica que se trate de exactamente el mismo objeto.
Entonces:
"aaaa" == "aaaa" -> Es verdadero por que la jvm crea un solo "aaaa"
"aaaa".equals(new String("aaaa")); -> Es verdadero por que tienen el mismo contenido (por mas que sean dos objetos distintos).
"aaaa" == new String("aaaa"); -> Es falso, son dos objetos distintos.

- If tipos switchs: (en java 7 pueden switchear por un String)
if (callingMethod.equals("debug")) {
	logLevel = LogLevel.DEBUG;
} else if (callingMethod.equals("trace")) {
	logLevel = LogLevel.TRACE;
} else if (callingMethod.equals("error")) {
	logLevel = LogLevel.ERROR;
} else if (callingMethod.equals("info")) {
	logLevel = LogLevel.INFO;
} else if (callingMethod.equals("warn")) {
	logLevel = LogLevel.WARN;
}
por:
switch (callingMethod) {
case "debug":
    logLevel = LogLevel.DEBUG;
    break;
case "trace":
    logLevel = LogLevel.TRACE;
    break;
case "error":
    logLevel = LogLevel.ERROR;
    break;
case "info":
    logLevel = LogLevel.INFO;
    break;
case "warn":
    logLevel = LogLevel.WARN;
    break;
}

- Exceptions no manejadas:
@Override
public void write(LogMessage message) {
	try {
		PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
		logFile.println(message.getPlainMessage());
		logFile.close();
	} catch (IOException e) {
	}
}
O la manejan ahí, o la relanzan (como RuntimeException), sino pueden generar bugs complicados de buscar ya que va a fallar muy lejos de ahí.

- Cosas que no usan:
Parámetro format en ar.fiuba.tecnicas.logging.formatter.LogMessage#setupHashMessage
ar.fiuba.tecnicas.logging.Logger#addCustomFilter
org.slf4j.impl.LoggerAdapter#LoggerAdapter(java.lang.String)

- Simplificación de código:
private LogMessage filter(Date date, String message, LogLevel level, String loggerName) {
	LogMessage logMessage = this.logFormatter.format(date, message, level, loggerName);
	LogMessage filteredMessage = this.filter.filter(logMessage, level,this.filterRegex, this.customFilter);
	
	return filteredMessage;
}
por:
private LogMessage filter(Date date, String message, LogLevel level, String loggerName) {
	LogMessage logMessage = logFormatter.format(date, message, level, loggerName);
    return filter.filter(logMessage, level, filterRegex, customFilter);
}


Tests: 
=======

- Buena cobertura.
- Hay un test roto: ar.fiuba.tecnicas.test.LoggerWithNameTest#log
- Estilo: 
@Test
public void addNewLoggerShouldReturnTrue() {	
	boolean expected = true;				
	boolean actual = this.loggerFactory.addLogger(new LoggerWithName(new Logger(), "aNewLogger"));
	
	assertEquals(expected, actual);
}

por

@Test
public void addNewLoggerShouldReturnTrue() {
    assertTrue(this.loggerFactory.addLogger(new LoggerWithName(new Logger(), "aNewLogger")));
}

- LoggerConfigTest, pueden hacer uso del setup/teardown para borrar archivos.

- Exception + fail:
@Test
public void emptyValueReturnedIfConfigFileNotFound() {

	try {
		this.loader = new JavaProperties("");
		
		String actualSeparator = loader.getValue("Separator");
		
		String expectedSeparator = "";
		assertEquals(expectedSeparator, actualSeparator);
	} catch (Exception e) {
		fail();
	}
}

No hace falta el try-catch si hay exception, el test fallara solito.
cambiar por:

@Test
public void emptyValueReturnedIfConfigFileNotFound() {
    this.loader = new JavaProperties("");

    String actualSeparator = loader.getValue("Separator");

    String expectedSeparator = "";
    assertEquals(expectedSeparator, actualSeparator);
}

- assertFalse(logMessage.equals(logger)); por assertNotEquals(logger, logMessage);
