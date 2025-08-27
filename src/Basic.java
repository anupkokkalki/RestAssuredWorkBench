import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();
		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

//		updatePlace
//		String putResponse=
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n"
						+ "\"address\":\"110 MG,Hubbali, Karnataka, India\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.52 (Ubuntu)");
//		System.out.println(putResponse);

		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).and()
				.body("address", equalTo("110 MG,Hubbali, Karnataka, India"));
		
  
	}

}
