Documentación 
===================

Solo un diagrama. Muy Escueto, falto alguna narrativa que explique como juega todo, 
que decisiones de diseño tomaron, que diseños alternativos pensaron, ejemplos de uso, etc.

Git & maven 
===================

Git: todo ok

Maven: todo ok


Diseño: 
===============

* En lineas generales me pareció muy adecuado.

* No se pidió el config por xml, sino por archivo de properties.

* Existe un acoplamiento fuerte entre LogerConfig y XmlProperties, cuando tal vez no debería existir.
Este método parece que permite configurar el Logger a partir de un file de configuración. 
Este método no queda clausurado ante cambios, nuevos formatos de configuración.

* Parece haber un error de Requerimiento Funcional.

public String filter(String message, LogLevel level)
	{
		if (shouldShowMessage(level))
		{
			return message;
		}
		else
		{
			return "";
		}
	}

Este método parece querer validar el Nivel de Log del mensaje con el configurado, para ver si debe ser ignorado o no.
Pero el mismo lo que hace es setear el mensaje en “” si no debe ser logueado, 
el log se sigue haciendo en el handler configurado, solo que como mensaje se loguea “”. Aquí se debe ver de que no se loguee en el handler.
 
Ver si es necesario delegar la responsabilidad de filtrar mensajes a la clase Filter, 
o lo puede manejar la misma clase Logger, incluso debería hacerse primero que todo y retornar si no es necesario loguearse.



Código: 
===============

* Formateo: Utilicemos siempre el mismo formateo en todos lados, y en lo posible si existe algún tipo
 de convención ya establecida por la comunidad.
En el caso de java: esta muy establecido este tipo de llaves http://google-styleguide.googlecode.com/svn/trunk/javaguide.html#s4.1-braces

* Naming: IProperties, ILogFormatter: http://staff.science.uva.nl/~fdevries/java/java0506/java0506inl/api.docu/conventions/CodeConventions.doc8.html#367 no se suele usar la "I" en gral se busca que el nombre de la interfaz sea los mas genérico posible, y que el nombre de las implementaciones tenga algun "hint" del como esta implementada, ej: http://www.programcreek.com/2013/03/hashmap-vs-treemap-vs-hashtable-vs-linkedhashmap/

* código autogenerado:
// TODO Auto-generated catch block

* Están ocultado exceptions:
try {
	xmlConfig = new FileInputStream(configFile);
	this.properties = new XmlProperties(xmlConfig);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
o
try {
	this.properties.loadFromXML(xmlFile);
} catch (InvalidPropertiesFormatException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (NullPointerException e) {

}

Si pasa esa exception el programa sigue, y seguramente fallará en un lugar menos apropiado 
para entender que ha ocurrido. O la manejamos ahí, o relanzamos la exception.

* Diamond Operator: http://docs.oracle.com/javase/7/docs/technotes/guides/language/type-inference-generic-instance-creation.html
this.outputs = new ArrayList<IHandler>();
por: (en java7)
this.outputs = new ArrayList<>();

* () de mas
return (level.getCode() <= globalLogLevel.getCode());
por:
return level.getCode() <= globalLogLevel.getCode();

* Código que nadie llama:
Logger -> log (debería la menos ser llamado por un test)
Logger -> addHandler



Tests: 
==============

* En general, tienen buena estructura.

* Falto un poco de cobertura:
Ej:
LoggerConfig, Logger, ConsoleHandler, FileHandler

* Si esta el @Test no hace falta que los test methods arranquen con la palabra testXXXX
Ej:
@Test
public void testEmptyValueReturnedIfConfigFileNotFound()
pasa a:
@Test
public void emptyValueReturnedIfConfigFileNotFound()

* Mocking/Stubbing:
@Test
public void testGetGlobalLogLevel()
{		
	// arrange
	XmlProperties mockedProperties = mock(XmlProperties.class);
	when(mockedProperties.getValue("LogLevel")).thenReturn("INFO");
	
	config = new  (mockedProperties);
	
	// act
	config.getGlobalLogLevel();
	
	// assert
	Mockito.verify(mockedProperties).getValue("LogLevel");
	assertEquals(LogLevel.INFO, config.getGlobalLogLevel());		
}
En esta linea: when(mockedProperties.getValue("LogLevel")).thenReturn("INFO");
Se lee que se esta creando un stub sobre el metodo getValue, con el parametro "LogLevel" para que siempre devuelva "INFO".
Luego se verifica que cuando se envie el mensaje getGlobalLogLevel a config retorne LogLevel.INFO.
Ahora bien, porque se esta verificando tambien que se mando el mensaje getValue("LogLevel") al stub?
Para probar que el config saque el LogLevel configurado de la configuracion, necesito un XMLProperties? no podría pasarle mejor a alguien que cumpla con la interfaz IProperties?
Básicamente este test esta metiendo un poco de ruido, uno pensaría que LoggerConfig requiere si o si de un XmlProperties, cuando en realidad no es cierto, y no hace falta para testear la funcionalidad que están testeando en este test.

* LoggerTest no tiene tests.

* No hace falta poner en todos los tests los comentarios de : // arrange, act, assert, con seprarar las cosas con un linea en blanco alcanza.

* Faltaron probar casos de exception.
