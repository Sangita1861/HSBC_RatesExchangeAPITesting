package stepDefinations;
import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Date;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.RatesAPIResurces;

public class StepDefination {
	RequestSpecification response;
	Response resp;
	String latestrateapiresponse;
	String apiresource;

@Given("{string} base URI")  //code for base URI and given method
public void latest_rates_api_base_uri(String string) {	
	System.out.println("\n"+string);
	RestAssured.baseURI = "https://api.ratesapi.io/api/";
	response = given();
}

@When("user calls {string} with GET http request") //code that will fetch resources path from RatesAPIResurces class based on the input API name
public void user_calls_with_get_http_request(String API) {
    
	
	RatesAPIResurces resourcesAPI = RatesAPIResurces.valueOf(API);
    resp = response.when().log().all().get(resourcesAPI.getResources());
    apiresource = resourcesAPI.getResources().toString();
    System.out.println("apiresource "+apiresource);
}
@Then("the user gets response with status code {int}") //code to validate response status code 
public String the_user_gets_response_with_status_code(Integer int1) {
	System.out.println("Expected status code is: "+int1);
     latestrateapiresponse = resp.then().assertThat().statusCode(int1).extract().response().asString();
     System.out.println("response is: "+latestrateapiresponse);
     return latestrateapiresponse;
}
@Then("the {string} in response is {string}") //code to assert value of 'base' in the response
public void the_in_response_is(String string, String axpectedbase) {
	JsonPath js = new JsonPath(latestrateapiresponse);
    String base = js.getString("base");
    System.out.println("actual base is: "+base); 
    Assert.assertEquals(base, axpectedbase);
}

@Then("the response contains expected error message in {string}") //code to assert response of invalid URI
public void the_response_contains_expected_error_message_in(String string) {
	JsonPath js1 = new JsonPath(latestrateapiresponse);
    String actual_message = js1.getString(string);
    System.out.println(string +" is: "+actual_message);
    String  expected_message = "time data '"+apiresource+"' does not match format '%Y-%m-%d'";
    System.out.println("expected is: "+expected_message);
    Assert.assertEquals(expected_message,actual_message);   
}


@When("user calls {string} with GET http request for date {string}") //code to call GET request with specific date as a input
public void user_calls_with_get_http_request_for_date(String string, String date) {
	String pastRatesAPIresource=date;
	resp = response.when().log().all().get(pastRatesAPIresource);
}

@Given("{string} base URI  for specific {string} {string}") //code to call GET method on specific base or symbol values

public void base_uri_for_specific(String string, String key, String value) {
	System.out.println("\n"+string);
	RestAssured.baseURI = "https://api.ratesapi.io/api/";
	response = given().queryParam(key, value);
}

@Given("{string} base URI  for specific {string} {string} and {string} {string}") // code to call GET http methos with specific base and symbol together
public void base_uri_for_specific_and(String string, String key1, String value1, String key2, String value2) {
	System.out.println("\n"+string);
	RestAssured.baseURI = "https://api.ratesapi.io/api/";
	response = given().queryParam(key1,value1).queryParam(key2, value2);
	
	
}

@Then("the {string} future date response matches with response of current date {string}")  //code to validate is future date response is same as current date response or not
public void the_response_matches_with_response_of_current_date(String API, String current_date) {
	
	//System.out.println("\n inside future date validation0");
	String actual_response = latestrateapiresponse;
	System.out.println("actual_response: "+actual_response);
	
	//code to get response for current date
	latest_rates_api_base_uri(API);
	user_calls_with_get_http_request_for_date(API, current_date);
	String currentdateresponse = the_user_gets_response_with_status_code(200);
	System.out.println("currentdateresponse: "+currentdateresponse);
	
	Assert.assertEquals(actual_response, currentdateresponse);
	
}

@Then("the value of {string} is {string}")
public void the_value_of_is(String key, String expectedvalue) {
  
	JsonPath js = new JsonPath(latestrateapiresponse);
    String actualvalue = js.getString("rates."+key);
    System.out.println("actual value is: "+actualvalue); 
    System.out.println("expectedvalue: "+expectedvalue);
    Assert.assertEquals(actualvalue, expectedvalue);
    
	
}


}