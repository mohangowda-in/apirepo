package org.restapi.restapitesting;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJson {
	
	public static void parseObject(JSONObject jsonobj, String key) {
		System.out.println(jsonobj.has(key));
		System.out.println(jsonobj.get(key));
	}
	
	public static void getKey(JSONObject jsonobj, String key) {
		boolean exists = jsonobj.has(key);
		Iterator itr;
		String nextkey;
		if(!exists) {
			itr = jsonobj.keys();
			while(itr.hasNext()) {
				nextkey = (String)itr.next();
				if(jsonobj.get(nextkey) instanceof JSONObject) {
					System.out.println("Key..."+nextkey);
					if(exists == false) {
						getKey(jsonobj.getJSONObject(nextkey), key);
					}
				}else if(jsonobj.get(nextkey) instanceof JSONArray) {
					System.out.println("else jsonarray key..."+nextkey);
					JSONArray jarray = jsonobj.getJSONArray(nextkey);
					for(int i=0; i<jarray.length(); i++) {
						System.out.println(jarray.get(i));
					}
					
				}
			}
		}else {
			parseObject(jsonobj, key);
		}
		
	}
	
	public static void main(String str[]) {
		String inputstr = "{ \n" +
		        "\"claim_loss_type_cd\": \"TEL\",\n " +
		        "\"claim_type\": \"002\",\n" +
		        "\"claim_reason\": \"001\",\n" +
		        "\"policy_number\": \"12kkr33445\",\n" +
		        "\"info\" : { \n" +
		            "\"apllicationContext\" : { \n" +
		                "\"country\" : \"US\", \n" +
		                
		            "} \n" +
		        "}, \n" +
		        "\"cate\": [ \n" +
		        "\"category\" : [4, 7, 9] \n" +
		        "] \n" +
		      "} ";
		
		JSONObject jobt = new JSONObject(inputstr);
		getKey(jobt, "category");
	}

}
