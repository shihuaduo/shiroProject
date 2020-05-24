package com.ceshi.utils;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class Md5Utils {

    @Test
    public void md5(){
//        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
//        credentialsMatcher.hash
        String password="123456";
        String md5Hash=new Md5Hash(password).toString();
        System.out.println("使用md5不加盐,散列一次进行加密:"+md5Hash);
        //加盐
        Object salt="上海";
        String md5Hash2=new Md5Hash(password,salt).toString();
        System.out.println("使用md5加盐,散列一次进行加密:"+md5Hash2);
        String md5Hash3=new Md5Hash(password,salt,2).toString();
        System.out.println("使用md5加盐,散列2次进行加密:"+md5Hash3);
    }

}
