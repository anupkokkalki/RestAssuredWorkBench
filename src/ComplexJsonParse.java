import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// Parse the JSON using JsonPath
		JsonPath js = new JsonPath(Payload.CoursePlan());

		// Print the JSON in a pretty format
		String JSpaste = js.prettyPrint();
		System.out.println(JSpaste);

		// Get the number of courses
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Get the purchase amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		// Get the title of the first course
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println(firstCourseTitle);

		// Loop through all courses and print their titles and prices
		for (int i = 0; i < count; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");
			String coursePrice = js.getString("courses[" + i + "].price");

			System.out.println("Course #" + (i + 1) + ": " + courseTitle);
			System.out.println("Course #" + (i + 1) + "price: " + coursePrice);
		}
		// Loop through all courses to find the course titled "RPA", If found, retrieve
		// and print the number of copies sold for that course

		for (int i = 0; i < count; i++) {
			if (js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				int RPAcopies = js.getInt("courses[" + i + "].copies");

				System.out.println("RPAcopies = " + RPAcopies);
				break;
			}

			/*
			 * for (int j = 0; j < count; j++) { int courseCopies = js.getInt("courses[" + j
			 * + "].copies"); int coursePrice = js.getInt("courses[" + j + "].price");
			 * 
			 * int Total = courseCopies * coursePrice;
			 * 
			 * System.out.println(Total); }
			 * 
			 * 
			 */
			
			 

		}
	}
}
