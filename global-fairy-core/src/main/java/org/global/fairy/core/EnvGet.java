package org.global.fairy.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EnvGet {

	public static String getEnvByKey(String key) {
		Map<String,String> map = System.getenv();
		Iterator it = map.entrySet().iterator();
		System.out.println();
		while (it.hasNext()) {
			
			Entry entry = (Entry) it.next();
			
			System.out.print(entry.getKey()+",");
			
//			System.out.println("待匹配值："+key);
			if (entry.getKey().equals(key)) {
//				System.out.print(entry.getKey() + "=");
//				System.out.println(entry.getValue());
				return entry.getValue().toString();
			}
		}
		System.out.println();
		return "没有读取到系统的环境变量配置" ;
	}

}
