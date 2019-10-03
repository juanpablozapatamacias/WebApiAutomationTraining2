package training.api.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RespUtils {
	static ObjectMapper objectMapper = new ObjectMapper();
	
	public static Map<String, String> getContentItems(String content){
		Map<String, String> mp = new HashMap<String, String>();
		try {
			mp = objectMapper.readValue(content, Map.class);
			return mp;
		} catch (IOException e) {
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
