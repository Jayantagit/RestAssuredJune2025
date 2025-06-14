package ReqResTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RestFulXMLTest {

	public static void main(String[] args) throws IOException {
		
		String payLoad=new String(Files.readAllBytes(Paths.get("./src/test/resources/payload/restfulbooker.xml")));
		String xmlPayload=payLoad.replace("{{totalPrice}}", "245");
		
		File resultFile=new File("./src/test/resources/payload/restfulbooker2.xml");
		/*
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resultFile, updatedpayLoad);*/
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Response res=RestAssured.given().log().all().header("Content-Type", "text/xml")
				.header("Accept","text/xml")
		.body(xmlPayload)
		.when().log().all().post("/booking");
		System.out.println(res.getBody().asPrettyString());
		XmlPath xml=new XmlPath(res.getBody().asString());
	

	}

}
