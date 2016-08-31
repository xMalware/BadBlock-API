package fr.badblock.gameapi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import lombok.Getter;

public class ServerProperties {
	@Getter
	private final static Properties properties;
	private final static File		propertiesFile = new File("server.properties");
	static {
		properties = new Properties();

		try {
			properties.load(new FileInputStream(propertiesFile));
			/*Files.readAllLines(Paths.get(propertiesFile.getAbsolutePath()), Charset.defaultCharset()).forEach(line -> {
				String[] splitted     = line.split("=");
				String   propertyName = splitted[0];
				String   property     = splitted.length == 1 ? "" : StringUtils.join(splitted, "=", 1);
				
				properties.setProperty(propertyName, property);
			})*/;
		} catch (IOException e) {
			System.out.println("Can not read server.properties file :");
			e.printStackTrace();
		}


	}

	public static void saveProperties(){
		try {
			properties.store(new FileOutputStream(propertiesFile), "");
		} catch (IOException e) {
			System.out.println("Can not save server.properties file :");
			e.printStackTrace();
		}
	}
}
