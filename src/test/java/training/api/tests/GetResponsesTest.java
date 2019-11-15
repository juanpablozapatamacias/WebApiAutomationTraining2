package training.api.tests;

import training.api.main.BaseAPITest;
import training.api.utils.HttpResponse;
import training.api.utils.RespUtils;
import training.api.utils.TestNGRetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class GetResponsesTest extends BaseAPITest{
	
	@BeforeTest
	public void setup() {
		rest = getRestAssuredInstance();
		base = getBaseURI("http://dummy.restapiexample.com");
		rest.baseURI = base.getBaseURI() + "/api/v1/employees";
		httpRequest = getHttpRequest(rest);
	}
	
	@Test
	public void getAllResponses(){
		resp = getResponse(httpRequest, Method.GET);
		
		httpResponse = getHttpResponse(resp);
		status = httpResponse.getStatus();
		System.out.println(httpResponse.getContent());
		
		Assert.assertEquals(status, 200, "Status is failed");
	}
	
	@Test(dependsOnMethods="getAllResponses", retryAnalyzer = TestNGRetry.class)
	public void getResponseIDItems() {
		resp = getResponse(httpRequest,Method.GET);
		String content = "";
		
		List<String> listIDs = new ArrayList<String>();
		
		httpResponse = getHttpResponse(resp);
		
		if(httpResponse.getStatus() == 200) {
			content = httpResponse.getContent();
			listIDs = RespUtils.getIDItem(content);
			
			System.out.println(listIDs);
		}
		
		Assert.assertTrue(!listIDs.isEmpty(), "No response for ID employees...");
	}
	
	@Test(dependsOnMethods="getResponseIDItems")
	public void getResponseEmployeeNameItems() {
		resp = getResponse(httpRequest,Method.GET);
		String content = "";
		httpResponse = getHttpResponse(resp);
		
		List<String> listEmpNames = new ArrayList<String>();
		
		if(httpResponse.getStatus() == 200) {
			content = httpResponse.getContent();
			listEmpNames = RespUtils.getEmployeeNameItem(content);
			System.out.println(listEmpNames);
		}
		
		Assert.assertTrue(!listEmpNames.isEmpty(), "No response for Employee Names ...");
		
	}
	
	@AfterTest
	public void teardown() {
		base.resetBaseURI();
		base.resetBasePath();
		httpRequest = null;
		rest = null;
	}
}
