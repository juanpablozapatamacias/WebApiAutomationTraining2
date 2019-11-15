package training.api.tests;

import training.api.main.BaseAPITest;
import training.api.utils.HttpResponse;
import training.api.utils.RespUtils;
import training.api.utils.TestNGRetry;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class TestDemo extends BaseAPITest{

	@BeforeTest
	public void setup() {
		rest = getRestAssuredInstance();
		base = getBaseURI("http://dummy.restapiexample.com");
	}
	
	@Test(retryAnalyzer = TestNGRetry.class)
	public void httpPostMethod() {
		rest.baseURI = base.getBaseURI() + "/api/v1/create";
		httpRequest = getHttpRequest(rest);
		
		String input = "{"
				+ "\"name\":\"TestJuanPablo10\","
				+ "\"salary\":\"123\","
				+ "\"age\":\"40\","
				+ "\"id\":\"\""
				+ "}";
		httpRequest.header("Content-Type", ContentType.JSON);
		httpRequest.body(input);
		resp = getResponse(httpRequest,Method.POST);
		
		httpResponse = getHttpResponse(resp);
		
		status = httpResponse.getStatus();
		System.out.println(httpResponse.getContent());
		System.out.println(httpResponse.getHeader());
		
		mapPost = RespUtils.getContentItems(httpResponse.getContent());
		
		Assert.assertEquals(status, 200);
	}
	
	@Test(dependsOnMethods="httpPostMethod", retryAnalyzer = TestNGRetry.class)
	public void httpGetMethod() {
		rest.baseURI = base.getBaseURI() + "/api/v1/employee/";
		httpRequest = getHttpRequest(rest);
		resp = getResponse(httpRequest, Method.GET, mapPost.get("id"));
		
		httpResponse = getHttpResponse(resp);
		
		status = httpResponse.getStatus();
		System.out.println(httpResponse.getContent());
		System.out.println(httpResponse.getHeader());
		
		mapGet = RespUtils.getContentItems(httpResponse.getContent());
		
		Assert.assertEquals(status, 200, "Status is failed");
	}
	
	@Test(dependsOnMethods="httpGetMethod", retryAnalyzer = TestNGRetry.class)
	public void validate(){
		Assert.assertEquals(mapPost.get("id"),mapGet.get("id"),
				"Employee ID values do not match in json request and response");
		Assert.assertEquals(mapPost.get("name"), mapGet.get("employee_name"), 
				"Employee Name values do not match in json request and response");
		Assert.assertEquals(mapPost.get("salary"), mapGet.get("employee_salary"),
				"Employee Salary values do not match in json request and response");
		Assert.assertEquals(mapPost.get("age"), mapGet.get("employee_age"),
				"Employee Age values do not match in json request and response");
	}
	
	@AfterTest
	public void teardown() {
		base.resetBaseURI();
		base.resetBasePath();
		httpRequest = null;
		rest = null;
	}
}
