package entity;

import util.MD5Util;

public class test {
    public static void main(String[] args) {
        String password= MD5Util.MD5PassWord("j");
        System.out.println(password);
    }
}
