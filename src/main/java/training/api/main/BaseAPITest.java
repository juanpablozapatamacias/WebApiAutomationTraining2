package training.api.main;

import java.util.HashMap;
import java.util.Map;

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
	
	protected Map<String, String> mapPost = new HashMap<String,String>();
	protected Map<String, String> mapGet = new HashMap<String,String>();
	
	public BaseAPITest() {}
	
	protected RestAssured getRestAssuredInstance() {
		if(rest==null) 
			rest = new RestAssured();
		
		return rest;
	}
	
	protected BaseURI getBaseURI(String uri, String path){
		if (base == null) 
			base = new BaseURI(uri, path); 
		
		return base;
	}
	
	protected BaseURI getBaseURI(String uri) {
		if (base == null) 
			base = new BaseURI(uri); 
		
		return base;
	}
	
	protected Response getResponse(RequestSpecification rqSpec, Method met, String arg) {
		Response rsp = null; 
		try {
			rsp = rqSpec.request(met,arg);
			return rsp;
		}
		catch(Exception e) {}
		return null;
	}
	
	protected Response getResponse(RequestSpecification rqSpec, Method met) {
		Response rsp = null; 
		try {
			rsp = rqSpec.request(met);
			return rsp;
		}
		catch(Exception e) {}
		return null;
	}
	
	protected RequestSpecification getHttpRequest(RestAssured rest) {
		RequestSpecification rs = null;
		if(rest !=null) 
			rs = rest.given();
		
		return rs;
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
