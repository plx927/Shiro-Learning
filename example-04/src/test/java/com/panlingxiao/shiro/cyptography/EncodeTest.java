package com.panlingxiao.shiro.cyptography;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class EncodeTest {

    /**
     * 使用Shiro对数据进行MD5加密
     */
    @Test
    public void testMD5() {
        String str1 = new Md5Hash("123").toHex();
        String str2 = DigestUtils.md5Hex("123");
        System.out.println(str1);
        Assert.assertEquals(str1, str2);

    }

    /**
     * SHA1加密测试
     */
    @Test
    public void testSHA1() {
        String str1 = DigestUtils.sha1Hex("123");
        String str2 = new Sha1Hash("123").toHex();
        Assert.assertEquals(str1, str2);
    }


    @Test
    public void testSHA256() {
        String str1 = DigestUtils.sha256Hex("123");
        String str2 = new Sha256Hash("123").toHex();
        Assert.assertEquals(str1, str2);
    }


    /**
     * 使用PasswordService对同一个数据进行两次加密，得到的结果不一致,因为其使用的salt不一值
     */
    @Test
    public void testPasswordService() {
        DefaultPasswordService passwordService = new DefaultPasswordService();
        String str1 = passwordService.encryptPassword("123");
        String str2 = passwordService.encryptPassword("123");
        Assert.assertNotEquals(str1, str2);
        /*
         * salt值包含在密码之中，通过passwordsMatch方法可以先从密码中获取salt值
         * 然后将明文使用对应的加密算法和salt值进行加密,与实际传入的值进行比较
         */
        Assert.assertTrue(passwordService.passwordsMatch("123", str1));
        Assert.assertTrue(passwordService.passwordsMatch("123", str2));
    }


}
