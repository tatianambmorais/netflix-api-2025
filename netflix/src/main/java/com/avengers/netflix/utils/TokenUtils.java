package com.avengers.netflix.utils;

import java.util.Random;

public class TokenUtils {
    public static String generateToken(){
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }
}
