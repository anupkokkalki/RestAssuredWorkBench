import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import Pojo.Api;
import Pojo.GetInstructorCourse;
import Pojo.WebAutomation;

public class GetInstructorAPITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		queryParams("access_token",accessToken)

		String AuthGrantResponse = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(AuthGrantResponse);

		JsonPath authGrantRespJSONParserVar = new JsonPath(AuthGrantResponse);
		String accessTokenGrant = authGrantRespJSONParserVar.getString("access_token");

		System.out.println(accessTokenGrant);

		GetInstructorCourse getInstructorCourseResp = given().queryParam("access_token", accessTokenGrant).when().log()
				.all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetInstructorCourse.class);

		// getInstructorCourseResp.getLinkedIn();
		System.out.println(getInstructorCourseResp.getInstructor());

		System.out.println(getInstructorCourseResp.getUrl());
		System.out.println(getInstructorCourseResp.getServices());
		System.out.println(getInstructorCourseResp.getLinkedIn());
		System.out.println(getInstructorCourseResp.getCourses().getApi().get(1).getCourseTitle() + "hello");

		List<Api> APICourses = getInstructorCourseResp.getCourses().getApi();

		for (int i = 0; i < APICourses.size(); i++) {
			if (APICourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI WebsErvices testing")) {
				System.out.println("SoapUI Course Price is --> " + APICourses.get(i).getPrice());
			}

		}

		String[] expectedCoursesTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		ArrayList<String> alist = new ArrayList<String>();

		List<WebAutomation> w = getInstructorCourseResp.getCourses().getWebAutomation();

		// GET ALL COURSE NAME UNDER WEBAUTOMATION
		ArrayList<String> a = new ArrayList<String>();
		for (int i = 0; i < getInstructorCourseResp.getCourses().getWebAutomation().size(); i++) {
			System.out.println(
					"Printing --> " + getInstructorCourseResp.getCourses().getWebAutomation().get(i).getCourseTitle());

			alist.add(getInstructorCourseResp.getCourses().getWebAutomation().get(i).getCourseTitle());
			System.out.println("Add completes");

		}

		List<String> convertedExpectedCourseTitle = Arrays.asList(expectedCoursesTitles);
		Assert.assertTrue(alist.equals(convertedExpectedCourseTitle));

	}

}
