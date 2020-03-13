// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpGet.java

package com.baidu.translate.demo;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.net.ssl.*;

class HttpGet
{

    HttpGet()
    {
    }

    public static String get(String host, Map params)
    {
        try
        {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] {
                myX509TrustManager
            }, null);
            String sendUrl = getUrlWithQueryString(host, params);
            URL uri = new URL(sendUrl);
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            if(conn instanceof HttpsURLConnection)
                ((HttpsURLConnection)conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            int statusCode = conn.getResponseCode();
            if(statusCode != 200)
                System.out.println((new StringBuilder("Http\u9519\u8BEF\u7801\uFF1A")).append(statusCode).toString());
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            for(String line = null; (line = br.readLine()) != null;)
                builder.append(line);

            String text = builder.toString();
            close(br);
            close(is);
            conn.disconnect();
            return text;
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(KeyManagementException e)
        {
            e.printStackTrace();
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrlWithQueryString(String url, Map params)
    {
        if(params == null)
            return url;
        StringBuilder builder = new StringBuilder(url);
        if(url.contains("?"))
            builder.append("&");
        else
            builder.append("?");
        int i = 0;
        for(Iterator iterator = params.keySet().iterator(); iterator.hasNext();)
        {
            String key = (String)iterator.next();
            String value = (String)params.get(key);
            if(value != null)
            {
                if(i != 0)
                    builder.append('&');
                builder.append(key);
                builder.append('=');
                builder.append(encode(value));
                i++;
            }
        }

        return builder.toString();
    }

    protected static void close(Closeable closeable)
    {
        if(closeable != null)
            try
            {
                closeable.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
    }

    public static String encode(String input)
    {
        if(input == null)
            return "";
        try
        {
            return URLEncoder.encode(input, "utf-8");
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return input;
    }

    protected static final int SOCKET_TIMEOUT = 10000;
    protected static final String GET = "GET";
    private static TrustManager myX509TrustManager = new X509TrustManager() {

        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

        public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

    }
;

}
