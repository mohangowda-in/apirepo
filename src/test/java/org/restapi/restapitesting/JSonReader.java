package org.restapi.restapitesting;


import org.testng.annotations.Test;
import io.restassured.path.json.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;


public class JSonReader {
	
	@Test
	public void readJson() {
		JSONObject jb = new JSONObject();
		jb.put("name", "Cozuui");
		jb.put("job", "compani34");
		//Response res = 
				given()
		.header("ContentType","application/json")
		//.body(jb)
		.when()
		//.get("https://reqres.in/api/users")
//		.post("https://reqres.in/api/users")
		//.put("https://reqres.in/api/users/2")
		//.patch("https://reqres.in/api/users/2")
		.delete("https://reqres.in/api/users/2")
		.then()
		//.body("data.last_name[0]", hasItems("Bluth"))
		.statusCode(204)
		.log()
		.all();
//		System.out.println(res.asString());
//		if(res != null) {
//		JsonPath path = new JsonPath(res.asString());
//		System.out.println("jsonpath =="+path.getInt("data.size()"));
//		int s = path.getInt("data.size()");
//		for(int i=0; i<s; i++){
//			String str = path.getString("data[" + i +"].first_name");
//			if(str.equals("Emma")) {
//				System.out.println(path.getString("data[" + i +"]"));
//				System.out.println("Value of str = "+str);
//			}
//		}
		//}
		
		
		
	}

}
