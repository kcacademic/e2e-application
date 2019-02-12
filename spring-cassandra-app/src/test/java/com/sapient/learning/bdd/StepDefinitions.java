package com.sapient.learning.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	@Given("^There is data available$")
	public void request_on_api_words() throws Exception {
		System.out.println("The given step has been invoked.");
		//throw new PendingException();
	}

	@When("^Request on /api/words$")
	public void there_is_a_data_source_available() throws Exception {
		System.out.println("The when step has been invoked.");
		//throw new PendingException();
	}

	@Then("^Return list of words$")
	public void return_list_of_words() throws Exception {
		System.out.println("The then step has been invoked.");
		//throw new PendingException();
	}

}
