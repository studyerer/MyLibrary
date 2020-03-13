// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5.java

package com.baidu.translate.demo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{

    public MD5()
    {
    }

    public static String md5(String input)
    {
        if(input == null)
            return null;
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte inputByteArray[] = input.getBytes();
            messageDigest.update(inputByteArray);
            byte resultByteArray[] = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        }
        catch(NoSuchAlgorithmException e)
        {
            return null;
        }
    }

    public static String md5(File file)
    {
        if(file.isFile())
            break MISSING_BLOCK_LABEL_39;
        System.err.println((new StringBuilder("\u6587\u4EF6")).append(file.getAbsolutePath()).append("\u4E0D\u5B58\u5728\u6216\u8005\u4E0D\u662F\u6587\u4EF6").toString());
        return null;
        try
        {
            FileInputStream in = new FileInputStream(file);
            String result = md5(((InputStream) (in)));
            in.close();
            return result;
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(InputStream in)
    {
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte buffer[] = new byte[1024];
            for(int read = 0; (read = in.read(buffer)) != -1;)
                messagedigest.update(buffer, 0, read);

            in.close();
            String result = byteArrayToHex(messagedigest.digest());
            return result;
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteArrayToHex(byte byteArray[])
    {
        char resultCharArray[] = new char[byteArray.length * 2];
        int index = 0;
        byte abyte0[];
        int j = (abyte0 = byteArray).length;
        for(int i = 0; i < j; i++)
        {
            byte b = abyte0[i];
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        return new String(resultCharArray);
    }

    private static final char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

}
