package org.restapi.restapitesting;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

import org.testng.annotations.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.json.JSONObject;
import org.json.JSONArray;
public class JSONPractice {
	JSONObject req = new JSONObject();
//	req.put("first_name", "RON");
//	req.put("last_name", "TED");
	Response res;
	//@Test(priority = 1)
	public void get() {
		res = given()
				.header("Content-type", "application/json")
				//.param("page", 2)
				.when()
				.get("https://reqres.in/api/users")
				.then()
				.statusCode(200)
				.body("data[0].id", equalTo(1))
				.log()
				.all()
				.extract().response();
		ResponseBody rb = res.getBody();
		String status = rb.asString();
		Assert.assertTrue(status.contains("1"));//,true,"Charles");
		JsonPath jp = res.jsonPath();
		JSONObject jo = new JSONObject(res.asString());
		//System.out.println("JSON ARRAY SIZE===="+jp.getInt("data.size()"));
		//System.out.println("JSONOBJECT VALUES######"+jo.get("data"));
		Iterator keys = jo.keys();
//		while(keys.hasNext()) {
//			
//			//System.out.println(jp.getString("data["+i+"]"));
//			System.out.println(keys.next());
//			String key = (String)keys.next();
//			if(jo.get(key) instanceof JSONArray) {
//				JSONArray ja = jo.getJSONArray((String)keys.next());
//				System.out.println("Nested array size"+ja.length());
//			}else if(jo.get(key) instanceof JSONObject) {
//				JSONObject tjo = jo.getJSONObject(key);
//				Iterator itr1 = tjo.keys();
//				while(itr1.hasNext()) {
//					System.out.println("It's JSONOBJECT####"+itr1.next());
//				}
//				
//			}
//		}
	}
	
	@Test
	public void post() {
//		Response res = given()
//				.header("Content-type","application/json")
				//.body(data.toJSONString())
	}
}
