package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {
	
//	RestClient restClient;
	
	@BeforeMethod
	public void createUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] getUserTestData() {
		
		return new Object[][] {
			
			{"Mohit", "male", "active"},
			{"Seema", "female", "inactive"},
			{"Tom", "male", "inactive"}
		};
	}
	
	@DataProvider
	public Object[][] getUserTestSheetData() {
		return ExcelUtil.getTestData(APIConstants.GOREST_USER_SHEET_NAME);
	}
	
	@Test(dataProvider="getUserTestSheetData")
	public void createUser(String name, String gender, String status) {
		
		//1. post
	//	User user = new User("naveen", StringUtils.getRandomEmailId(), "male", "active");
		User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
		
		//RestClient restClient = new RestClient();
		Integer userId= restClient.post(GOREST_ENDPOINT, "JSON", user, true, true)
					.then().log().all()
					 .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
					 	.extract().path("id");
		System.out.println("User id: " + userId);
		
		//2. GET;
		RestClient restClientGet = new RestClient(prop, baseURI);
		restClientGet.get(GOREST_ENDPOINT + "/"+ userId, true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
						.body("id", equalTo(userId));
		}
	
	@Test
	public void createUserAPISchemaTest(String name, String gender, String status) {
		
		//1. post
	 User user = new User("Tom", StringUtils.getRandomEmailId(), "male", "active");
		//User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
		
		//RestClient restClient = new RestClient();
		restClient.post(GOREST_ENDPOINT, "JSON", user, true, true)
					.then().log().all()
					 .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
					 .body(matchesJsonSchemaInClasspath("createuserschema.json"));
		
		}
	}

