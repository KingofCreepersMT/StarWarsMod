package com.multiteam.starwarsmod.utils;

/**
 * Created by fabbe on 20/12/2017 - 9:01 AM.
 */
public class StringUtilities {
    public static String upperCaseFirstLetter(String str) {
        String s1 = str.substring(0, 1).toUpperCase();
        return s1 + str.substring(1);
    }
}
