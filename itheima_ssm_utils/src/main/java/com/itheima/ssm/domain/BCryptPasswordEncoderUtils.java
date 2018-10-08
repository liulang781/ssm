package com.itheima.ssm.domain;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {


    private static BCryptPasswordEncoder bc = new BCryptPasswordEncoder();


    public static String getPasswordEncoder(String password){
        String passwordEncode = bc.encode(password);
        return passwordEncode;
    }

   /* public static void main(String[] args) {
        String liulang = getPasswordEncoder("liulang");

        System.out.println(liulang.length()+liulang);
    }*/

}
