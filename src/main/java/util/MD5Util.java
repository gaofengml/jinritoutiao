package util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util{
    //加密
    public static String MD5PassWord(String password){
        return DigestUtils.md5Hex(password.getBytes());
    }

    //验证
    public static boolean checkPassword(String password,String MD5PassWord){
        return DigestUtils.md5Hex(password.getBytes()).equals(MD5PassWord);
    }
}
