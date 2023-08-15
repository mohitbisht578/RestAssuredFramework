package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class GetUserTest extends BaseTest {
	
	//RestClient restClient;
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getAllUsersTest() {
		 restClient.get(GOREST_ENDPOINT, true, true)
		 		   .then().log().all()
		 		   .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test
	public void getUserTest() {
		// restClient = new RestClient(prop, baseURI);
		 restClient.get(GOREST_ENDPOINT + "/" + "4181342", true, true)
		 		   .then().log().all()
		 		   .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		 		   .and().body("id", equalTo(4181342));
	}

	@Test
	public void getUserWithQueryParamTest() {
		// restClient = new RestClient(prop, baseURI);
		 Map<String, Object> queryParams = new HashMap<>();
		 queryParams.put("name", "naveen");
		 queryParams.put("status", "active");
		 restClient.get(GOREST_ENDPOINT, null, queryParams, true, true)
		 		   .then().log().all()
		 		   .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
}
