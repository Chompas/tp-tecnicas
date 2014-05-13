
import java.io.FileInputStream;
import java.util.Properties;

public class Main {

	public static int main(String[] args)
		throws Exception
	{
		Properties properties = new Properties();
		properties.loadFromXML(new FileInputStream("config.xml"));
		
		String logLevel = properties.getProperty("LogLevel");
		String separator = properties.getProperty("Separator");

		System.out.println(logLevel);
		System.out.println(separator);
		
		return 0;
	}
}
