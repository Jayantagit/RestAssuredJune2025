package ReqResTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;

public class PostCall {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/";
		JsonObject jsonObject = readFileFromPathGson("./src/test/resources/payload/Users.json");
		jsonObject.addProperty("job", "QA");
		writeValueGson("./src/test/resources/payload/Users.json", jsonObject);
		
		Map<String, Object> payLoad = readFileFromPathMapper("./src/test/resources/payload/Users.json");
		payLoad.put("job", "TestLead");
		writeValueMapperFile("./src/test/resources/payload/Users.json", payLoad);
		

	}

	public static void writeValueMapper(String path, Map<String, Object> value) {
		ObjectMapper mapper = new ObjectMapper();
		try (FileWriter fr = new FileWriter(new File(path))) {
			mapper.writeValue(fr, value);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void writeValueMapperFile(String path, Map<String, Object> value) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			File fr=new File(path);
			mapper.writeValue(fr, value);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeValueGson(String path, JsonObject obj) {
		Gson mapper = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter fr = new FileWriter(new File(path))) {
			mapper.toJson(obj, fr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Map<String, Object> readFileFromPathMapper(String path) {
		FileReader fr = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			fr = new FileReader(new File(path));
			return mapper.readValue(fr, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JsonObject readFileFromPathGson(String path) {
		FileReader fr = null;
		Gson mapper = new Gson();
		JsonObject jsonObj = null;
		try {
			fr = new FileReader(new File(path));
			jsonObj = mapper.fromJson(fr, JsonObject.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

}
