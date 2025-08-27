import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test

	public void sumOfCourses() {
		JsonPath Js = new JsonPath(Payload.CoursePlanWithRestAssured());
		int coursesArraySize = Js.getInt("courses.size()");

		int sum = 0;
		for (int i = 0; i < coursesArraySize; i++) {
			int CoursePrice = Js.getInt("courses[" + i + "].price");
			int Coursecopies = Js.getInt("courses[" + i + "].copies");
			int amount = CoursePrice * Coursecopies;
			System.out.println(amount);
			sum = sum + amount;
		}
		System.out.println("total sum = "+sum);
		int purchaseAmount =Js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);

	}

	int purchaseAmountComputation(int price, int copies) {

		int computation = price * copies;
		return computation;

	}
}
