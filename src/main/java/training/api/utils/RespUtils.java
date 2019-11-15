package training.api.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RespUtils {
	static ObjectMapper objectMapper = new ObjectMapper();
	
	public static Map<String, String> getContentItems(String content){
		Map<String, String> mp = new HashMap<String, String>();
		try {
			mp = objectMapper.readValue(content, Map.class);
			return mp;
		}catch(JsonParseException jse) {
			System.out.println(jse.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static List<String> getIDItem(String content){
		List<String> lstId = new ArrayList<String>();
		
		try {
			JsonNode rootNode = objectMapper.readTree(content);
			
			for(JsonNode root : rootNode) {
				String id = root.path("id").asText();
				lstId.add(id);
			}
			
			return lstId;
		}
		catch(JsonParseException jse) {
			System.out.println(jse.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static List<String> getEmployeeNameItem(String content){
		List<String> lstId = new ArrayList<String>();
		
		try {
			JsonNode rootNode = objectMapper.readTree(content);
			
			for(JsonNode root : rootNode) {
				String id = root.path("employee_name").asText();
				lstId.add(id);
			}
			
			return lstId;
		}
		catch(JsonParseException jse) {
			System.out.println(jse.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
	
	
	/*public static String getId(String content) {
		Map<String, String> map = getJson(content);
		return map.get("id");
	}
	
	public static String getName(String content) {
		Map<String, String> map = getJson(content);
		return map.get("name");
	}
*/
}
