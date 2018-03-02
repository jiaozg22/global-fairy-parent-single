package org.global.fairy.core.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResultJsonUtil {

	public static String toJsonList(Object list) {
		String jsonString = "\"total\":" + ((List) list).size() + ",\"rows\":";
		String listString = JSONObject.toJSONString(list);
		return "{" + jsonString + listString + "}";
	}
	
	public static String result(boolean result){
		return result ? success("true"):fail("0"); 
	}

	public static String toJson(Object obj) {
		String jsonString = JSON.toJSONString(obj);
		return jsonString;
	}

	public static String success(Object obj) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(success());
		strBuf.append(JSON.toJSONString(obj));
		return strBuf.toString();
	}

	public static String success() {
		String success = "{'succss': 'true','errCode': '0000','data': ''}";
		return success.toString();
	}

	public static String fail(String errorCode) {
		String success = "{'succss': 'false','errCode': '" + errorCode
				+ "','data': ''}";
		return success.toString();
	}

}
