package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.Product;


public class CreateProductTest extends BaseTest {

	@BeforeMethod
	public void createUserSetUp() {
		restClient = new RestClient(prop, baseURI);

	}
	
	@DataProvider
	public static Object[][] getProductTestData() {
		
		return new Object[][] {
			
			{"Men Tshirt", 515.55, "Black Color", "https://fake.com", "men clothing"},
			{"Men Trouser", 200.22, "White Color", "https://api.com", "men clothing"},
			{"Men Jacket", 720.77, "Green Color", "https://apitest.com", "men clothing"}
			
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void createProductTest(String title, Double price, String description, String category, String image) {
		
		//Product product = new Product("Men Tshirt", 515.55, "Black color", "men clothing", "https://fakestoreapi.com");
		Product product = new Product(title, price, description, category, image);
		
		restClient.post(FAKESTORE_ENDPOINT,"JSON" , product, false, true)
		.then().log().all()
		 .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		 	//.extract().path("id");

	}
}
