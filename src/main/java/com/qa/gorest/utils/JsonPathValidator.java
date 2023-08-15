package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private String jsonResponse(Response response) {
		return response.getBody().asString();
	}
	//single primitive
	//T means type return any type - primitive, non primitive
	public <T> T read(Response response, String jsonPath) {
		
		//String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse(response), jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath + "is not found....");
		}
	}
	
	public <T> List<T> readList(Response response, String jsonPath) {
			
		//	String jsonResponse = response.getBody().asString();
			try {
				return JsonPath.read(jsonResponse(response), jsonPath);
			}
			catch(PathNotFoundException e) {
				e.printStackTrace();
				throw new APIFrameworkException(jsonPath + "is not found....");
			}
	}
	
	public <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
		
		//String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse(response), jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath + "is not found....");
		}
	}

}
