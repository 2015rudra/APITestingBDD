package Small;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import io.restassured.RestAssured;
import  io.restassured.matcher.*;
import io.restassured.response.Response;

import org.apache.commons.collections4.map.HashedMap;
import  org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
body can be create in multiple ways

1. Using HashMap (HashMap hm = new Hashmap(); hm.put("name","Rupesh"))

2. org.json library

3. using POJO Class (Plain Old Java Object)

4. using External JSON File */

public class secondDay 
{


	@Test(priority=1)
	public void postRequestUsingHashMap() 
	{
		HashMap data = new HashMap();

		data.put("name","Sonali");
		data.put("location", "Mumbai");
		data.put("phone", "9657493980");
		String cArr[]= {"Java","Python"};

		data.put("courses", cArr);


		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("name",equalTo("Sonali"))
		.body("location",equalTo("Mumbai"))
		.body("phone",equalTo("9657493980"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Python"))
		.header("Content-Type", "application/json")
		.log().all();


	}

	//@Test(priority=2, dependsOnMethods= {"postRequestUsingHashMap"})
	public void deleteStudent() 
	{
		given()
		.when()
		.delete("http://localhost:3000/students/c773")
		.then()
		.statusCode(200);
	}

	@Test(priority=2)
	public void postRequestUsingJSONORGLibrary() 
	{
		JSONObject data = new JSONObject();

		data.put("name","Rakesh");
		data.put("location", "Mumbai");
		data.put("phone", "9657493980");
		String cArr[]= {"Java","Python"};

		data.put("courses", cArr);


		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("name",equalTo("Rakesh"))
		.body("location",equalTo("Mumbai"))
		.body("phone",equalTo("9657493980"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Python"))
		.header("Content-Type", "application/json")
		.log().all();


	}
	
	
	@Test(priority=3)
	public void postRequestUsingPOJO() 
	{
		
		requestPayload rp = new requestPayload();
		rp.setName("Yogesh");
		rp.setLocation("Chunnabhatti");
		rp.setPhone("9604507843");
		String cArr[]= {"Java","Python"};

		rp.setCourses(cArr);


		given()
		.contentType("application/json")
		.body(rp)
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("name",equalTo("Yogesh"))
		.body("location",equalTo("Chunnabhatti"))
		.body("phone",equalTo("9604507843"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Python"))
		.header("Content-Type", "application/json")
		.log().all();


	}

	
	
	@Test(priority=4)
	public void postRequestUsingExternalFile() throws FileNotFoundException 
	{
		
		File f = new File(".\\userpayload.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);


		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("name",equalTo("Sakshi"))
		.body("location",equalTo("Dahisar"))
		.body("phone",equalTo("9604507844"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Python"))
		.header("Content-Type", "application/json")
		.log().all();


	}






}
