package org.restapi.restapitesting;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class APITesting {
	//@Test
	public void get() {
		Response res = 
		 given()
		 .param("page", "1")
		.when()
		//.get("https://reqres.in/api/users?page=1");
		.get("https://reqres.in/api/users");
		//.then()
		//.statusCode(200)
		//.body("result", not(emptyArray()));
		//.body("data.first_name", hasItems("George","Janet","Mohan"))
		//.body("data.first_name[1]", equalTo("Janet"));
		//.log().all();
		
		JsonPath js = new JsonPath(res.asString());
		System.out.println("JSon data size == "+js.getInt("data.size()"));
		for(int i=0; i<js.getInt("data.size()"); i++ ) {
			System.out.println("First Name: "+ js.getString("data["+ i + "].first_name"));
			System.out.println("Last Name: "+ js.getString("data["+ i + "].last_name"));
			System.out.println("====================");
		}
	}
	
	//@Test
	public void post() {
		JSONObject request = new JSONObject();
		request.put("Name", "Mohan");
		request.put("job", "Manager");
		
		given()
		.header("Content-Type","application/json")
		.body(request.toJSONString())
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.statusCode(201)
		.log()
		.all();
		
	}
	
	//@Test
	public void put() {
		JSONObject request = new JSONObject();
		request.put("name", "Mohan2");
		request.put("job", "Manager");
		
		given()
		.header("Content-Type","application/json")
		.body(request.toJSONString())
		.when()
		.put("https://reqres.in/api/users/2")
		.then()
		.statusCode(200)
		.log()
		.all();
		
	}

	//@Test
	public void patch() {
		JSONObject request = new JSONObject();
		request.put("name", "Mohan3");
		request.put("job", "Manager");
		
		given()
		.header("Content-Type","application/json")
		.body(request.toJSONString())
		.when()
		.patch("https://reqres.in/api/users/2")
		.then()
		.statusCode(200)
		.log()
		.all();
		
	}
	
	@Test
	public void delete() {
		given()
		//.header("Content-Type","application/json")
		//.body(request.toJSONString())
		.when()
		.delete("https://reqres.in/api/users/2")
		.then()
		.statusCode(204)
		.log()
		.all();
		
	}
}
