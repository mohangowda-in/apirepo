package org.restapi.restapitesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.*;
import org.testng.asserts.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class APITestingDemo {
	@Test(priority = 1)
	public void get() {
		Response res = 
				given()
				.header("Content-type","application/json")
				.param("page", "2")
				.when()
				.get("https://reqres.in/api/users")
				.then()
				.statusCode(200)
				.body("data.first_name", hasItems("Michael","Lindsay","Rachel"))
				.log().all()
				.extract().response();
		JsonPath jp = res.jsonPath();
		
		JSONObject jb = new JSONObject(res.asString());
		getKey(jb, "last_name");
		
				
//		JsonPath jp = new JsonPath(res.asString());
//		
//		System.out.println("Json data path =="+jp.getInt("data.size()"));
//		for(int i=0; i<6;i++) {
//			System.out.print(jp.getString("data["+ i +"].first_name"));
//			System.out.println(" "+jp.getString("data["+i+"].last_name"));
//		}
	}
	
	
	public void getKey(JSONObject job, String key) {
		boolean exist = job.has(key);
		Iterator itr;
		String nextKey;
		if(!exist) {
			itr = job.keys();
			while(itr.hasNext()) {
				nextKey = (String)itr.next();
				if(job.get(nextKey) instanceof JSONArray) {
					JSONArray jarray = job.getJSONArray(nextKey);
					System.out.println("data array length====="+ jarray.length());
					for(int i=0; i<jarray.length(); i++) {
						System.out.println(jarray.get(i));
						if(jarray.get(i) instanceof JSONObject) {
							JSONObject jo = jarray.getJSONObject(i);
							getKey(jo, key);
						}
					}
				}
			}
		}else {
			parseObject(job,key);
		}
	}
	
	public void parseObject(JSONObject job, String key) {
		System.out.println("Key value####"+ job.get(key));
	}
    //@Test(priority = 2)
	public void post() {
//		JSONObject req = new JSONObject();
//		req.put("name", "Ron");
//		req.put("job", "Manager");
//		
//		System.out.println("JSON Format####"+req.toJSONString());
//		
//		given()
//		.header("Content-type","application/json")
//		.body(req.toJSONString())
//		.when()
//		.post("https://reqres.in/api/users")
//		.then()
//		.statusCode(201)
//		.log().all();
	}
	
	//@Test(priority = 3)
	public void put() {
//		JSONObject req = new JSONObject();
//		req.put("name", "Ronn");
//		req.put("job", "Manager1");
//		Response res =
//				given()
//				.header("Content-type","application/json")
//				.body(req.toJSONString())
//				.when()
//				.put("https://reqres.in/api/users/633")
//				.then()
//				.statusCode(200)
//				.log().all()
//				.extract().response();
	}
	
	//@Test(priority = 4)
	public void patch() {
//		JSONObject req = new JSONObject();
//		req.put("name", "Ronn1");
//		req.put("job", "Manager1");
//		Response res = 
//				given()
//				.header("Content-type","application/json")
//				.body(req.toJSONString())
//				.when()
//				.patch("https://reqres.in/api/users/633")
//				.then()
//				.statusCode(200)
//				.log().all()
//				.extract().response();
	}
	
	//@Test(priority = 5)
	public void delete() {
		Response res =
				given()
				.when()
				.delete("https://reqres.in/api/users/633")
				.then()
				.statusCode(204)
				.log().all()
				.extract().response();
        
	}

}
