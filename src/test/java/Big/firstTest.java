package Big;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import io.restassured.RestAssured;
import  io.restassured.matcher.*;
import io.restassured.response.Response;

import org.apache.commons.collections4.map.HashedMap;
import  org.hamcrest.Matchers.*;



public class firstTest 
{	
	Response res;
	
	//@Test
	public void getAllUsers() 
	{
		 //RestAssured.baseURI = "https://reqres.in";
		
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.log()	
		.all();
	}
	
	@Test(priority=1)
	public void createUser() 
	{
		// RestAssured.baseURI = "https://reqres.in";
		
		HashMap hm = new HashMap();
		hm.put("name", "Rupesh");
		hm.put("job", "Engineer");
		
		res=given()
		.contentType("application/json")
		.body(hm)
		.when()
		.post("https://reqres.in/api/users");
		
		
		
		//.statusCode(201)
		//.log()	
		//.all();
	}
	
	//@Test(priority=2,dependsOnMethods= {"createUser"})
	public void checkIfUserCreated() 
	{
		// RestAssured.baseURI = "https://reqres.in";
		
		System.out.println(res.asPrettyString());
		
		int id = res.jsonPath().getInt("id");
		
		String ep = "https://reqres.in/api/users/"+id+"";
		System.out.println(ep);
		
		given()
		.when()
		.get(ep)
		.then()
		.log().all()
		.statusCode(200)
		.body("data.first_name",equalTo("Rupesh"));
		
	}
	
	@Test(priority=2,dependsOnMethods= {"createUser"})
	public void updateUserCreated() 
	{
		// RestAssured.baseURI = "https://reqres.in";
		
		HashMap hb = new HashMap();
		hb.put("name", "Kadam");
		hb.put("job", "Engineer");
		
		System.out.println(res.asPrettyString());
		
		int id = res.jsonPath().getInt("id");
		
		String ep = "https://reqres.in/api/users/"+id+"";
		System.out.println(ep);
		
		given()
		.contentType("application/json")
		.body(hb)
		.when()
		.put(ep)
		.then()
		.log().all()
		.statusCode(200);
		
	}
	
	//@Test(priority=3, dependsOnMethods= {"createUser"})
		public void getSingleUser() 
		{
			// RestAssured.baseURI = "https://reqres.in";
			
			int id = res.jsonPath().getInt("id");
			
			String ep = "https://reqres.in/api/unknown/"+id;
			System.out.println(ep);
			
			given()
			.when()
			.get(ep)
			.then()
			.log().all()
			.statusCode(200);
			
		}
		
		@Test(priority=4, dependsOnMethods= {"createUser"})
				public void deleteUserCreated() 
				{
					// RestAssured.baseURI = "https://reqres.in";
					
					int id = res.jsonPath().getInt("id");
					
					String ep = "https://reqres.in/api/users/"+id;
					System.out.println(ep);
					
					given()
					.when()
					.delete(ep)
					.then()
					.log().all()
					.statusCode(204);
					
				}


}
