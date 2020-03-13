// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransApi.java

package com.baidu.translate.demo;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.baidu.translate.demo:
//            HttpGet, MD5

public class TransApi
{

    public TransApi(String appid, String securityKey)
    {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to)
    {
        Map params = buildParams(query, from, to);
        return HttpGet.get("http://api.fanyi.baidu.com/api/trans/vip/translate", params);
    }

    private Map buildParams(String query, String from, String to)
    {
        Map params = new HashMap();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        String src = (new StringBuilder(String.valueOf(appid))).append(query).append(salt).append(securityKey).toString();
        params.put("sign", MD5.md5(src));
        return params;
    }

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private String appid;
    private String securityKey;
}
