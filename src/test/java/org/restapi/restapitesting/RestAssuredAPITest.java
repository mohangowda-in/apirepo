package org.restapi.restapitesting;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
//import static io.restassured.*;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.matcher.RestAssuredMatchers.*;

 
public class RestAssuredAPITest {
	RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    static String reqbody = "{" +
    "  \"userId\": \"10\",\n" +
    //"  \"id\": \"102\",\n"+
    "  \"title\": \"temporibus sit alias delectus eligendi possimus magni new\" ,\n" +
    "  \"body\": \"new quo deleniti praesentium dicta non quod\\naut est molestias\\nmolestias et officia quis nihil\\nitaque dolorem quia\" \n}";
 //@Test
public void GetBooksDetails() { 
	// Specify the base URL to the RESTful web service 
	//RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
	// Get the RequestSpecification of the request to be sent to the server. 
	RequestSpecification httpRequest = RestAssured.given(); 
	// specify the method type (GET) and the parameters if any. 
	//In this case the request does not take any parameters 
	Response response = httpRequest.request(Method.GET, "https://demoqa.com/BookStore/v1/Books");
	// Print the status and message body of the response received from the server 
	System.out.println("Status received => " + response.getStatusLine()); 
	System.out.println("Response=>" + response.prettyPrint());
	System.out.println("Content type=>" + response.getContentType());
	int statusCode = response.getStatusCode();
	System.out.println("#########################statusCode "+statusCode);
    // Assert that correct status code is returned.
   Assert.assertEquals(statusCode,200, "Correct status code returned");
	
    	
}
 
 //@Test
 public void IteratingHeaders() 
 { RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
  RequestSpecification httpRequest = RestAssured.given(); 
  Response response = httpRequest.get(""); 
  // Get all the headers and then iterate over allHeaders to print each header 
  Headers allHeaders = response.headers(); 
  // Iterate over all the Headers 
  for(Header header : allHeaders) { 
    System.out.println("Key: " + header.getName() + " Value: " + header.getValue()); 
  } 
 } 
 
 //@Test
 public void accountRegistration() {
	 
	 String jsonString = "{\"name\":\"newapitest\",\"salary\":\"4000\",\"age\":\"29\"}";
	 
     RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/create";

     // Create a request specification
     requestSpecification = RestAssured.given();

     // Setting content type to specify format in which request payload will be sent.
     requestSpecification.contentType(ContentType.JSON);

     // Adding body as string
     requestSpecification.body(jsonString);

     // Calling POST method
     response = requestSpecification.post();

     // Let's print response body.
     String responseString = response.prettyPrint();

     /*
      * To perform validation on response, we need to get ValidatableResponse type of
      * response
      */
     validatableResponse = response.then();

     // Check status code
     validatableResponse.statusCode(200);

     // It will check if status line is as expected
     validatableResponse.statusLine("HTTP/1.1 200 OK");

     // Check response body - name attribute
     //validatableResponse.body("data.name", equalTo("newapitest"));

     // Check response body - message attribute
     //validatableResponse.body("message", equals("Successfully! Record has been added."));
     System.out.println("===========Adding new content=========");
     System.out.println("Response body==>\n"+responseString);
 }
 
  
 @Test
 public void getRequest() {
//	 baseURI = "https://jsonplaceholder.typicode.com";
//	 response = given()
//	 .contentType(ContentType.JSON)
//	 .param("userId", "1")
//	 .when()
//	 .get("/posts/101")
//	 .then()
//	 //.body("title[0]", equalTo("sunt autfacere repellat provident occaecati excepturi optio reprehenderit"));
//	 .extract().response();
//	 System.out.println("Body==========>>"+response.asString());
//	 System.out.println("Status code#########"+response.statusCode());
	 
	 JSONObject jo = new JSONObject();
	 jo.put("name", "Cozu");
	 jo.put("job", "company");
	 
	 System.out.println("JSON tree ####" + jo.toJSONString());
	   
	 
 }
 
 //@Test
 public void postRequest() {
	 baseURI = "https://jsonplaceholder.typicode.com";
	 response = given()
	 .header("Content-type","application/json")
	 .and()
	 .body(reqbody)
	 .when()
	 .post("/posts")
	 .then()
	 .extract()
	 .response();
	 System.out.println("ADDING RECORDS########"+response.asString());
	 Assert.assertEquals(201, response.getStatusCode());
	 //Assert.assertEquals("foon", response.jsonPath().getString("title"));
	 //Assert.assertEquals("adding new record", response.jsonPath().getString("body"));
 }
 
 //@Test
 public void deleteRequest() {
	 baseURI = "https://jsonplaceholder.typicode.com";
	 System.out.println("JSON payload \n"+reqbody);
	 response = given()
	 .header("Content-type","application/json")
	 //.and()
	 //.body("reqbody")
	 .when()
	 .delete("/posts/101")
	 .then()
	 .extract()
	 .response();
	 
	 System.out.println("Response########"+response.asString());
	 System.out.println("Status code#######"+response.statusCode());
 }
/* public static void main(String args[]) {
	 new RestAssuredAPITest().GetBooksDetails();
 }*/
}
