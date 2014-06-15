package ar.fiuba.tecnicas.logging.config;

public class DefaultProperties implements IProperties {

	@Override
	public String getValue(String key) {
		switch (key) {
			case "LogLevel":
				return "DEBUG";
			case "Separator":
				return "-";
			case "Outputs":
				return "Console";
			case "Format":
				return "[%p] %n %m";
			default:
				return "";
		}
	}

}
