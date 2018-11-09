package com.panlingxiao.shiro.cyptography;


import com.panlingxiao.shiro.realm.ByteUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

public class Md5Test {

    @Test
    public void md5() throws Exception{
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] bytes = digest.digest("hello".getBytes());
        Assert.assertEquals(16,bytes.length);
        System.out.println(new String(bytes));


        System.out.println(new SimpleHash("MD5","hello").toHex());
        System.out.println(ByteUtil.byte2Hex(bytes));

    }







}
