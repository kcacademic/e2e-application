package com.sapient.learning.bdd;

import static org.hamcrest.CoreMatchers.is;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private String SERVICE_ENDPOINT = "http://localhost:8085/api/words";

	@Given("^Request is sent to /api/words$")
	public void request_is_sent_to_api_words() throws Exception {
		System.out.println("The given step has been invoked.");
		request = RestAssured.given();
	}

	@When("^Response is generated$")
	public void response_is_generated() throws Exception {
		System.out.println("The when step has been invoked.");
        response = request.when().get(SERVICE_ENDPOINT);
	}

	@Then("^There is a list of (\\d+) words or less in the response$")
	public void there_is_a_list_of_words_or_less_in_the_response(int count) throws Exception {
		System.out.println("The then step has been invoked.");
		json = response.then().statusCode(200);
		json.body("size()", is(count));
	}

}
