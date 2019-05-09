package com.bksoftware.sellingweb.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyFormDao {

    public static Boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean match = matcher.matches();
        return match;
    }

    public static Boolean checkPhone(String phone){
        String regex = "^[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        boolean match = matcher.matches();
        return match;
    }

    public static Boolean checkName(String name){
        String regex = "^[a-z._-]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean match = matcher.matches();
        return match;
    }


}


