package com.mx.unodostres.certificacion.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Metodos {
	
	public static String jsonBodyRequestPath;
	
	public static JSONObject leerArchivoJson() {
		Object ob = null;
		try {
			ob = new JSONParser().parse(new FileReader("src/test/resources/dataJson/"+jsonBodyRequestPath+".json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jsonRequerimiento = (JSONObject) ob;
		
		return jsonRequerimiento;
	}
}
