package ReqResTest;
import java.util.List;
import java.util.Map;

import io.restassured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCall {

	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		Response response=RestAssured.given().when().get("/api/users?page=2");
		//System.out.println(response.getBody().asPrettyString());
		System.out.println(response.statusCode());
		System.out.println("=============");
		JsonPath jsonPath=response.jsonPath();
		List<Map<String,Object>> responseList=jsonPath.getList("data");
		for(Map<String,Object> userData:responseList)
		{
			System.out.println(userData.get("id"));
		}
		
		

	}

}
