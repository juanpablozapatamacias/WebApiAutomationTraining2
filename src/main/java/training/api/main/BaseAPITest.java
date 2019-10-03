package training.api.main;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import training.api.utils.BaseURI;
import training.api.utils.HttpResponse;

public class BaseAPITest {
	
	protected RestAssured rest;
	protected Response resp;
	protected RequestSpecification httpRequest;
	protected Method method;
	protected BaseURI base ;
	
	protected int status;
	
	protected HttpResponse httpResponse;
	
	public BaseAPITest() {}
	
	protected RestAssured getRestAssuredInstance() {
		if(rest==null) rest = new RestAssured();
		
		return rest;
	}
	
	protected BaseURI getBaseURI(String uri, String path){
		return new BaseURI(uri, path); 
	}
	
	protected Response getResponse(RequestSpecification rqSpec, Method met, String arg) {
		return rqSpec.request(met,arg);
	}
	
	protected Response getResponse(RequestSpecification rqSpec, Method met) {
		return rqSpec.request(met);
	}
		
	protected HttpResponse getHttpResponse (Response rs) {
		HttpResponse r = new HttpResponse();
		int status = rs.getStatusCode();
		String content =  "";
		String header = "";
		
		if(rs.getStatusCode() == 200) {
			content = rs.asString();
			header = rs.getHeader("Content-Type");
			r.setContent(content);
		}
		else {
			String error  = rs.asString();
			header = rs.getHeader("Content-Type");
			r.setContent(error);
		}
		
		r.setStatus(status);
		r.setHeader(header);
		return r;
	}

}
