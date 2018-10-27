package com.panlingxiao.shiro.test;

import authorizer.permission.BitPermission;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class BitPermissionTest {

    @Test
    public void testBitPermission(){
        //表示可以对用户资源进行查看和修改操作
        BitPermission bitPermission = new BitPermission("+user+10");
        Assert.assertEquals("user",bitPermission.getResourceIdentify());
        Assert.assertEquals(10,bitPermission.getPermissionBit());
        Assert.assertEquals("*",bitPermission.getInstanceId());

        //可以删除Id为20号的用户
        bitPermission = new BitPermission("+user+2+20");
        Assert.assertEquals("user",bitPermission.getResourceIdentify());
        Assert.assertEquals(2,bitPermission.getPermissionBit());
        Assert.assertEquals("20",bitPermission.getInstanceId());

        //可以对用户资源进行任何操作
        bitPermission = new BitPermission("+user");
        Assert.assertEquals("user",bitPermission.getResourceIdentify());
        Assert.assertEquals(0,bitPermission.getPermissionBit());
        Assert.assertEquals("*",bitPermission.getInstanceId());


        bitPermission = new BitPermission("+user+");
        Assert.assertEquals("user",bitPermission.getResourceIdentify());
        Assert.assertEquals(0,bitPermission.getPermissionBit());
        Assert.assertEquals("*",bitPermission.getInstanceId());

        bitPermission = new BitPermission("+");
        Assert.assertEquals("*",bitPermission.getResourceIdentify());
        Assert.assertEquals(0,bitPermission.getPermissionBit());
        Assert.assertEquals("*", bitPermission.getInstanceId());


        bitPermission = new BitPermission("");
        Assert.assertEquals("*",bitPermission.getResourceIdentify());
        Assert.assertEquals(0,bitPermission.getPermissionBit());
        Assert.assertEquals("*", bitPermission.getInstanceId());

    }

    @Test
    public void testBitPermissionImpl(){
        BitPermission bitPermission = new BitPermission("+user+10");
        Assert.assertEquals(true,bitPermission.implies(new BitPermission("+user+2")));

    }
}
