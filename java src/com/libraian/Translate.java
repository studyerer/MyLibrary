package com.libraian;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;


import com.baidu.translate.demo.TransApi;
 

 

public class Translate {
	// 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20191012000341054";
    private static final String SECURITY_KEY = "4RYRUHnIHusPZP6ORLnO";
	public String translateApi(String s1) throws JSONException{
	//s1和s2 是我从前台收的值，
		
	        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
	        System.out.println(api.toString()+"接口引用");
 
	        String transResult1 = api.getTransResult(s1, "zh", "en");
	        
	        JSONObject strJson;
			try {
				System.out.println(s1);
				strJson = new JSONObject(transResult1);
				JSONArray strJson1 = strJson.getJSONArray("trans_result");
				JSONObject  strJson2 = strJson1.getJSONObject(0) ;
				s1=strJson2.getString("dst");
				System.out.println("s1:     "+s1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 return s1;
	}
	
}
