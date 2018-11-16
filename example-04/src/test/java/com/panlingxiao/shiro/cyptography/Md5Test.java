package com.panlingxiao.shiro.cyptography;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

public class Md5Test {

    @Test
    public void md5() throws Exception{
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] bytes = digest.digest("hello".getBytes());
        Assert.assertEquals(16,bytes.length);
        // 0-F
        System.out.println(new String(bytes));

        System.out.println(bytes2Hex(bytes));
        System.out.println( new Md5Hash("hello").toHex());


        //sha1( rawData + session_key )
        digest = MessageDigest.getInstance("sha1");
        String source = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}HyVFkGl5F5OQWJZZaNzBBg==";
        byte[] data = digest.digest(source.getBytes());
        System.out.println(new String(data));
        //  75e81ceda165f4ffa64f4068af58c64b8f54b88c
        //  61398cb548f1d1ad4a2132d21ee90bc62e095161
        //  1693c85b841f1ddaa412232de19eb06ce2901516
        System.out.println(bytes2Hex(data));

        System.out.println(new Sha1Hash(source).toHex());
        System.out.println(DigestUtils.sha1Hex(source));
    }

    private static final char[] HEX_CHARS = {
            '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
    };
    //
    public  String bytes2Hex(byte[] data){
        char[] chars = new char[data.length * 2];
        for(int i = 0;i < data.length;i++){
            int j = i * 2;
            // 1101,1101 --> 0000,1101 --> 1000,1101
            chars[j] = HEX_CHARS[(data[i] >>>4) & 0x0f];
            chars[j+1] = HEX_CHARS[ data[i] & 0x0f];
        }
        return new String(chars);
    }

    public  String bytes2Hex2(byte[] data){
        char[] chars = new char[data.length * 2];
        for(int i = 0;i < data.length;i++){
            int j = i * 2;
            chars[j] = HEX_CHARS[data[i] & 0x0f];
            chars[j+1] = HEX_CHARS[(data[i] >>>4) & 0x0f];
        }
        return new String(chars);
    }






}
