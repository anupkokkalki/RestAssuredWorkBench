import org.hamcrest.text.IsEqualIgnoringCase;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParseWithRestAssured {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath Js = new JsonPath(Payload.CoursePlanWithRestAssured());
		String JsToParse = Js.prettify();

//		Print No. of courses returned by API

		int coursesArraySize = Js.getInt("courses.size()");
		System.out.println("Objective 1 to Print #coursesArraySize in response returned =" + "   " + coursesArraySize);

//Print purchase amount
		int purchaseAmount = Js.getInt("dashboard.purchaseAmount");
		System.out.println("Objective 2 to Print purchase amount  =" + purchaseAmount);
//
//Print the Title of the first course
		String FirstCourseTitle = Js.getString("courses[" + 0 + "].title");
		System.out.println("Objective 2 to Print the Title of the first course =" + FirstCourseTitle);

//Print All course Title and their respective Prices

		for (int i = 0; i < coursesArraySize; i++) {
			String CourseTitle = Js.getString("courses[" + i + "].title");
			int CoursePrice = Js.getInt("courses[" + i + "].price");

			System.out.println("Objective 3 Print All course Title and their respective Prices, So course title ="
					+ CourseTitle + " and price =" + CoursePrice);

		}

//Print no of copies sold by RPA course

		for (int i = 0; i < coursesArraySize; i++) {

			if (Js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				int copiesOfRPASold=Js.getInt("courses["+i+"].copies");
				System.out.println("Objective 4 Print no of copies sold by RPA course =  "+copiesOfRPASold);
				break;
			}

		}

//Verify if the sum of all course prices matches with Purchase Amount
		int FinalCoursePrice=0;
		for (int i = 0; i < coursesArraySize; i++) {
			int CoursePrice = Js.getInt("courses[" + i + "].price");
			int Coursecopies= Js.getInt("courses["+ i + "].copies");

			SumValidation calci= new SumValidation();
			int CurrentcoursePurchasePrice = calci.purchaseAmountComputation(CoursePrice, Coursecopies);
					
					
		 int printfinal = FinalCoursePrice+CurrentcoursePurchasePrice;
			
		
			System.out.println(printfinal);

		}

	}

}
