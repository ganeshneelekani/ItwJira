package com.agileidc.itw.helper;

import java.security.*;  

public class Encrypt {  

  public static String md5(String plainText) {  
      String encryptedText = "";  
      try {  
          MessageDigest md = MessageDigest.getInstance("MD5");  
          byte encryptedData[] = md.digest(plainText.getBytes());  
          StringBuffer hexString = new StringBuffer();  
          for (int i = 0; i < encryptedData.length; i++) {  
              String hex = Integer.toHexString(0xFF & encryptedData[i]);  
              if (hex.length() == 1) {  
                  hexString.append('0');  
              }  
              hexString.append(hex);  
          }  
          encryptedText = hexString.toString();  

      } catch (NoSuchAlgorithmException e) {  
          e.printStackTrace();  
      }  
      return encryptedText;  
  }  
}  