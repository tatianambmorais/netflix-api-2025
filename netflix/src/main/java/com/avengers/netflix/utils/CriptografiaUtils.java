package com.avengers.netflix.utils;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
public class CriptografiaUtils {
    public static String sha256(String original){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] hash=md.digest(original.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb=new StringBuilder();
            for(byte b:hash) sb.append(String.format("%02x",b));
            return sb.toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}