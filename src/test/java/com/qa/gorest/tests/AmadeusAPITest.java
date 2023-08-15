package com.qa.gorest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{
	
	private String accessToken;
	
	@Parameters({"baseURI", "grantType", "clientId", "clientSecret"})
	@BeforeMethod
	public void flightAPISetup(String baseURI, String grantType, String clientId, String clientSecret){
		
		restClient = new RestClient(prop, baseURI);
		accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);
		
	}
	
	@Test
	public void getFlightInfoTest() {
		
		//two apis are calling so we need two seperate RestClient
		RestClient restClientFlight = new RestClient(prop, baseURI);
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);
		
		Map<String, String> headersMap =  new HashMap<>();
		headersMap.put("Authorization", "Bearer " + accessToken);
		
		Response flightDataResponse = 
		restClientFlight.get(AMADEUS_FLIGHTBOOKING_ENDPOINT	, headersMap, queryParams , false, true)
							.then().log().all()
							.assertThat()
							.statusCode(APIHttpStatus.OK_200.getCode())
							.and().log().all()
							.extract().response();
							
		JsonPath js = flightDataResponse.jsonPath();	
		String type = js.get("data[0].type");
		System.out.println("type = " + type);
		
		//call get api
//		Response response = given().log().all()
//			.queryParam("origin", "PAR")
//			.queryParam("maxPrice", 200)
//			.header("Authorization", "Bearer " + accessToken)
//			.when()
//			.get("/v1/shopping/flight-destinations")
//			.then().log().all()
//			.assertThat()
//			.statusCode(200)
//			.extract()
//			.response();
			// .body("data[0].type", equalTo("flight-destination"));
//		JsonPath js = response.jsonPath();	
//		String type = js.get("data[0].type");
//		System.out.println("type = " + type);
//		
//		//return all lists price
//		List<String> priceList = js.getList("data.price.total");
//		for(String price : priceList) {
//			System.out.println(price);
//		}
		
		//returns all list of locations detailedName
//		List<String> detailedNameList = js.getList("dictionaries.locations.detailedName");
//		for(String detailedName : detailedNameList) {
//			System.out.println(detailedName);
//		}
		
			
		
	}

}
