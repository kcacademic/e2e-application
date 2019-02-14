package com.sapient.learning.bdd

import cucumber.api.java8.En
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.equalTo

class StepDefinitions : En {

	lateinit var response: Response
	lateinit var json: ValidatableResponse
	lateinit var request: RequestSpecification
	val SERVICE_ENDPOINT = "http://localhost:8085/api/words"

	init {

		Given("^Request is sent to /api/words$") {
			System.out.println("The given step has been invoked.")
			request = RestAssured.given();
		}

		When("^Response is generated$") {
			System.out.println("The when step has been invoked.")
			response = request.`when`().get(SERVICE_ENDPOINT)
		};

		Then("^There is a list of (\\d+) words or less in the response$") { count: Int ->
			System.out.println("The then step has been invoked.")
			json = response.then().statusCode(200)
			json.body("size()", equalTo(count))
		};
	}


}