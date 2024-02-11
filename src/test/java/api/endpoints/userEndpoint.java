package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoint {
	
	public static Response createUser(User payload){
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.createurl);
		
		return response;
	}
	public static Response getUser(String userName){
		Response response = given().pathParam("username", userName)
		

		
		.when()
		.get(Routes.geturl);
		
		return response;
	}
	
	public static Response updateUser(String userName,User payload){
		Response response = given().pathParam("username", userName)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.put(Routes.updateurl);
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		Response response = given().pathParam("username", userName)
		
		.when()
		.delete(Routes.deleteurl);
		
		return response;
	}
}
