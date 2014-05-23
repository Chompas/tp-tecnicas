package ar.fiuba.tecnicas.logging.config;

public class DefaultProperties implements IProperties {

	@Override
	public String getValue(String key) {
		if (key == "LogLevel") {
			return "DEBUG";
		} else if (key == "Separator") {
			return "-";
		} else if (key == "Outputs") {
			return "Console";
		} else if (key == "Format") {
			return "%p %n %m";
		} else {
			return "";
		}
	}

}
