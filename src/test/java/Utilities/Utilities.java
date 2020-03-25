package Utilities;

import io.restassured.path.json.JsonPath;

public class Utilities {
	public static JsonPath rawJson(String res) {
		/*
		 * String response=res.toString();
		 *  System.out.println(response);
		 */
		JsonPath json=new JsonPath(res);
		return json;
	}
 
	
}
