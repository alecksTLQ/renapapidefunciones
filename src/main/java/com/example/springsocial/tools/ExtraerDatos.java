package com.example.springsocial.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExtraerDatos {
	
	private String clave;
	
	public String leerArchivo(String key) {
		
		Properties propiedades = new Properties();
		InputStream entrada = null;
		
		try {
			
			entrada = new FileInputStream("datos.properties");
			propiedades.load(entrada);
			
			clave = propiedades.getProperty(key);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return clave;
	}
}
